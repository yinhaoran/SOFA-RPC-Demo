/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ZxingTests.java  
 * Package Name:com.example.zxing 
 * Date:2019年3月28日上午9:41:51  
 * Copyright (c) 2019,  
 *  
*/

package com.example.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * ClassName:ZxingTests Date: 2019年3月28日 上午9:41:51
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ZxingTests {
	@Test
	public void decodeTest() throws Exception {
		String filePath = "D://";
		String fileName = "zxing.png";
		JSONObject json = new JSONObject();
		json.put("zxing", "https://github.com/zxing/zxing/tree/zxing-3.0.0/javase/src/main/java/com/google/zxing");
		json.put("author", "shihy");
		/**
		 * 加密内容
		 */
		String content = json.toJSONString();
		/**
		 * 图像高度
		 */
		int width = 200;
		/**
		 * 图像高度
		 */
		int height = 200;
		/**
		 * 图像类型
		 */
		String format = "png";//
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		/**
		 * 生成矩阵
		 */
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		/**
		 * 输出图像
		 */
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		System.out.println("输出成功.");

	}

	@Test
	public void encodeTest() {
		String filePath = "D://zxing.png";
		BufferedImage image;
		try {
			image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			JSONObject content = JSONObject.parseObject(result.getText());
			System.out.println("图片中内容：	");
			System.out.println("author：	" + content.getString("author"));
			System.out.println("zxing：	" + content.getString("zxing"));
			System.out.println("图片中格式：	");
			System.out.println("encode：	" + result.getBarcodeFormat());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
