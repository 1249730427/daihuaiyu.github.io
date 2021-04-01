package com.daihuaiyu.videoapplet.api.controller;

import com.daihuaiyu.videoapplet.api.service.BgmService;
import com.daihuaiyu.videoapplet.api.service.VideoService;
import com.daihuaiyu.videoapplet.api.util.ApiResponse;
import com.daihuaiyu.videoapplet.api.util.FfmPeg;
import com.daihuaiyu.videoapplet.core.domain.Bgm;
import com.daihuaiyu.videoapplet.core.domain.Video;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * 视频相关Controller
 * @author :daihuaiyu
 * @Description: 视频相关Controller
 * @create 2021/4/1 22:36
 */
@RestController
public class VideoController {

    @Autowired
    private BgmService bgmService;

    @Autowired
    private VideoService videoService;

    private static final String FiLESPACE ="D:/Study/ffmpeg";

    private static final Integer SIZE =4;

    /***
     * 用户上传视频业务逻辑：
     *1.前端传入用户ID,视频长度,视频宽度，视频时长，视频描述，以及上传的视频信息，校验用户ID
     *2.读取传入的视频文件流写入到制定文件夹目录下
     * 3.保存视频信息到数据库，返回视频信息
     * @return
     */
    @ApiOperation(value = "用户视频上传", notes = "用户视频上传的接口")
    @PostMapping(value = "/uploadVideo",headers = "content-type=multipart/form-data")
    public ApiResponse getBgmList(@RequestParam("id")String id, String bgmId, @RequestParam("videoSeconds") Integer videoSeconds, @RequestParam("videoWidth")int videoWidth
            , @RequestParam("videoHeight")int videoHeight, String desc , @ApiParam(value="短视频",required = true)MultipartFile file) throws IOException {
        if(StringUtils.isEmpty(id)){
            return ApiResponse.errorMsg("ID不能为空");
        }
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        String upLoadPathDb = "/"+id+"/video";
        String coverName = "/"+ UUID.randomUUID().toString()+".jpg";
        try {
            if(file!=null && !file.isEmpty()){
                String originalFilename = file.getOriginalFilename();
                upLoadPathDb = upLoadPathDb+"/"+originalFilename;
                String filePath = FiLESPACE+upLoadPathDb;
                File file1 = new File(filePath);
                String coverPath = FiLESPACE+"/"+id+"/video"+"/"+coverName;
                if(file1.getParentFile()!=null && !file1.getParentFile().isDirectory()){
                    file1.getParentFile().mkdirs();
                }
                fileOutputStream = new FileOutputStream(file1);
                inputStream = file.getInputStream();
                IOUtils.copy(inputStream,fileOutputStream);
                if(!StringUtils.isEmpty(bgmId)){  //bgmId不为空说明用户选择了背景音乐
                    //判断bgmid是否为空，如果不为空，说明用户选择了bgm，根据该bgmid对bgm进行查询
                    Bgm bgm = bgmService.findOne(bgmId);
                    //获取bgm的相对路径，并和命名空间拼接获得该bgm的绝对路径
                    String bgmPath = FiLESPACE+bgm.getPath();
                    //该路径为视频合成后需要存放的相对路径
                    upLoadPathDb ="/"+id+"/video/"+UUID.randomUUID().toString()+".mp4";
                    String videoOutPath = FiLESPACE+upLoadPathDb;
                    FfmPeg.convert(filePath,bgmPath,videoSeconds,videoOutPath);
                }
                FfmPeg.convert(FiLESPACE+FiLESPACE,coverPath);
                //TODO 保存视频到数据库
                Video videos =new Video();
                videos.setUserId(id);
                videos.setAudioId(bgmId);
                videos.setVideoDesc(desc);
                videos.setStatus(1);
                videos.setVideoSeconds((float)videoSeconds);
                videos.setVideoHeight(videoHeight);
                videos.setVideoWidth(videoWidth);
                videos.setVideoPath(upLoadPathDb);
                videos.setCreateTime(new Date());
                videos.setLikeCounts(0l);
                videos.setCoverPath(coverPath);
                videoService.save(videos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            fileOutputStream.close();
        }
        return ApiResponse.ok(upLoadPathDb);
    }

    @ApiOperation(value = "查询BGM列表", notes = "查询bgm列表的接口")
    @PostMapping("/bgmList")
    public ApiResponse getBgmList(){
        return ApiResponse.ok(bgmService.findAllBgm());
    }

}
