///*
// * Project: mybatis-demo
// *
// * File Created at 2014年3月4日 下午2:02:08
// *
// * Copyright 2012 seaway.com Corporation Limited.
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of
// * Seaway Company. ("Confidential Information").  You shall not
// * disclose such Confidential Information and shall use it only in
// * accordance with the terms of the license agreement you entered into
// * with seaway.com.
// */
//package org.liu.demo.office;
//
//import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
//import jp.ne.so_net.ga2.no_ji.jcom.JComException;
//import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;
//
///**
// * word to pdf
// * <br>----------------------------------------------------变更记录--------------------------------------------------
// * <br> 序号      |           时间                        	|   作者      |                          描述
// * <br> 0     | 2014年3月4日 下午2:02:08  	|  刘章盛     | 创建
// */
//public class JcomWordToPdf {
//
//    private static final String docApp = "PDFMakerAPI.PDFMakerApp";
//
//    public void createPDF(String docApplication, String officePath, String pdfPath) {
//        ReleaseManager rm = new ReleaseManager();
//        IDispatch docApp = null;
//        try {
//            docApp = new IDispatch(rm, docApplication);
//            docApp.method("CreatePDF", new Object[] { officePath, pdfPath });
//        } catch (JComException e) {
//            e.printStackTrace();
//        } finally {
//            docApp = null;
//            rm.release();
//            rm = null;
//        }
//    }
//
//    /**
//     * word to pdf
//     * @param args
//     * <br>----------------------------------------------------变更记录--------------------------------------------------
//     * <br> 序号      |           时间                        	|   作者      |                          描述
//     * <br> 0     | 2014年3月4日 下午2:02:08  	|  刘章盛     | 创建
//     */
//    public static void main(String[] args) {
//
//        JcomWordToPdf trans = new JcomWordToPdf();
//        trans.createPDF(docApp, "d:\\【1-3】【文档】易通贷抵押贷借款协议（每月等额还款）140218.docx",
//                "d:\\【1-3】【文档】易通贷抵押贷借款协议（每月等额还款）140217.pdf");
//
//    }
//
//}
