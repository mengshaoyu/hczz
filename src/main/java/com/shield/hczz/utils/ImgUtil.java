package com.shield.hczz.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

/**
 * 图片操作工具类
 */
public class ImgUtil {


    /**
     * 读取图片的base64编码字符串
     * 
     * @param filename
     *            原图片文件路径
     * @return base64字符串
     */
    public static String encodeToString(String filename) {
        String result = null;
        try {
            byte[] data = null;
            // 读取图片字节数组
            InputStream in = new FileInputStream(filename);
            data = new byte[in.available()];
            in.read(data);
            in.close();
            // 对字节数组Base64编码
            result = encodeToString(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * 图片base64编码转图片
     * 
     * @param base64Str
     *            base64字符串
     * @param filePath
     *            生成的图片存储地址
     * @return 0成功 1失败
     */
    public static int decode(String base64Str, String filePath) {
        // Base64解码
        byte[] bytes = decodeToBytes(base64Str);
        return decode(bytes, filePath);
    }

    /**
     * 图片base64编码转图片(该方法慎用推荐使用decode方法)
     * 
     * @param base64Str
     *            base64字符串
     * @param filePath
     *            生成的图片存储地址
     * @return 0成功 1失败
     */
    public static int decode2(String base64Str, String filePath) {
        try {
            OutputStream out = null;
            try {
                // Base64解码
                byte[] bytes = decodeToBytes(base64Str);
                decode(bytes, filePath);
                for (int i = 0; i < bytes.length; ++i) {
                    if (bytes[i] < 0) {// 调整异常数据
                        bytes[i] += 256;
                    }
                }
                out = new FileOutputStream(filePath);
                out.write(bytes);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 读取图片的二进制字节数组
     * 
     * @param filePath
     *            读取图片的文件路径
     * @return bytes 图片的二进制字节数组
     */
    public static byte[] encodeToBytes(String filePath) {
        BufferedInputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            int bufSize =1024;
            byte[] data = new byte[bufSize];
            in = new BufferedInputStream(new FileInputStream(filePath));
            baos = new ByteArrayOutputStream();
            int len =0;
            while(-1!=(len=in.read(data,0,bufSize))){
                baos.write(data, 0, len);
            }
//          File file = new File(filePath);
//          BufferedImage img = ImageIO.read(file);
//          ImageIO.write(img, "jpg", baos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    /**
     * 将二进制字节数组转为图片
     * 
     * @param bytes
     *            二进制字节数组
     * @param filePath
     *            生成的图片存储地址
     * @return 0成功 1失败
     */
    public static int decode(byte[] bytes, String filePath) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return decode(bais, filePath);
    }

    /**
     * 将图片流转为图片
     * 
     * @param bais
     * @param filePath
     *            图片文件的存储路径
     * @return 0成功 1失败
     */
    public static int decode(ByteArrayInputStream bais, String filePath) {
        try {
            BufferedImage bi = ImageIO.read(bais);
            File ff = new File(filePath);
            File p = ff.getParentFile();
            if (!p.exists())
                p.mkdirs();
            ImageIO.write(bi, "jpg", ff);
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
        return 0;
    }

    /**
     * 图片截取
     * 
     * @param filePath
     *            待截取图片文件路径
     * @param x
     *            截取起始点x轴坐标
     * @param y
     *            截取起始点y轴坐标
     * @param w
     *            截取宽度
     * @param h
     *            截取高度
     * @param toPath
     *            截图存储路径
     * @return 0成功 1失败
     */
    public static int cut(String filePath, int x, int y, int w, int h, String toPath) {
        try {
            BufferedImage bufImg = ImageIO.read(new File(filePath));
            bufImg = bufImg.getSubimage(x, y, w, h);
            ImageIO.write(bufImg, "jpg", new File(toPath));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    /**
     * base64字符串转byte数组
     * @param base64Str
     * @return
     */
    public static byte[] decodeToBytes(String base64Str){
        return Base64.decodeBase64(base64Str);
    }
    
    /**
     * byte数组转base64字符串
     * @param bytes
     * @return
     */
    public static String encodeToString(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }
    
    /**
     * 获取图片的宽度和高度
     * @param img
     * @return 宽度,高度
     */
    public static String getWidthAndLength(File img){
        Image image = null;
        try {
            image = ImageIO.read(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(image.getWidth(null) == -1)
            return "0,0";
        else{
            return image.getWidth(null)+","+image.getHeight(null);
        }
    }
    
    /**
     * 获取图片的宽度和高度
     * @param imgPath
     * @return 宽度,高度
     */
    public static String getWidthAndLength(String imgPath){
        File img = new File(imgPath);   
        return getWidthAndLength(img);
    }

}
