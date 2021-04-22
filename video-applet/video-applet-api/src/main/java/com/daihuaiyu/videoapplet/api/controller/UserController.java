package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.CommentService;
import com.daihuaiyu.videoapplet.api.service.UserFansService;
import com.daihuaiyu.videoapplet.api.service.UserLikeVideoService;
import com.daihuaiyu.videoapplet.api.service.UserService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.api.util.PageResult;
import com.daihuaiyu.videoapplet.core.domain.Comments;
import com.daihuaiyu.videoapplet.core.domain.PublishVo;
import com.daihuaiyu.videoapplet.core.domain.UserVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 用户相关信息Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-31 16:32
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    //默认每页显示数据数
    private final Integer SIZE=4;

    @Autowired
    private UserService userService;

    @Autowired
    private UserLikeVideoService userLikeVideoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserFansService userFansService;

    /**
     * 获取用户信息业务逻辑
     * 1.客户端传入用户的ID，服务端根据用户ID查找用户信息
     * 2.转换为UserVo返回前端
     * @param id
     * @return
     */
    @ApiOperation(value = "用户信息查询", notes = "用户信息查询的接口")
    @PostMapping("/findUserInfo")
    public ApiResponse findUserInfo(@RequestParam("id")String id,@RequestParam("fansId") String fansId){
        Boolean followed = false;
        if(StringUtils.isBlank(id)){
            return ApiResponse.errorMsg("用户的ID为空!");
        }
        if (!StringUtils.isBlank(fansId)){
            followed = userFansService.findIsFollowed(id, fansId);
        }
        UserVo userVo = new UserVo();
         Users users = userService.fingById(id);
        BeanUtils.copyProperties(users,userVo);
        userVo.setFollowed(followed);
        return ApiResponse.ok(userVo);
    }

    /**
     * 用户上传头像逻辑
     * 1.前端传入用户的ID和文件的内容，服务端对用户ID进行校验,ID不能为空
     * 2.读取文件内容，写入要存放图片的位置
     * 3.设置用户名中的头像字段的值
     * 4.返回路径，显示头像
     * @param id
     * @param file
     * @return
     */
    @ApiOperation(value = "用户头像上传", notes = "用户头像上传的接口")
    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("id")String id, @RequestParam("file") MultipartFile[] file) throws IOException {

        if(StringUtils.isBlank(id)){
            return ApiResponse.errorMsg("用户ID为空!");
        }
        InputStream inputStream = null;
        FileOutputStream fileOutputStream =null;
        try {
            String fileRootPath = "F:/file";
            String filePath = "/"+id+"/face";
            String originalFilename ="";
            if(file!=null && file.length>0){
               originalFilename = file[0].getOriginalFilename();
            }
            File  file1 = new File(fileRootPath+filePath+"/"+ originalFilename);
            //获得该文件存入数据库的相对路径
            String upLoadPathDb=filePath+"/"+ originalFilename;
            if(file1.getParentFile()!=null && !file1.getParentFile().isDirectory()){
                boolean mkdirs = file1.getParentFile().mkdirs();
            }
            if(file1 !=null){
                fileOutputStream = new FileOutputStream(file1);
            }
            if(file !=null && file.length>0){
                inputStream = file[0].getInputStream();
            }
            IOUtils.copy(inputStream,fileOutputStream);
            Users users = userService.fingById(id);
            users.setFaceImage(upLoadPathDb);
            userService.save(users);
            return ApiResponse.ok(upLoadPathDb);
        } catch (Exception e) {
            e.printStackTrace();
            if(fileOutputStream !=null){
                fileOutputStream.close();
            }
        } finally {
            if(inputStream !=null ){
                inputStream.close();
            }
            if(fileOutputStream !=null){
                fileOutputStream.close();
            }
        }

        return null;
    }

    @ApiOperation(value = "用户点赞功能", notes = "用户点赞功能")
    @PostMapping("/like")
    public ApiResponse like(String id,String videoId,String videoCreatedId){
        userLikeVideoService.like(id,videoId,videoCreatedId);
        return ApiResponse.ok(null);
    }

    @ApiOperation(value = "用户取消点赞功能", notes = "用户取消点赞功能")
    @PostMapping("/unlike")
    public ApiResponse unlike(String id,String videoId,String videoCreatedId){

        userLikeVideoService.unlike(id,videoId,videoCreatedId);
        return ApiResponse.ok(null);
    }

    @ApiOperation(value = "用户评论的接口/回复用户的留言", notes = "用户评论的接口/回复用户的留言")
    @PostMapping(value = "/saveComments")
    public ApiResponse saveComments(@RequestBody Comments comments){
        commentService.saveComment(comments);
        return ApiResponse.ok(null);
    }

    @ApiOperation(value = "查询用户留言", notes = "查询用户留言")
    @PostMapping(value = "/findComments")
    public ApiResponse findComments(String videoId,Integer page){
        PageResult pageResult = commentService.findComments(videoId, page, SIZE);
        return ApiResponse.ok(pageResult);
    }

    @ApiOperation(value = "查询视频发布者信息", notes = "查询视频发布者信息")
    @PostMapping("/findPublish")
    public ApiResponse findPublish(String id,String videoId,String publishId){
        //查询视频发布者的信息
        Users userInfo = userService.fingById(publishId);
        userInfo.setPassword("");
        //查询该用户是否给该视频点赞
        Boolean b = userLikeVideoService.userIsLike(id, videoId);
        PublishVo publishVo =new PublishVo();
        publishVo.setUsers(userInfo);
        publishVo.setIsLike(b);
        return ApiResponse.ok(publishVo);
    }
}

