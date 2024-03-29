package cn.sc.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil {
	
	public static BufferedImage getBIForFile(String ImageFileSrc) throws IOException{
		
		File f = new File(ImageFileSrc);

	    //Find a suitable ImageReader
	    Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
	    ImageReader reader = null;
	    while(readers.hasNext()) {
	        reader = (ImageReader)readers.next();
	        if(reader.canReadRaster()) {
	            break;
	        }
	    }

	    //Stream the image file (the original CMYK image)
	    ImageInputStream input =   ImageIO.createImageInputStream(f); 
	    reader.setInput(input); 

	    //Read the image raster
	    Raster raster = reader.readRaster(0, null); 

	    //Create a new RGB image
	    BufferedImage bi = new BufferedImage(raster.getWidth(), raster.getHeight(), 
	    BufferedImage.TYPE_4BYTE_ABGR); 

	    //Fill the new image with the old raster
	    bi.getRaster().setRect(raster);
		
	    return bi;
	}
	
	// BufferedImage getType()
	// TYPE_BYTE_BINARY (0,8]
	// TYPE_BYTE_INDEXED (8,256]
	// TYPE_3BYTE_BGR (256,256^3]
	public static ArrayList<Color> GetColorForImage(BufferedImage bi ,int num,int colorRGB) {

		int item = num;
		ArrayList<Color> result =new ArrayList<Color>();

		HashMap<Color, Integer> hashMap = new HashMap<Color, Integer>();
		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				int rgb = bi.getRGB(j, i);
				if (!hashMap.containsKey(new Color(rgb))) {
					hashMap.put(new Color(rgb), 1);
				}else{
					hashMap.put(new Color(rgb), hashMap.get(new Color(rgb))+1);
				}
			}
		}
		System.out.println("hashMap.size():"+hashMap.size());
		if(hashMap.size()<=num){
			item = hashMap.size();
		}
		while(item!=0 && hashMap.size()!=0)
		{
			int max = 0;
			Color maxColor =null;
			ArrayList<Color> clearColor =new ArrayList<Color>();
			for (Color color : hashMap.keySet()) {
				if(hashMap.get(color)>max/* && checkColorRGB(result,color,colorRGB)*//*(!result.contains(color))*/)
				{
					max = hashMap.get(color);
					maxColor=color;
				}
			}
			
			if(maxColor!=null){
				result.add(maxColor);
				item -- ;
			}
			
			for (Color color : hashMap.keySet()) {
				if(checkColorRGB(result,color,colorRGB))
				{
					clearColor.add(color);
				}
			}
			for (Color color : clearColor) {
				hashMap.remove(color);
			}
			System.out.println("hashMap.size():"+hashMap.size());
			System.out.println("clearColor.size():"+clearColor.size());
		}
		
		for (Color color : result) {
			System.out.println(color);
		}
		if(hashMap.size()<=num){
			for(int i=result.size();i<num;i++){
				result.add(new Color(128, 128, 128));
			}
		}
		
		return result;
	}
	
	private static boolean checkColorRGB(ArrayList<Color> result, Color color, int colorRGB) {
		// TODO Auto-generated method stub
		for (Color color2 : result) {
			if(Math.abs(color2.getRed() - color.getRed())<=colorRGB && 
					Math.abs(color2.getGreen() - color.getGreen())<=colorRGB &&
					Math.abs(color2.getBlue() - color.getBlue())<=colorRGB)
				return true;
		}
		return false;
	}

	public static BufferedImage toBMP(BufferedImage bi) {
		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);
		
		HashMap<Color, Integer> hashMap = new HashMap<Color, Integer>();
		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				int rgb = bi.getRGB(j, i);
				myBI.setRGB(j, i, rgb);
				if (!hashMap.containsKey(new Color(rgb))) {
					hashMap.put(new Color(rgb), 1);
				}else{
					hashMap.put(new Color(rgb), hashMap.get(new Color(rgb))+1);
				}
			}
		}
		for (Color color : hashMap.keySet()) {
			System.out.println(color+"->"+hashMap.get(color));
		}
		System.out.println(hashMap.keySet().size());
		
		
		if (hashMap.keySet().size() > 256)
			return myBI;
		else if (hashMap.keySet().size() > 16)
			myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
					BufferedImage.TYPE_BYTE_INDEXED);
		else
			myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
					BufferedImage.TYPE_BYTE_BINARY);

		
		int deep = hashMap.keySet().size()>16 ? 256:16;
		int sizes = hashMap.keySet().size();
		int bits = sizes <= 16 ? 4 : 8;
		if (hashMap.keySet().size() <= 256) {
			byte[] br = new byte[deep];
			byte[] bg = new byte[deep];
			byte[] bb = new byte[deep];

			int item = 0;
			for (Color color : hashMap.keySet()) {
				br[item] = (byte) color.getRed();
				bg[item] = (byte) color.getGreen();
				bb[item] = (byte) color.getBlue();
				item++;
			}
			
			for (int i = item; i < deep; i++) {
				Color color =new Color(128, 128, 128);
				br[i] = (byte) color.getRed();
				bg[i] = (byte) color.getGreen();
				bb[i] = (byte) color.getBlue();
				
			}
			
			IndexColorModel indexColorModel = new IndexColorModel(bits, deep, br,
					bg, bb);
			
			int imageType=hashMap.keySet().size()>16?  BufferedImage.TYPE_BYTE_INDEXED:BufferedImage.TYPE_BYTE_BINARY;
			
			BufferedImage bufferedImage = new BufferedImage(bi.getWidth(), bi.getHeight(), imageType, indexColorModel);

			for (int i = 0; i < bi.getHeight(); i++) {
				for (int j = 0; j < bi.getWidth(); j++) {
					int rgb = bi.getRGB(j, i);
					bufferedImage.setRGB(j, i, rgb);
				}
			}
			return bufferedImage;
		}
		return myBI;
	}

	public static BufferedImage toGrayImage(BufferedImage bi) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				bi.getType());

		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				myBI.setRGB(j, i, GaryRGB(j, i, bi));
			}
		}

		return myBI;
	}

	public static BufferedImage toRedImage(BufferedImage bi,
			ArrayList<Color> arr) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				bi.getType());

		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				int r = GetR(j, i, bi, arr);

				myBI.setRGB(j, i, r);
			}
		}

		return myBI;
	}

	public static BufferedImage toGreenImage(BufferedImage bi,
			ArrayList<Color> arr) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				bi.getType());


		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				myBI.setRGB(j, i, GetG(j, i, bi, arr));
			}
		}

		return myBI;
	}

	public static BufferedImage toBlueImage(BufferedImage bi,
			ArrayList<Color> arr) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				bi.getType());

		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				myBI.setRGB(j, i, GetB(j, i, bi, arr));
			}
		}

		return myBI;
	}

	public static BufferedImage toWhiteImage(BufferedImage bi,
			ArrayList<Color> arr) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR/* bi.getType() */);
		System.out.println("11111111111");
		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {

				int rgb = GetW(j, i, bi, arr);

				myBI.setRGB(j, i, rgb);
			}
		}
		System.out.println("333333333333333");
		return myBI;
	}

	// 去杂色
	public static BufferedImage DelZS(BufferedImage bi, ArrayList<Color> arr) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				BufferedImage.TYPE_3BYTE_BGR);

		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				myBI.setRGB(j, i, ZSRGB(j, i, bi, 1, arr));
			}
		}

		return myBI;
	}

	private static int ZSRGB(int x, int y, BufferedImage bi, int range,
			ArrayList<Color> arr) {
		int RGB = bi.getRGB(x, y);
		int RGB1 = 0;
		int num = 0;
		if (y != 0 && x != 0 && y != bi.getHeight() - range
				&& x != bi.getWidth() - range) {
			int iWidth = (1 + range * 2);
			for (int i = 0; i < iWidth; i++) {
				for (int j = 0; j < iWidth; j++) {
					RGB1 = bi.getRGB(x - 1 + i, y - 1 + j);
					if (RGB1 == RGB)
						num++;
				}
			}
		} else {
			return RGB;
		}
		int t = ((range * 2 + 1) * (range * 2 + 1) + 1) / 2 - 1;
		
		if (num >= t)
			return RGB;
		else {
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			int iWidth = (1 + range * 2);
			for (int i = 0; i < iWidth; i++) {
				for (int j = 0; j < iWidth; j++) {
					RGB1 = bi.getRGB(x - 1 + i, y - 1 + j);
					if (hm.containsKey(RGB1)) {
						hm.put(RGB1, hm.get(RGB1) + 1);
					} else {
						hm.put(RGB1, 1);
					}
				}
			}
			num = 0;
			int max = 0;
			for (Integer i : hm.keySet()) {
				if (hm.get(i) > num) {
					num = hm.get(i);
					max = i;
				}

			}
			return max;

		}

		// return arr.get(0).getRGB();
	}

	public static BufferedImage JunZhiLvBo(BufferedImage bi) {

		BufferedImage myBI = new BufferedImage(bi.getWidth(), bi.getHeight(),
				/*bi.getType()*/ BufferedImage.TYPE_3BYTE_BGR );
		for (int i = 0; i < bi.getHeight(); i++) {
			for (int j = 0; j < bi.getWidth(); j++) {
				int rgb = AverageRGB(j, i, bi, 1);
				myBI.setRGB(j, i, rgb);
			}
		}
		
		return myBI;
	}

	private static int GetR(int x, int y, BufferedImage bi, ArrayList<Color> arr) {
		int r = (bi.getRGB(x, y) & 0xFF0000) >> 16;
		int minAbs = 255;
		int resultColor = 0;// bi.getRGB(x, y)&0xFF000000;
		int iRed = 0;
		for (Color color : arr) {
			if (Math.abs(color.getRed() - r) < minAbs) {
				iRed = color.getRed();
				minAbs = Math.abs(color.getRed() - r);
			}
		}
		resultColor |= (iRed << 16);
		return resultColor;
	}

	private static int GetG(int x, int y, BufferedImage bi, ArrayList<Color> arr) {
		int g = (bi.getRGB(x, y) & 0xFF00) >> 8;
		int minAbs = 255;
		int resultColor = 0;// bi.getRGB(x, y)&0xFF000000;
		int iGreen = 0;
		for (Color color : arr) {
			if (Math.abs(color.getGreen() - g) < minAbs) {
				iGreen = color.getGreen();
				minAbs = Math.abs(color.getGreen() - g);
			}
		}
		resultColor |= (iGreen << 8);
		return resultColor;

		// return (bi.getRGB(x, y)& 0xFF00FF00);
	}

	private static int GetB(int x, int y, BufferedImage bi, ArrayList<Color> arr) {
		int g = (bi.getRGB(x, y) & 0xFF);
		int minAbs = 255;
		int resultColor = 0;// bi.getRGB(x, y)&0xFF000000;
		int iBlue = 0;
		for (Color color : arr) {
			if (Math.abs(color.getBlue() - g) < minAbs) {
				iBlue = color.getBlue();
				minAbs = Math.abs(color.getBlue() - g);
			}
		}
		resultColor |= (iBlue);
		return resultColor;

		// return (bi.getRGB(x, y)& 0xFF0000FF);
	}

	private static int GetW(int x, int y, BufferedImage bi, ArrayList<Color> arr) {
		int getR = GetR(x, y, bi, arr) & 0xFF0000;
		int getG = GetG(x, y, bi, arr) & 0x00FF00;
		int getB = GetB(x, y, bi, arr) & 0x0000FF;

		Color c = new Color(getR | getG | getB);
		for (Color color : arr) {
			if (c.equals(color)) {

				return c.getRGB();
			}
		}

		// return c.getRGB();
		c = new Color(bi.getRGB(x, y));
		Color iColor = arr.get(0);
		// double min = 256 * 3;
		int minRGB = 256 * 3;
		for (Color color : arr) {
			int absR1 = Math.abs((c.getRed() - color.getRed()));

			int absG1 = Math.abs((c.getGreen() - color.getGreen()));

			int absB1 = Math.abs((c.getBlue() - color.getBlue()));

			// double absR = (double) absR1
			// / (double) (color.getRed() + 1);
			// double absG = (double) absG1
			// / (double) (color.getGreen() + 1);
			// double absB = (double) absB1
			// / (double) (color.getBlue() + 1);
			// System.out.println(absR + "->" + absG + "->" + absB
			// +"->"+c+"->"+color);
			// if ((absR + absG + absB) < min /*&& Math.abs(absR-absG)<=0.3 &&
			// Math.abs(absR-absB)<=0.3 && Math.abs(absB-absG)<=0.3*/) {
			// min = (absR + absG + absB);
			// iColor = color;
			// }

			if ((absR1 + absG1 + absB1) < minRGB /*
												 * && Math.abs(absR-absG)<=0.3
												 * && Math.abs(absR-absB)<=0.3
												 * && Math.abs(absB-absG)<=0.3
												 */) {
				minRGB = (absR1 + absG1 + absB1);
				iColor = color;
			}
		}

		return iColor.getRGB();

	}

	private static int GaryRGB(int x, int y, BufferedImage bi) {
		int RGB = bi.getRGB(x, y);
		int r = (RGB & 0xFF0000) >> 16;
		int g = (RGB & 0xFF00) >> 8;
		int b = RGB & 0xFF;

		r = (r + g + b) / 3;
		g = r;
		b = r;

		int RGB1 = (RGB & 0xFF000000) | (r << 16) | (g << 8) | (b);
		return RGB1;
	}

	private static int AverageRGB(int x, int y, BufferedImage bi, int range) {
		int RGB = bi.getRGB(x, y);
		int RGB1 = RGB;
		int r = 0;
		int g = 0;
		int b = 0;
		if (y != 0 && x != 0 && y != bi.getHeight() - range
				&& x != bi.getWidth() - range) {
			/*
			int iWidth = (1 + range * 2);
			int iNum = iWidth * iWidth;
			for (int i = 0; i < iWidth; i++) {
				for (int j = 0; j < iWidth; j++) {
					RGB = bi.getRGB(x - 1 + i, y - 1 + j);
					r += (RGB & 0xFF0000) >> 16;
					g += (RGB & 0xFF00) >> 8;
					b += RGB & 0xFF;
				}
			}
			b += RGB & 0xFF;
			r /= iNum;
			g /= iNum;
			b /= iNum;
			
			*/
			RGB = bi.getRGB(x - 1, y);
			r += (RGB & 0xFF0000) >> 16;
			g += (RGB & 0xFF00) >> 8;
			b += RGB & 0xFF;
			
			RGB = bi.getRGB(x , y-1);
			r += (RGB & 0xFF0000) >> 16;
			g += (RGB & 0xFF00) >> 8;
			b += RGB & 0xFF;
			
			RGB = bi.getRGB(x , y);
			r += (RGB & 0xFF0000) >> 16;
			g += (RGB & 0xFF00) >> 8;
			b += RGB & 0xFF;
			
			RGB = bi.getRGB(x + 1 , y);
			r += (RGB & 0xFF0000) >> 16;
			g += (RGB & 0xFF00) >> 8;
			
			RGB = bi.getRGB(x , y+1);
			r += (RGB & 0xFF0000) >> 16;
			g += (RGB & 0xFF00) >> 8;
			b += RGB & 0xFF;
			
			
			b += RGB & 0xFF;
			r /= 5;
			g /= 5;
			b /= 5;
			RGB1 = (RGB & 0xFF000000) | (r << 16) | (g << 8) | (b);
		}

		return RGB1;
	}
	
	 public static BufferedImage applyFilter(BufferedImage image, double[][] kernel) {
	        int width = image.getWidth();
	        int height = image.getHeight();
	        int centerX = kernel.length / 2;
	        int centerY = kernel[0].length / 2;

	        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                double sumR = 0, sumG = 0, sumB = 0, sumWeight = 0;
	                for (int j = 0; j < kernel[0].length; j++) {
	                    for (int i = 0; i < kernel.length; i++) {
	                        int pixelX = x + i - centerX;
	                        int pixelY = y + j - centerY;
	                        if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
	                            Color color = new Color(image.getRGB(pixelX, pixelY));
	                            double weight = kernel[i][j];
	                            sumR += color.getRed() * weight;
	                            sumG += color.getGreen() * weight;
	                            sumB += color.getBlue() * weight;
	                            sumWeight += weight;
	                        }
	                    }
	                }
	                if (sumWeight == 0) {
	                    sumWeight = 1;
	                }
	                int r = (int) Math.min(255, Math.max(0, sumR / sumWeight));
	                int g = (int) Math.min(255, Math.max(0, sumG / sumWeight));
	                int b = (int) Math.min(255, Math.max(0, sumB / sumWeight));
	                result.setRGB(x, y, ((r==0&&g==0&&b==0)? new Color(r, g, b):(new Color(255, 255, 255))).getRGB());
	            }
	        }

	        return result;
	    }
	 
	 public static BufferedImage applyFilter(BufferedImage image, int[][] kernel) {
	        int width = image.getWidth();
	        int height = image.getHeight();
	        int centerX = kernel.length / 2;
	        int centerY = kernel[0].length / 2;

	        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	        for (int y = 0; y < height; y++) {
	            for (int x = 0; x < width; x++) {
	                int sumR = 0, sumG = 0, sumB = 0, sumWeight = 0;
	                for (int j = 0; j < kernel[0].length; j++) {
	                    for (int i = 0; i < kernel.length; i++) {
	                        int pixelX = x + i - centerX;
	                        int pixelY = y + j - centerY;
	                        if (pixelX >= 0 && pixelX < width && pixelY >= 0 && pixelY < height) {
	                            Color color = new Color(image.getRGB(pixelX, pixelY));
	                            int weight = kernel[i][j];
	                            sumR += color.getRed() * weight;
	                            sumG += color.getGreen() * weight;
	                            sumB += color.getBlue() * weight;
	                            sumWeight += weight;
	                        }
	                    }
	                }
	                if (sumWeight == 0) {
	                    sumWeight = 1;
	                }
	                int r = (int) Math.min(255, Math.max(0, sumR / sumWeight));
	                int g = (int) Math.min(255, Math.max(0, sumG / sumWeight));
	                int b = (int) Math.min(255, Math.max(0, sumB / sumWeight));
	                result.setRGB(x, y, new Color(r, g, b).getRGB());
	            }
	        }

	        return result;
	    }
	 
	 public static BufferedImage setSize(BufferedImage image,int  width,int height){
		 BufferedImage result = new BufferedImage(width, height, image.getType());
		 
		 for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int x = j*(image.getWidth()-1)/width>=(image.getWidth()-1)?(image.getWidth()-1):j*(image.getWidth()-1)/width;
				int y = i*(image.getHeight()-1)/height>=(image.getHeight()-1)?(image.getHeight()-1):i*(image.getHeight()-1)/height;
				result.setRGB(j, i, image.getRGB(x, y));
			}
		}
		 
		 
		 
		 return result;
	 }
	 
}