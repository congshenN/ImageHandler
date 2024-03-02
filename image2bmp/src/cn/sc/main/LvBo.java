package cn.sc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import cn.sc.util.ImageUtil;
import cn.sc.util.readConfigFile2ArrList;

public class LvBo {
	public static void main(String[] args) throws Exception {
		double kernel[][]= new double[][]{
			{1.0,1.0,1.0},
			{1.0,-8.0,1.0},
			{1.0,1.0,1.0}
			
		};
		
//		double kernel[][]= new double[][]{
//			{0,1.0,0},
//			{1.0,-4.0,1.0},
//			{0,1.0,0}
//			
//		};
	
//		double kernel[][]= new double[][]{
//			{-1.0,-2.0,-1.0},
//			{-2.0,13.0,-2.0},
//			{-1.0,-2.0,-1.0}
//		};
		BufferedImage read = ImageIO.read(new File("D:\\文档资料\\myjar\\b146f9c9ff024c0843df405d8071514f_95f5ab80d4b17e565e313cb4bcb4ad7e_8.bmp"));
		
		BufferedImage applyFilter = ImageUtil.applyFilter(read, kernel);
		
		ImageIO.write(applyFilter, "bmp", new File("D:\\文档资料\\myjar\\DORANI6.bmp"));
		
//		Test.imageHandle("D:\\文档资料\\myjar\\DORANI_Test.bmp",
//				"D:\\文档资料\\myjar\\DORANI_Test1.bmp",
//				readConfigFile2ArrList.ColorModeEnum.CM_MAX,
//				2,
//				new ArrayList<Color>(),
//				true,
//				0);
//		
	}
}

