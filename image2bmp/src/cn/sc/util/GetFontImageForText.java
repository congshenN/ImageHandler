package cn.sc.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GetFontImageForText {
	public static int t =0;
	public static void main(String[] args) throws IOException {
//		String Test= "电影";
//		Font font =new Font("思源黑体", Font.PLAIN, 12);
//        ImageIO.write(Test(font,Test), "bmp", new File("Test.jpg"));
		
		BufferedImage read = ImageIO.read(new File("Test.bmp"));
		for (int i = 0; i < args.length; i++) {
			
		}
		
	}
	
//	public static BufferedImage Test(Font font,String text) throws IOException {
//        // 获取文本的宽度和高度，以便创建适当大小的图片
//        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR/*TYPE_BYTE_BINARY*/);
//        Graphics g = image.getGraphics();
//        g.setFont(font);
//        int width = g.getFontMetrics().stringWidth(text);
//        int height = g.getFontMetrics().getHeight();
//        g.dispose();
//
//        // 创建具有适当大小的图片
//        image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
//        g = image.getGraphics();
//        g.setFont(font);
//
//        // 在图片上绘制文本
//        g.drawString(text, 0, g.getFontMetrics().getAscent());
//
//        g.dispose();
//        
//        BufferedImage image1 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
//        for (int i = 0; i < height; i++) {
//        	for (int j = 0; j < width; j++) {
//        		int rgb = image.getRGB(j, i);
//        		new 
//    		}
//		}
//        
//        return image1;
//    }
}
