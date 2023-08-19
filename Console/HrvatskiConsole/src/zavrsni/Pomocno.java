package zavrsni;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import zavrsni.model.Korisnik;

public class Pomocno {
	
	public static Scanner ulaz;
	private static final String FORMAT_DATE="dd.MM.yyyy";
	private static SimpleDateFormat df= new SimpleDateFormat(FORMAT_DATE);
	
	public static int unosBroja(String poruka,String greska,int min,int max) {
		int i;
		while (true) {
			try {
				System.out.print("+ "+poruka);
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
	
	public static void unosIdPetlja(int noviId, int[] sifre) {
		
		while (true) {
			boolean fact=true;
			noviId = unosBroja("+ Unesite novi id ", "Error! Mora bit pozitivan cijelobrojan broj ", 1, Integer.MAX_VALUE);
			for (int id : sifre) {
				if (id==noviId) {
					System.out.println("Ovo je vec postojeci id");
					fact=false;
				}	
			}
			if (fact) {
				break;
			}
		}

	}
	public static String unosStringa(String poruka, String greska) {
		String s="";
		while (true) {
			System.out.print("+ "+poruka);
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
				System.out.print("+ "+poruka);
				return df.parse(ulaz.nextLine());
			} catch (Exception e) {
				System.out.println("Unos datuma u formatu "+ FORMAT_DATE+ " npr. "+ df.format(new Date()) + " za danas");
			}
		}
	}
	
	public static Date datum(int dan, int mjesec, int godina) {
		Date d = new Date(godina-1900,mjesec-1,dan);
//		Date d = new Date();
//		d.setYear(godina-1900);
//		d.setMonth(mjesec-1);
//		d.setDate(dan);
		return d;
	}
	
	public static boolean unosBoolean(String poruka,String greska, String da, String ne) {
		while (true){
			System.out.print("+ "+ poruka);
			String unos=ulaz.nextLine().trim().toLowerCase();
			if (unos.equals(da)) {
				return true;
			} else if (unos.equals(ne)) {
				return false;
			} else {
				System.out.println(greska);
			}
		}
	}
	
	
	public static float unosFloat(String poruka, String greska) {
		while (true) {
			try {
				System.out.print("+ "+poruka);
				return Float.parseFloat(ulaz.nextLine());
			} catch (Exception e) {
				System.out.println(greska);
			}
		}
	}
	
	public static String Razmaci(String znak, int n) {
		String s = "";
		for (int i=0; i<n; i++) {
			s=s+znak;
		}
		return s;
	}
	
	
	
}
