package com.daihuaiyu.videoapplet.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 视屏处理工具类
 *
 * @author :daihuaiyu
 * @Description: 该类为对音频视频进行合成处理的工具类
 * @create 2021/4/1 21:47
 */
public class FfmPeg {

    private static final  String FFMPEGEXE="D:\\Study\\ffmpeg\\bin\\ffmpeg.exe";

    /**
     * FfmPeg主要是通过cmd命令对文件进行相关操作的:
     *  ffmpeg.exe -i test.mp4  -i bgm.mp3 -t 3 -y new.mp4:该命令的含义为将test.mp4和bgm.mp3合成为一个新的mp4文件，且长度为3s，使用该命令就可以将我们的音频和视频进行合成的处理了
     *  ffmpeg.exe -ss 00:00:02 -y -i lex.mp4 -vframes 1 lex.jpg:改命令表示把lex.mp4这个视频的第二秒的内容进行一个截图，并保存为lex.jpg，使用该命令就可以生成视频的封面图
     * @param in
     * @param mp3
     * @param seconds
     * @param out
     * @throws IOException
     */
    public static void convert(String in,String mp3,Integer seconds,String out) throws IOException {
        //ffmpeg.exe -i test.mp4  -i bgm.mp3 -t 3 -y new.mp4
        //把一段命令通过空格的方式分割，并存入list
        List<String> cmd=new ArrayList();
        cmd.add(FFMPEGEXE);
        cmd.add("-i");
        cmd.add(in);
        cmd.add("-i");
        cmd.add(mp3);
        cmd.add("-t");
        cmd.add(String.valueOf(seconds));
        cmd.add("-y");
        cmd.add(out);
        //该类为java中操作cmd命令的一个类，参数中需要传入一个list列表
        ProcessBuilder processBuilder =new ProcessBuilder(cmd);
        Process process = processBuilder.start();
        //下面的操作是对FFMPeg中错误流的处理，如果不做该处理，最后合成的视频会不完整且无法播放
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        while ( br.readLine() != null ) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }
    public static void convert(String in, String out) throws IOException {
        //ffmpeg.exe -ss 00:00:01 -y -i lex.mp4 -vframes 1 lex.jpg
        List<String> cmd=new ArrayList<String>();
        cmd.add(FFMPEGEXE);
        cmd.add("-ss");
        cmd.add("00:00:01");
        cmd.add("-y");
        cmd.add("-i");
        cmd.add(in);
        cmd.add("-vframes");
        cmd.add("1");
        cmd.add(out);
        ProcessBuilder processBuilder =new ProcessBuilder(cmd);
        Process process = processBuilder.start();
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line = "";
        while ( (line = br.readLine()) != null ) {
        }
        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
    }

    public static void main(String[] args) {
        try {
            FfmPeg.convert("D:\\Study\\ffmpeg\\bin\\lex.mp4","D:\\Study\\ffmpeg\\bin\\lex.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
