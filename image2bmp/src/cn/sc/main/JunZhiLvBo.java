package cn.sc.main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import cn.sc.util.ImageUtil;

public class JunZhiLvBo {
	public static void main(String[] args) throws Exception {
		
		BufferedImage read = ImageUtil.JunZhiLvBo(ImageIO.read(new File("D:\\文档资料\\myjar\\DORANI_Test1.bmp")));
		ImageIO.write(read, "bmp", new File("D:\\文档资料\\myjar\\DORANI4.bmp"));
		
		
	}
}
