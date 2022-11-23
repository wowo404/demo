package org.liu.filereader;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class FileReader {

    public static final String SRC = "src/main/resources/pdf/contract_tpl.pdf";

    public static void main(String[] args) throws IOException, URISyntaxException {
        FileReader fr = new FileReader();
//		fr.filter();
//		fr.read();
//		fr.read2();
//		fr.saveFile();
//		fr.getFileFromUrl();

        fr.readImageFromUrl();
    }

    public byte[] readFileFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        URLConnection con = url.openConnection();

        // 得到URL的输入流
        InputStream input = con.getInputStream();
        int length = input.available();
        // 设置数据缓冲
        byte[] bs = new byte[length];
        // 读取到的数据长度
        input.read(bs, 0, length);
        return bs;
    }

    public void readImageFromUrl() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        BufferedImage bi = ImageIO.read(new URL("https://factory-img.jxminxun.com/FACTORY/132/20210814ce8be155-2d75-4e35-9ac5-d2ac0313b13e.jpg"));
        ImageIO.write(bi, "jpg", baos);

        byte[] bytes = baos.toByteArray();
        String base64String = Base64.getEncoder().encodeToString(bytes);
        System.out.println(base64String);
    }

    public void filter() throws IOException {
        String path = "D:\\projects\\manufacture\\";
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        String fileName = "manufacture.properties";
        File file = new File(path + File.separator + fileName);
        if (!file.exists()) {
            boolean newFile = file.createNewFile();
            System.out.println(newFile);
        }
        FilenameFilter filter = (dir1, name) -> {
            if (name.startsWith("manufacture")) {
                return true;
            }
            return false;
        };
        File[] files = dir.listFiles(filter);
        for (File file1 : files) {
            System.out.println(file1.getName());
        }
    }

    public void read() throws IOException {
        InputStream is = new FileInputStream(new File("D:\\work\\workspace-idea\\demo\\demo-test\\src\\main\\resources\\test.el"));
        byte[] buff = IoUtil.readBytes(is);
        String content = new String(buff, "UTF-8");
        System.out.println(content);
    }

    public void read2() throws IOException {
        InputStream is = new FileInputStream(new File("D:\\work\\workspace-idea\\demo\\demo-test\\src\\main\\resources\\test.el"));
        byte[] buff = new byte[is.available()];
        int offset = 0;
        int read;
        while ((read = is.read(buff, offset, 512)) != -1) {
            offset += read;
        }
        String content = new String(buff, "UTF-8");
        System.out.println(content);
    }

    public static String bufferRead(String path) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(path));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public void fileName() {
        File file = new File(SRC);
        String name = file.getName().substring(0, file.getName().lastIndexOf("."));
        System.out.println(name);
    }

    public void saveFile() throws IOException {
        //target
//        FileOutputStream fos = new FileOutputStream(new File("/Users/liuzhangsheng/Downloads/e.txt"));
        //source
        File source = new File("/Users/liuzhangsheng/Downloads/a.txt");
        File dest = new File("/Users/liuzhangsheng/Downloads/f.txt");
        FileUtil.copy(source, dest, true);
        InputStream is = new FileInputStream(source);
//        FileUtils.copyInputStreamToFile(is, dest);
//        byte[] buffer = IOUtils.toByteArray(is, is.available());
//        fos.write(buffer);
        is.close();
//        fos.close();
    }

    public void getFileFromUrl() throws URISyntaxException, IOException {
        File file = new File(new URI("https://manman-note.oss-cn-beijing.aliyuncs.com/note-api/static/%E5%B0%8F%E6%9B%BC%E5%A4%B4%E5%83%8F.png"));
        File dest = new File("D:\\funny\\downloads\\f.txt");
        FileUtil.copy(file, dest, true);
        InputStream is = new FileInputStream(file);
//        FileUtils.copyInputStreamToFile(is, dest);
        is.close();
    }

}
