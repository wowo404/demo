package org.liu.filereader;

import java.io.*;

/**
 * https://blog.csdn.net/qq_40100414/article/details/120179117
 *
 * @Author lzs
 * @Date 2023/9/18 15:51
 **/
public class RandomAccessFileTest {
    /**
     * 向指定文件的指定位置插入指定的内容
     *
     * @param fileName      指定文件名
     * @param pos           指定文件的指定位置
     * @param insertContent 指定文件的指定位置要插入的指定内容
     */
    public static void insert(String fileName, long pos,
                              String insertContent) throws IOException {
        RandomAccessFile raf = null;
        //创建一个临时文件来保存插入点后的数据
        File tmp = File.createTempFile("tmp", null);
        FileOutputStream tmpOut = null;
        FileInputStream tmpIn = null;
        tmp.deleteOnExit();
        try {
            raf = new RandomAccessFile(fileName, "rw");
            tmpOut = new FileOutputStream(tmp);
            tmpIn = new FileInputStream(tmp);
            raf.seek(pos);
            //--------下面代码将插入点后的内容读入临时文件中保存---------
            byte[] bbuf = new byte[64];
            //用于保存实际读取的字节数
            int hasRead = 0;
            //使用循环方式读取插入点后的数据
            while ((hasRead = raf.read(bbuf)) > 0) {
                //将读取的数据写入临时文件
                tmpOut.write(bbuf, 0, hasRead);
            }
            //----------下面代码插入内容----------
            //把文件记录指针重新定位到pos位置
            raf.seek(pos);
            //追加需要插入的内容
            raf.write(insertContent.getBytes());
            //追加临时文件中的内容
            while ((hasRead = tmpIn.read(bbuf)) > 0) {
                raf.write(bbuf, 0, hasRead);
            }
        } finally {
            if (raf != null) {
                raf.close();
            }
        }
    }
}
