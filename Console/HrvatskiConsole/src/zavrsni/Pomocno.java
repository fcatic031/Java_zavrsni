package zavrsni;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Pomocno {
	
	public static Scanner ulaz;
	private static final String FORMAT_DATE="dd.MM.yyyy";
	private static SimpleDateFormat df= new SimpleDateFormat(FORMAT_DATE);
	
	public static int unosBroja(String poruka,String greska,int min,int max) {
		int i;
		while (true) {
			try {
				System.out.print(poruka);
				i=Integer.parseInt(ulaz.nextLine());
				if (i>=min && i<=max) {
					return i;
				}
				System.out.println(greska);
			} catch (Exception e) {
				System.out.println(greska);
			}
			
		}
	}
	
	public static String unosStringa(String poruka, String greska) {
		String s="";
		while (true) {
			System.out.print(poruka);
			s=ulaz.nextLine();
			if (s.trim().length()>0) {
				return s;
			}
			System.out.println(greska);
		}
	}
	
	public static Date unosDatuma(String poruka) {
		while (true) {
			try {
				System.out.print(poruka);
				return df.parse(ulaz.nextLine());
			} catch (Exception e) {
				System.out.println("Unos datuma u formatu "+ FORMAT_DATE+ " npr. "+ df.format(new Date()) + " za danas");
			}
		}
	}
	
	
	public static boolean unosSpol(String poruka,String greska) {
//		String poruka="Musko ili zensko";
//		String greska="Krivi unos";
		while (true){
			System.out.print("Unesi spol \n (" + poruka+"): ");
			String unos=ulaz.nextLine().trim().toLowerCase();
			if (unos.equals("musko")) {
				return true;
			} else if (unos.equals("zensko")) {
				return false;
			} else {
				System.out.println(greska);
			}
		}
	}
	
	public static float unosFloat(String poruka, String greska) {
		while (true) {
			try {
				System.out.print(poruka);
				return Float.parseFloat(ulaz.nextLine());
			} catch (Exception e) {
				System.out.println(greska);
			}
		}
	}
	
	
}
