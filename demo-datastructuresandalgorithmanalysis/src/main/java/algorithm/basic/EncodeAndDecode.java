package algorithm.basic;

import java.util.Base64;

/**
 * 编码和解码
 *
 * @author lzs
 * @Date 2024/9/20 14:51
 **/
public class EncodeAndDecode {

    public static void main(String[] args) {
        byte[] data = "Hello World!".getBytes();
        String encode = Base64.getEncoder().encodeToString(data);
        System.out.println(encode);
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode));
    }

}
