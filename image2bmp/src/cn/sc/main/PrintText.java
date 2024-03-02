package cn.sc.main;

import java.util.HashMap;
import java.util.HashSet;

public class PrintText {
	
	public static HashMap<Character, String> ch = new HashMap<Character, String>();
	
	public static void init(){
		ch.put('ã', "_abl_,");
		ch.put('ç', "_c1p_,");
		ch.put('ć', "_c2s_,");
		ch.put('é', "_e2s_,");
		ch.put('ê', "_edj_,");
		ch.put('ę', "_ewb_,");
		ch.put('ı', "_i0d_,");
		ch.put('í', "_i2s_,");
		ch.put('ì', "_i4s_,");
		ch.put('ö', "_o2d_,");
		ch.put('Ş', "_s1p_,");
		ch.put('ü', "_u2d_,");
		ch.put('ż', "_z1d_,");
		ch.put('.', "_DOT_,");
		ch.put(' ', "_,");
		ch.put('?', "_WH_,");
		ch.put('\'', "_PIE_,");
		
		String input = "текущая операция повысит энергоемкость продукции.";
		System.out.println((int)'т');
		for (int i = 0; i < input.length(); i++) {
			boolean flag = true;
			a:for ( char ich : ch.keySet()) {
				if(input.charAt(i) == ich){
					System.out.print(ch.get(ich));
					flag = false;
					break a;
				}
			}
			if(flag)
				System.out.print("_"+input.charAt(i)+"_,");
		}
		System.out.println("_END_,");
	}
	
	public static void main(String[] args) {
		//init();
	}
}

