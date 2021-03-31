package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.UserService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.core.domain.UserVo;
import com.daihuaiyu.videoapplet.core.domain.Users;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 用户相关信息Controller
 *
 * @author daihuaiyu
 * @create: 2021-03-31 16:32
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 获取用户信息业务逻辑
     * 1.客户端传入用户的ID，服务端根据用户ID查找用户信息
     * 2.转换为UserVo返回前端
     * @param id
     * @return
     */
    @ApiOperation(value = "用户信息查询", notes = "用户信息查询的接口")
    @PostMapping("/findUserInfo")
    public ApiResponse findUserInfo(@RequestParam("id")String id){
        if(StringUtils.isBlank(id)){
            return ApiResponse.errorMsg("用户的ID为空!");
        }
        UserVo userVo = new UserVo();
         Users users = userService.fingById(id);
        BeanUtils.copyProperties(users,userVo);
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
        InputStream InputStream = null;
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
                file1.getParentFile().mkdirs();
            }
            fileOutputStream= new FileOutputStream(file1);
            InputStream = file[0].getInputStream();
            IOUtils.copy(InputStream,fileOutputStream);
            Users users = userService.fingById(id);
            users.setFaceImage(upLoadPathDb);
            userService.save(users);
            return ApiResponse.ok(upLoadPathDb);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            InputStream.close();
            fileOutputStream.close();
        }

        return null;
    }
}

