package cn.sc.main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.Utilities;

import cn.sc.util.ImageUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		ImageIO.write(ImageUtil.toBMP(ImageUtil.setSize(ImageIO.read(new File("D:\\文档资料\\myjar\\compurion2.bmp")), 720, 576)), "bmp", new File("D:\\文档资料\\myjar\\compurion3.bmp"));
	}
}

