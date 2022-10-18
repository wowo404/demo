package org.liu.encryptdecrypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by liuzhsh on 2017/11/9.
 */
public class MD5CheckFileSign {

    /**
     * 测试了md5加密2G的大文件，使用了nio的方式
     */
    public static String encrypt() {
        String hashType = "MD5";
        FileInputStream fStream = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(hashType);
            fStream = new FileInputStream("K:\\Games\\World of Warcraft\\Data\\common.MPQ");
            FileChannel fChannel = fStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);
            long s = System.currentTimeMillis();
            for (int count = fChannel.read(buffer); count != -1; count = fChannel.read(buffer)
            ) {
                buffer.flip();
                md5.update(buffer);
                if (!buffer.hasRemaining()) {
                    //System.out.println("count:"+count);
                    buffer.clear();
                }
            }
            s = System.currentTimeMillis() - s;
            String encodeString = Base64.getEncoder().encodeToString(md5.digest());
            System.out.println("耗时：" + s + "," + encodeString);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fStream != null)
                    fStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
