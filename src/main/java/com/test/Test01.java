package com.test;

import java.io.*;

public class Test01 {

    public static String getRootPath() {
        Class aClass = null;
        String rootPath = null;
        try {
            rootPath = aClass.getMethod("getRootPath").invoke(null) == null?
                    "":aClass.getMethod("getRootPath").invoke(null).toString();

            rootPath = rootPath.replaceAll("/+","/");
            rootPath = rootPath.replaceAll("\\\\+","/");

            if(!rootPath.endsWith("/")) {
                rootPath = rootPath + "/";
            }
        } catch (Exception e) {
            e.printStackTrace();
            rootPath = "";
        }
        return rootPath;
    }

    public static String nullToString(Object s){
        if(s == null){
            return "";
        }else{
            return s.toString().trim();
        }

    }




    /**
     * 根据字节码写出文件
     * @param path
     * @param bytes
     */
    public static boolean writeClassFile(String path, byte[] bytes) {
        FileOutputStream fileOutputStream = null;
        try {
            String filePath = path.substring(0,path.lastIndexOf("/"));
            checkAndCreate(filePath);
            fileOutputStream = new FileOutputStream(new File(path));
            fileOutputStream.write(bytes,0,bytes.length);
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static byte[] readToByte(String path) {
        FileInputStream inputStream = null;
        ByteArrayOutputStream outStream = null;
        byte[] bytes = null;
        try {
            int len = 0;
            inputStream = new FileInputStream(new File(path));
            outStream = new ByteArrayOutputStream();
            byte [] buffer = new byte[1024];
            while( (len = inputStream.read(buffer)) != -1){
                outStream.write(buffer, 0, len);
            }
            bytes = outStream.toByteArray();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    /**
     * 检测文件是否存在
     */
    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    /**
     * 检测文件是否存在
     */
    public static boolean isFileExists(String path) {
        File file = new File(path);
        return isFileExists(file);
    }

    /**
     * 检测文件是否存在，不存在则创建
     * @param path
     */
    public static void checkAndCreate(String path) {
        File file = new File(path);
        try {
            // 判断文件是否存在，不存在则创建
            if (!isFileExists(file)) {
                file.mkdirs();
            }
        } catch (Exception e) {
            throw  new RuntimeException("目录创建失败");
        }
    }
}
