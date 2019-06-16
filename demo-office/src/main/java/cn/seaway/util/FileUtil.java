/*
 * Project: POI
 * 
 * File Created at 2014年3月6日 下午8:49:21
 * 
 * Copyright 2012 seaway.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Seaway Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with seaway.com.
 */
package cn.seaway.util;

import java.io.File;

/**
 * 文件工具类
 * <br>----------------------------------------------------变更记录--------------------------------------------------
 * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
 * <br> 0     | 2014年3月6日 下午8:49:21  	|  刘章盛     | 创建  
 */
public class FileUtil {
    
    /**
     * 
     * 如果该文件路径不存在则创建
     * @param path
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月6日 下午8:55:54  	|  刘章盛     | 创建
     */
    public static void createDir(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    /**
     * 
     * 删除一个文件
     * @param filePath 文件路径
     * @return
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月8日 上午11:42:41  	|  刘章盛     | 创建
     */
    public static Boolean deleteFile(String filePath){
        boolean flag = false;
        File file = new File(filePath);
        if(file.isFile() && file.exists()){
            file.delete();
            flag = true;
        }
        return flag;
    }
    /**
     * 
     * 删除目录及目录下的所有文件
     * @param directoryPath
     * <br>----------------------------------------------------变更记录--------------------------------------------------
     * <br> 序号      |           时间                        	|   作者      |                          描述                                                         
     * <br> 0     | 2014年3月8日 下午12:59:11  	|  刘章盛     | 创建
     */
    public static void deleteDirectory(String directoryPath){
        if(!directoryPath.endsWith(File.separator)){
            directoryPath = directoryPath + File.separator;
        }
        File file = new File(directoryPath);
        if(!file.exists()){
            return ;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File file2 : files) {
                deleteDirectory(file2.getAbsolutePath());
            }
        }
        
        file.delete();
    }

}
