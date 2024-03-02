package cn.sc.util;

import com.aspose.psd.Color;
import com.aspose.psd.Image;
import com.aspose.psd.SmoothingMode;
import com.aspose.psd.TextRenderingHint;
import com.aspose.psd.fileformats.png.PngColorType;
import com.aspose.psd.imageoptions.PngOptions;
import com.aspose.psd.imageoptions.VectorRasterizationOptions;

public class Cdr2Png {
	public static void main(String[] args) {
		String sourceFileName = "E:\\文档资料\\myjar\\Cynix - Logo_Final.cdr";
		String outFileName = "E:\\文档资料\\myjar\\Cynix - Logo_Final1.png";
		// AiImage就是将目标文件转为指定文件的类.
		// load()方法可以加载的是路径也可以是流.
		Image image = Image.load(sourceFileName);
		// 设置一些转png的格式参数(比如图片宽高)
		PngOptions options = new PngOptions();
		options.setColorType(PngColorType.TruecolorWithAlpha);
		VectorRasterizationOptions defaultOptions = (VectorRasterizationOptions) image
				.getDefaultOptions(new Object[] { Color.getWhite(), image.getWidth(), image.getHeight() });
		options.setVectorRasterizationOptions(defaultOptions);
		defaultOptions.setTextRenderingHint(TextRenderingHint.SingleBitPerPixel);
		defaultOptions.setSmoothingMode(SmoothingMode.None);
		// save()方法是保存图片.
		image.save(outFileName, options);
		// 也可以以流的方式保存,需要创建一个OutputStream来接收内容.
		// ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// image.save(outStream, options);

	}
}
