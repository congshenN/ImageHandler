package cn.sc.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cn.sc.util.ImageUtil;
import cn.sc.util.readConfigFile2ArrList;
import cn.sc.util.readConfigFile2ArrList.ColorModeEnum;


public class LogoHandler {
	
//	public static ArrayList<Color> arr=new ArrayList<Color>();
	
	public static String debugMessage="";

public static void main(String[] args) {
		try{
			System.out.println("sc");
			
			readConfigFile2ArrList readConfigFile2ArrList = new readConfigFile2ArrList();
			
			System.out.println(readConfigFile2ArrList.InputFileSrc);
			System.out.println(readConfigFile2ArrList.OutputFileSrc);
			System.out.println(readConfigFile2ArrList.ColorMode);
			System.out.println(readConfigFile2ArrList.ColorNumber);
			System.out.println(readConfigFile2ArrList.pattern);
			System.out.println(readConfigFile2ArrList.ClearMottled);
			System.out.println(readConfigFile2ArrList.ColorRGB);

  
			
			System.out.println("--------------------");
			LogoHandler.imageHandle(readConfigFile2ArrList.InputFileSrc,
					readConfigFile2ArrList.OutputFileSrc,
					readConfigFile2ArrList.ColorMode,
					readConfigFile2ArrList.ColorNumber,
					readConfigFile2ArrList.pattern,
					readConfigFile2ArrList.ClearMottled,
					readConfigFile2ArrList.ColorRGB
			);	
		}
		catch(Exception e){
			System.out.println("FAIL："+e);
		}
		wait1();
	}
  
  public static void wait1(){
		System.out.println("end");
		Scanner s=new Scanner(System.in);
		if(s.hasNextLine())
			s.nextLine();
		s.close();
	}

/*
	 * 	in 源图片
	 * 	out 结果
	 *  colorMode 	CM_PALETTE ：arrList
	 *				CM_MAX：	colorNumber
	 *
	 *  colorNumber colorMode.CM_MAX
	 *  arrList colorMode.CM_PALETTE
	 *  ClearMottled 清除 <(rang^2)/2  rang:3  clear 1-3
	 *  colorRGB	colorMode.CM_MAX 选取的调色板颜色差值 默认0
	 */
	public static void imageHandle(String in,
			String out,
			readConfigFile2ArrList.ColorModeEnum colorMode,
			int colorNumber,
			ArrayList<Color> arrList,
			boolean ClearMottled,
			int colorRGB
		){
		ArrayList<Color> arr = null ;
		BufferedImage read = null;
		System.out.println("解析图片");
		try {
			System.out.println("读取文件"+in);
			read = ImageIO.read(new File(in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("读取图片位图格式转化1");
		read = ImageUtil.toBMP(read);
		
		if(colorMode == ColorModeEnum.CM_MAX)
			arr = ImageUtil.GetColorForImage(read,colorNumber,colorRGB);
		else if(colorMode == ColorModeEnum.CM_PALETTE)
			arr=arrList;
		else{
			System.err.println("error");
			return ;
		}
		BufferedImage write4 = ImageUtil.toWhiteImage(read,arr);

		if(ClearMottled){
			write4 = ImageUtil.DelZS(write4,arr);
		}
		System.out.println("读取图片位图格式转化1");
		BufferedImage write3 = ImageUtil.toBMP(write4);
		try {
			System.out.println("写入硬盘");
			ImageIO.write(write3, "bmp", new File(out));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void UnCall(){
		/*
//		arr.add(new Color(0, 0, 0));
//		arr.add(new Color(255,255,255));
//		arr.add(new Color(200, 0, 0));
		
//		arr.add(new Color(255,255,255));
//		arr.add(new Color(0, 91, 172));
		
//		arr.add(new Color(255,255,255));
//		arr.add(new Color(174,0,4));
//		arr.add(new Color(89,87,87));
		
		arr.add(new Color(255,255,255));
		arr.add(new Color(35,24,21));
		arr.add(new Color(255,0,23));
		
		
		BufferedImage read = ImageIO.read(new File("E:\\文档资料\\ImageTest\\logo_test3.bmp"));
		
		read=ImageUtil.JunZhiLvBo(read);
		ImageIO.write(read, "bmp", new File("E:\\文档资料\\ImageTest\\logo_jun.bmp"));
		
		
		BufferedImage write3 = ImageUtil.toWhiteImage(read,arr);
//		ImageIO.write(write3, "png", new File("E:\\文档资料\\ImageTest\\logo_white_test.png"));

		BufferedImage write4 = ImageUtil.DelZS(write3,arr);
		ImageIO.write(write4, "png", new File("E:\\文档资料\\ImageTest\\logo_ZS_test.png"));
		*/
		
		
		
//		arr.add(new Color(0, 0, 0));
//		arr.add(new Color(255, 255, 255));
		
		
//		BufferedImage read = ImageIO.read(new File("E:\\文档资料\\ImageTest\\SCUTUM.bmp"));
//		read=ImageUtil.toBMP(read);
//		ImageIO.write(read, "bmp", new File("E:\\文档资料\\ImageTest\\SCUTUM_test.bmp"));

//		arr.add(new Color(255, 255, 255));
//		arr.add(new Color(35, 31, 32));
//		arr.add(new Color(245, 245, 245));
//		arr.add(new Color(135, 133, 134));
//		arr.add(new Color(181, 180, 180));
//		arr.add(new Color(197, 196, 196));
//		arr.add(new Color(178, 176, 177));
//		arr.add(new Color(59, 55, 56));
		
//		BufferedImage read = ImageIO.read(new File("E:\\文档资料\\ImageTest\\SCUTUM_test.bmp"));
//		BufferedImage write4 = ImageUtil.toWhiteImage(read,arr);
//		//ImageIO.write(write4, "bmp", new File("E:\\文档资料\\ImageTest\\logo_ZS_test.bmp"));
//		
//		BufferedImage write3 = ImageUtil.toBMP(write4);
//		ImageIO.write(write3, "bmp", new File("E:\\文档资料\\ImageTest\\SCUTUM_bmp.bmp"));

//		BufferedImage write5 = ImageUtil.DelZS(write3,arr);
//		ImageIO.write(write5, "png", new File("E:\\文档资料\\ImageTest\\logo_ZS_test.png"));
		
//		write4 = ImageUtil.toBMP(write5);
//		ImageIO.write(write4, "bmp", new File("E:\\文档资料\\ImageTest\\logo_ZS_test.bmp"));
		
		
//		arr.add(new Color(240, 84, 35));
//		arr.add(new Color(255, 255, 255));
//		BufferedImage read = ImageIO.read(new File("E:\\文档资料\\ImageTest\\CITA.bmp"));
//		
//		BufferedImage write3 = ImageUtil.toWhiteImage(read,arr);
////		ImageIO.write(write3, "png", new File("E:\\文档资料\\ImageTest\\logo_white_test.png"));
//
//		BufferedImage write5 = ImageUtil.DelZS(write3,arr);
//		ImageIO.write(write5, "png", new File("E:\\文档资料\\ImageTest\\logo_ZS_test.png"));
		
		
//		BufferedImage read = ImageIO.read(new File("E:\\文档资料\\ImageTest\\logo_ZS_test.bmp"));
//		read=ImageUtil.JunZhiLvBo(read);
//		ImageIO.write(read, "bmp", new File("E:\\文档资料\\ImageTest\\logo_jun.bmp"));
		
		
//		BufferedImage read = ImageIO.read(new File("E:\\文档资料\\ImageTest\\logo_ZS_test.png"));
//		BufferedImage write4 = ImageUtil.toBMP(read);
//		ImageIO.write(write4, "bmp", new File("E:\\文档资料\\ImageTest\\logo_final.bmp"));
		
	}
	
}
