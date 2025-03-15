package org.demo;/*
 * Project: POI
 *
 * File Created at 2014年4月2日 下午2:22:11
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

import java.io.IOException;

public class TestExec {

    public static void main(String[] args) {

        String command = "java -jar \\opt\\jodconverter-cli-2.2.2.jar 3.docx one.pdf";
        Process pro = null;
        try {
            System.out.println("aaa");
            pro = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != pro) {
                pro.destroy();
            }
        }

    }

}
