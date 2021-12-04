package org.liu.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

/**
 * @Author lzs
 * @Date 2021/11/17 10:07
 **/
public class TestZXing2 {

    private static final int MULTIPLE = 4;

    public static void main(String[] args) {
        try {
            //不带logo
//            BufferedImage bi = createImage("http://www.wengqiqi.com/", null, 120, 120, false);
//            saveImg(bi, "D://download//saved.png");
            BufferedImage buff = createImage("http://www.wengqiqi.com/", "D://download//logo.png", 800, 800, true);
            saveImg(buff, "D://download//saved_logo.png");
//            InputStream in = changeMerchantSeatQrcodeImage(bi, "d://ewm.png");
//            saveFile(in, "D://download//new_ewm.png");
        } catch (Exception e) {
        }
    }


    private static BufferedImage createImage(String content, String logoImgPath, int width, int height, boolean needCompress) throws WriterException, IOException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1);//设置白边
        // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //循环遍历每一个像素点
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        // 没有logo
        if (logoImgPath == null || "".equals(logoImgPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, logoImgPath, needCompress);
        return image;
    }


    private static void insertImage(BufferedImage source, String logoImgPath, boolean needCompress) throws IOException {
        File file = new File(logoImgPath);
        if (!file.exists()) {
            return;
        }
        Image logoImage = ImageIO.read(new File(logoImgPath));
        int width = logoImage.getWidth(null);//logo原始宽度
        int height = logoImage.getHeight(null);//logo原始高度
        int scaleWidth = width, scaleHeight = height;
        //处理logo
        if (needCompress) {
            //比二维码要小至少4倍
            if (width > source.getWidth() / MULTIPLE) {
                scaleWidth = source.getWidth() / MULTIPLE;
            }
            if (height > source.getHeight() / MULTIPLE) {
                scaleHeight = source.getHeight() / MULTIPLE;
            }
            //缩放后的logo图
            Image scaleLogoImage = logoImage.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(scaleWidth, scaleHeight, BufferedImage.TYPE_INT_RGB);
            Graphics gMaker = tag.getGraphics();
            gMaker.drawImage(scaleLogoImage, 0, 0, null); // 绘制缩小后的图
            gMaker.dispose();
            logoImage = scaleLogoImage;
        }
//        BufferedImage whiteImage = generateWhiteImage(scaleWidth, scaleHeight);
        // 在中心位置插入logo
        Graphics2D graph = source.createGraphics();
        int x = (source.getWidth() - scaleWidth) / 2;
        int y = (source.getHeight() - scaleHeight) / 2;
        graph.setBackground(Color.WHITE);
        graph.setColor(Color.GRAY);
        graph.clearRect(x, y, scaleWidth, scaleHeight);
//        graph.drawImage(whiteImage, x, y, scaleWidth, scaleHeight, null);
        graph.drawImage(logoImage, x, y, scaleWidth, scaleHeight, null);
        Shape shape = new RoundRectangle2D.Float(x, y, scaleWidth, scaleHeight, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    private static BufferedImage generateWhiteImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = image.createGraphics();
        graphic.setColor(Color.white);
        graphic.fillRect(0, 0, width, height);
        return image;
    }


    public static InputStream changeMerchantSeatQrcodeImage(BufferedImage zxingImage, String backgroundPath) {
        InputStream imagein = null;
        ImageOutputStream imOut = null;
        try {
            imagein = new FileInputStream(backgroundPath);
            BufferedImage image = ImageIO.read(imagein);
            Graphics g = image.getGraphics();
            // 生成的二维码设置的较小，这里等比放大了二维码。也可在zxing中设置二维码生成的大小
            g.drawImage(zxingImage, 40, 25, zxingImage.getWidth(), zxingImage.getHeight(), null);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, "png", imOut);
            InputStream is = new ByteArrayInputStream(bs.toByteArray());
            return is;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                imagein.close();
                imOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void saveFile(InputStream is, String fileName) throws IOException {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        in = new BufferedInputStream(is);
        out = new BufferedOutputStream(new FileOutputStream(fileName));
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
    }

    public static void saveImg(BufferedImage bi, String backgroundPath) {
        try {
            File outputfile = new File(backgroundPath);
            ImageIO.write(bi, "png", outputfile);
        } catch (Exception e) {
        }
    }
}
