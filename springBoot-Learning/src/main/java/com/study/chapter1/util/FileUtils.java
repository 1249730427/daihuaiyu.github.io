package com.study.chapter1.util;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @文件工具类
 *
 * @Author: daihuaiyu
 * @Date: 2020-12-24 15:19
 */
public class FileUtils {

    public static final String FILE_SEPARATOR = File.separator;

    public static final String JSON_SUFFIX = "json";

    /**
     * 创建文件夹
     *
     * @param dirPath
     */
    public static void makeDir(String dirPath) {
        // 判断上级目录是否存在
        String parenterDir = dirPath.substring(0, dirPath.indexOf(FILE_SEPARATOR, dirPath.lastIndexOf(FILE_SEPARATOR)));
        File localFile = new File(parenterDir);
        if (!localFile.exists()) {
            makeDir(parenterDir);
        }
        File dir = new File(dirPath);
        if (!dir.exists()) dir.mkdir();
    }

    public static void makeFile(List<String[]> fileArray, String filePath, String fileName, String splitChar) throws Exception {

        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            makeDir(filePath);
            if (!filePath.endsWith(FILE_SEPARATOR)) {
                filePath = filePath + FILE_SEPARATOR;
            }
            File file = new File(filePath + fileName);
            // 写取文件
            fileOutputStream = new FileOutputStream(file);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            int writtenLineNum = 0;
            for (String[] strs : fileArray) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < strs.length; i++) {
                    if (strs[i] != null && !strs[i].toLowerCase().equals("null")) {
                        sb.append(strs[i]);
                    }
                    //不是最后一列则拼分隔符
                    if (i != strs.length - 1) {
                        sb.append(splitChar);
                    }
                }
                bufferedWriter.write(sb.toString());
                writtenLineNum++;
                // 生成文件的最后一行不包含换行符
                if (writtenLineNum != fileArray.size()) {
                    bufferedWriter.write(13);
                    bufferedWriter.write(10);
                }
            }
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }

    }

    public static void makeFile(String str, String filePath, String fileName) throws Exception {
        String[] strings = new String[1];
        strings[0] = str;
        List<String[]> contentList = new ArrayList<>();
        contentList.add(strings);
        makeFile(contentList, filePath, fileName, null);
    }

    public static String readFile(File file) throws Exception {

        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String str = br.readLine();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            // 关闭读入流
            if (br != null) {
                br.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    public static void writer(List<String[]> fileArray, String filePath) throws IOException {
        FileUtils.writer(fileArray, ",", '"', filePath, "UTF-8");
    }

    /**
     * @param fileArray 文件数据内容
     * @param splitChar 分隔符号
     * @param quoteChar
     * @param filePath  本地文件路径
     * @param charSet   字符编码
     * @throws IOException
     */
    public static void writer(List<String[]> fileArray, String splitChar, char quoteChar, String filePath, String charSet) throws IOException {
        File file = new File(filePath);
        List<String[]> alreadyWriterList = new ArrayList<String[]>();
        if (file.exists()) {
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            CSVReader reader = new CSVReader(new InputStreamReader(in, charSet), splitChar.toCharArray()[0]);
            alreadyWriterList = reader.readAll();
            // 关闭读取器
            in.close();
            reader.close();
        }

        if (CollectionUtils.isEmpty(alreadyWriterList)) {
            alreadyWriterList = new ArrayList<>();
        }
        alreadyWriterList.addAll(fileArray);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charSet));
        CSVWriter csvWriter = new CSVWriter(writer, splitChar.toCharArray()[0], quoteChar);
        csvWriter.writeAll(alreadyWriterList);
        csvWriter.close();
    }

    /**
     * 将传入进来的data集合通过反射获取到对应实体类的列名及数据，并设置数据
     * @param data
     * @param parameterMap
     * @return List<String []> 需要写入文件的数据
     */
    public static List<String[]> ObjectToArray(List<Object> data, Map<Integer, String> parameterMap,Integer pageNum) {
        List<String[]> dataList = new ArrayList<>();
        Map<String, Object> filedMap = new HashMap<>();
        if(pageNum==1){
            String[] titleArray = new String[parameterMap.size()];  //标题数组
            if (data != null && data.size() > 0) {
                for (int i = 0; i < parameterMap.size(); i++) {
                    String parameterDefine = parameterMap.get(i);
                    final String toUnderline = StringUtils.camelToUnderline(parameterDefine);
                    titleArray[i] = toUnderline;
                }
                dataList.add(titleArray);  //添加标题数组
            }
        }
            //此处从数据库中查询出来的是对象
            data.stream().forEach(dataObj -> {
                try {
                    String[] dataArray = new String[parameterMap.size()];   //数据数组
                    //获取对象的Field信息
                    final Field[] fields = dataObj.getClass().getDeclaredFields();
                    for (int i = 0; i < fields.length; i++) {
                        String fieldName = fields[i].getName();
                        fields[i].setAccessible(true);
                        Object object = fields[i].get(dataObj);
                        fields[i].setAccessible(false);
                        filedMap.put(fieldName, object);
                    }
                    for (int i = 0; i < parameterMap.size(); i++) {
                        String parameterDefine = parameterMap.get(i);
                        Object o = filedMap.get(parameterDefine);
                        dataArray[i] = "";
                        if (o != null) {
                            if(o instanceof Date){
                                dataArray[i] = DateUtil.format((Date)o);
                            }else if(o instanceof LocalDateTime) {
                                dataArray[i] = DateUtil.format((LocalDateTime)o);
                            }else {
                                dataArray[i] = o.toString();
                            }
                        }
                    }
                    dataList.add(dataArray);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        return dataList;
    }
}