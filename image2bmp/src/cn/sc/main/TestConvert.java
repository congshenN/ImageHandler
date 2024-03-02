package cn.sc.main;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class TestConvert {
	private final static String src= "C:\\Users\\Administrator\\Desktop\\Test\\";
	
	
	public static void main(String[] args) throws Exception {
		//BufferedImage read = ImageIO.read(new File(src+"Test.jpg"));
		
		//BufferedImage desaturate = desaturate(read);
		
		//ImageIO.write(desaturate, "bmp", new File(src+"out.bmp"));
		
		
		double[] rgb2hsl = RGB2HSL(new Color(23,31,255));
		for (double d : rgb2hsl) {
			System.out.println(d);
		}
		
	
	
	
	public static double[] RGB2HSL(Color AColor){
		double[] result =new double[3];
		 double H=0;
		 double S=0;
		 double L=0;
		double R,G,B,Max,Min,del_R,del_G,del_B,del_Max;
	    R = (AColor.getRed()) / 255.0;       //Where RGB values = 0 ÷ 255
	    G = (AColor.getGreen()) / 255.0;
	    B = (AColor.getBlue()) / 255.0;

	    Min = Math.min(R, Math.min(G, B));    //Min. value of RGB
	    Max = Math.max(R, Math.max(G, B));    //Max. value of RGB
	    del_Max = Max - Min;        //Delta RGB value

	    L = (Max + Min) / 2.0;

	    if (del_Max == 0)           //This is a gray, no chroma...
	    {
	        //H = 2.0/3.0;          //Windows下S值为0时，H值始终为160（2/3*240）
	        H = 0;                  //HSL results = 0 ÷ 1
	        S = 0;
	    }
	    else                        //Chromatic data...
	    {
	        if (L < 0.5) S = del_Max / (Max + Min);
	        else         S = del_Max / (2 - Max - Min);

	        del_R = (((Max - R) / 6.0) + (del_Max / 2.0)) / del_Max;
	        del_G = (((Max - G) / 6.0) + (del_Max / 2.0)) / del_Max;
	        del_B = (((Max - B) / 6.0) + (del_Max / 2.0)) / del_Max;

	        if      (R == Max) H = del_B - del_G;
	        else if (G == Max) H = (1.0 / 3.0) + del_R - del_B;
	        else if (B == Max) H = (2.0 / 3.0) + del_G - del_R;

	        if (H < 0)  H += 1;
	        if (H > 1)  H -= 1;
	    }
	    result[0]=H;
	    result[1]=S;
	    result[2]=L;
		return result;
	} 
	
	public static BufferedImage desaturate(BufferedImage source) {
	    ColorConvertOp colorConvert = 
	        new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	    colorConvert.filter(source, source);

	    return source;
	}
}

