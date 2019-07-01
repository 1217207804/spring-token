package com.joe.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Recodeutil {
	
	public static void createRrCode(String contents, int width, int height, HttpServletResponse response) {
		
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//容错级别最高
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置字符编码
		hints.put(EncodeHintType.MARGIN, 1);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE,width, height, hints);
			BufferedImage image = toBufferedImage(bitMatrix);
			//转换成png格式的IO流
			ImageIO.write(image, "png", response.getOutputStream());
			// byte[] bytes = out.toByteArray();
			// // 2、将字节数组转为二进制
			// BASE64Encoder encoder = new BASE64Encoder();
			// binary = encoder.encodeBuffer(bytes).trim();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for(int x=0;x<width;x++) {
			for(int y=0;y<height;y++) {
				image.setRGB(x, y, matrix.get(x,y)? 0xFF000000: 0xFFFFFFFF);
			
			}
		}
		return image;
	}
	
}
