
public class readConfigFile2ArrList {
	
	public enum START2END{
		PALETTE,
		END,
	};
	public enum ColorModeEnum{
		CM_PALETTE,
		CM_MAX,
		END,
	}
	
	public ArrayList<Color> pattern;
	
	public String InputFileSrc;
	
	public String OutputFileSrc;
	
	public Integer ColorNumber;
	
	public ColorModeEnum ColorMode;
	
	public boolean ClearMottled;
	
	public Integer ColorRGB;
	
	public static void main(String[] args) throws FileNotFoundException {
		new readConfigFile2ArrList();
	}
	
	/**
	 * @throws FileNotFoundException
	 */
	
	
	public readConfigFile2ArrList() throws FileNotFoundException{
		System.out.println("解析配置文件");
		Scanner scanner = new Scanner(new File("logo_config.tx"));
		
		pattern=new ArrayList<Color>();
		
		a:while (scanner.hasNext()) {
			String str=scanner.nextLine().replaceAll(" ", "");
//			System.out.println(str);
			int index=str.indexOf("=");
			switch(index){
			
			 	case -1:
			 		if(str.indexOf("START")==-1)
			 			break a;
			 		START2END myS2E=START2END.END;
			 		if(str.replaceAll("_START","").equals("PALETTE"))
			 			myS2E=START2END.PALETTE;
			 		switch(myS2E){
			 			case PALETTE:
			 				boolean flag =true;
			 				while(scanner.hasNext()&& flag)
			 				{
			 					str=scanner.nextLine().replaceAll(" ", "");
//			 					System.out.println(str);
			 					if(str.equals("PALETTE_END")){
			 						flag=false;
			 					}else{
			 						String[] split = str.split(",");
			 						if(split.length==3){
			 							pattern.add(new Color(new Integer(split[0]), new Integer(split[1]), new Integer(split[2])));
			 						}
			 							
			 					}
			 				}
			 				break;
			 			default:
			 				System.out.println("error");
			 				break;
			 		}
			 		break;
			 	default:
			 		String[] split = str.split("=");
			 		if(split.length==2){
						if("InputFileSrc".equals(split[0])){
							
							InputFileSrc=split[1];
							
						}else if("OutputFileSrc".equals(split[0])){
							
							OutputFileSrc=split[1];
							
						}else if("ColorNumber".equals(split[0])){
							
							ColorNumber =new Integer(split[1]);
							
						}else if("ColorRGB".equals(split[0])){
							
							ColorRGB =new Integer(split[1]);
							if(ColorRGB<0)
								ColorRGB =0;
						}else if("ColorMode".equals(split[0])){
							if("PALETTE".equals(split[1])){
								ColorMode =ColorModeEnum.CM_PALETTE;
							}else if("MAX".equals(split[1])){
								ColorMode =ColorModeEnum.CM_MAX;
							}
						}else if("ClearMottled".equals(split[0])){
							if("y".equals(split[1])){
								ClearMottled =true;
							}else if("n".equals(split[1])){
								ClearMottled =false;
							}
						}
					}
			 		break;
 			
			}
		}
		
//		System.out.println(InputFileSrc);
//		System.out.println(OutputFileSrc);
//		for (Color c : pattern) {
//			System.out.println(c);
//		}
//		System.out.println(ColorNumber);
//		System.out.println(ColorMode);
		
	}
}
