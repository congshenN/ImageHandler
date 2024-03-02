package cn.sc.util;

import com.aspose.psd.Image;
import com.aspose.psd.fileformats.ai.AiImage;
import com.aspose.psd.fileformats.png.PngColorType;
import com.aspose.psd.imageoptions.PngOptions;

public class Ai2Png {
	public static void main(String[] args) {
		String sourceFileName    = "E:\\文档资料\\myjar\\开机logo-BIOS 新界面1920x1080.ai";
	    String outFileName       = "E:\\文档资料\\myjar\\开机logo-BIOS1 新界面1920x1080.png";
	    // AiImage就是将目标文件转为指定文件的类.
	    // load()方法可以加载的是路径也可以是流.
	    AiImage image = (AiImage)Image.load(sourceFileName);
	    // 设置一些转png的格式参数(比如图片宽高)
	    PngOptions options = new PngOptions();
	    options.setColorType(PngColorType.TruecolorWithAlpha);
	    // save()方法是保存图片.
	    image.save(outFileName, options);
	    // 也可以以流的方式保存,需要创建一个OutputStream来接收内容.
	    // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    // image.save(outStream, options);

	}
}
