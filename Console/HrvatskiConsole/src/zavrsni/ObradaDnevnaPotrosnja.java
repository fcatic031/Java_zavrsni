package zavrsni;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;

public class ObradaDnevnaPotrosnja {

	private List<DnevnaPotrosnja> dnevnePotrosnje;
	private Izbornik izbornik;
	
	
	public ObradaDnevnaPotrosnja(Izbornik izbornik) {
		this();
		this.izbornik=izbornik;
		testniPodaci();
	}

	public ObradaDnevnaPotrosnja() {
		dnevnePotrosnje = new ArrayList<DnevnaPotrosnja>();
	}
	
	public List<DnevnaPotrosnja> getDnevnePotrosnje(){
		return dnevnePotrosnje;
	}
	
	private void testniPodaci() {
		dnevnePotrosnje.add(new DnevnaPotrosnja(1,izbornik.getObradaKorisnik().getKorisnici().get(1),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(4, 5, 2023),5.55f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(2,izbornik.getObradaKorisnik().getKorisnici().get(1),izbornik.getObradaKategorija().getKategorije().get(2),new Date(120,3,5),50.2f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(3,izbornik.getObradaKorisnik().getKorisnici().get(1),izbornik.getObradaKategorija().getKategorije().get(2),new Date(122,4,4),32.40f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(4,izbornik.getObradaKorisnik().getKorisnici().get(1),izbornik.getObradaKategorija().getKategorije().get(2),new Date(122,6,2),6.30f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(5,izbornik.getObradaKorisnik().getKorisnici().get(1),izbornik.getObradaKategorija().getKategorije().get(2),new Date(122,4,1),12.3f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(6,izbornik.getObradaKorisnik().getKorisnici().get(1),izbornik.getObradaKategorija().getKategorije().get(1),new Date(121,6,1),6.60f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(7,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),new Date(121,4,9),12.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(8,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(13,3, 2023),5.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(9,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(2),new Date(),20.00f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(10,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(2),new Date(),3.60f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(11,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),new Date(),17.20f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(12,izbornik.getObradaKorisnik().getKorisnici().get(3),izbornik.getObradaKategorija().getKategorije().get(0),Pomocno.datum(14, 5, 2017),220.20f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(17,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(14,3,2023),11.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(18,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(15,3, 2023),7.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(27,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(25,3, 2023),12.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(28,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(13,4, 2023),5.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(37,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(15,4, 2023),12.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(38,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(18,3, 2023),5.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(47,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(14,3, 2023),2.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(48,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(0),Pomocno.datum(17,4, 2023),52.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(57,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(5,4, 2023),12.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(58,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(23,5, 2023),5.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(67,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),Pomocno.datum(12,5, 2023),12.90f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(68,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(13,5, 2023),5.10f));
		
		
	}
	

	public void prikaziIzbornik() {
		Pomocno.naslovSredina("DNEVNA POTROSNJA", "+","||", 30);
		Pomocno.naslovSredina("1)Pregled", " ","||", 30);
		Pomocno.naslovSredina("2)Unos", " ","||", 30);
		Pomocno.naslovSredina("3)Promjena", " ","||", 30);
		Pomocno.naslovSredina("4)Obrisati", " ","||", 30);
		Pomocno.naslovSredina("5)Statistika", " ","||", 30);
		Pomocno.naslovSredina("6)Nazad", " ","||", 30);
		Pomocno.naslovSredina("", "+", "||", 30);
		odabirStavke();
	}

	private void odabirStavke() {
		switch (Pomocno.unosBroja("Unesi broj stavke ", "Između 1 i 6", 1, 6)) {
		case 1:
			pregledDnevnihPotrosnji();
			prikaziIzbornik();
			break;
		case 2:
			unosDnevnePotrosnje();
			prikaziIzbornik();
			break;
		case 3:
			promjenaDnevnaPotrosnja();
			prikaziIzbornik();
			break;
		case 4:
			brisanjeDnevnaPotrosnja();
			prikaziIzbornik();
			break;
		case 5:
			statistikaPotrosnja();
			prikaziIzbornik();
			break;
		case 6:
			System.out.println("");
			break;

		}

	}

	private void pregledDnevnihPotrosnji() {
		int b = 1;
		System.out.println("+++++++++++++++++");
		System.out.println("DNEVNA++POTROSNJA");
		System.out.println("+++++++++++++++++");
		for (DnevnaPotrosnja d : dnevnePotrosnje) {
			System.out.println(b++  + ") "+ d.getDatum() +"|"+ d.getKorisnik().getIme() + " " + d.getKorisnik().getPrezime() + " "
					+ d.getKategorija().getNaziv() + " " + d.getPotrosnja());
			
		}
		
		System.out.println("+++++++++++++++++");

	}

	private void unosDnevnePotrosnje() {
		DnevnaPotrosnja d = new DnevnaPotrosnja();
		d.setId(Pomocno.unosBroja("Unesi novi id ", "Pozitivan broj", 1, Integer.MAX_VALUE));
		d.setKorisnik(postaviKorisnika());
		d.setKategorija(postaviKategoriju());
		d.setDatum(Pomocno.unosDatuma("Unesi dan "));
		d.setPotrosnja(Pomocno.unosFloat("Unesi potrosen iznos ", "Pogreska"));
		dnevnePotrosnje.add(d);
	}

	private Kategorija postaviKategoriju() {
		izbornik.getObradaKategorija().pregledKategorija();
		int index=Pomocno.unosBroja("Unesi broj kategorije iz tablice ", "Pogreska", 1, izbornik.getObradaKategorija().getKategorije().size());
		return izbornik.getObradaKategorija().getKategorije().get(index-1);
	}

	private Korisnik postaviKorisnika() {
		izbornik.getObradaKorisnik().pregledKorisnik();
		int index=Pomocno.unosBroja("Unesi broj korisnika iz tablice ", "Pogreska", 1, izbornik.getObradaKorisnik().getKorisnici().size());
		return izbornik.getObradaKorisnik().getKorisnici().get(index-1);
	}
	
	private void promjenaDnevnaPotrosnja() {
		pregledDnevnihPotrosnji();
		int index = Pomocno.unosBroja("Unesi broj potrosnje koju zelite promjeniti: ", "Pogreska ", 1, dnevnePotrosnje.size());
		DnevnaPotrosnja d = dnevnePotrosnje.get(index-1);
		DnevnaPotrosnja d2= new DnevnaPotrosnja();
		d2.setId(d.getId());
		d2.setKorisnik(d.getKorisnik());
		d2.setKategorija(d.getKategorija());
		d2.setDatum(d.getDatum());
		d2.setPotrosnja(d.getPotrosnja());
		d.setId(Pomocno.unosBroja("Unesi id koji zelis za zeljenu potrosnju: ", "Pogreska! Mora biti pozitivan broj", 1, Integer.MAX_VALUE));
		d.setKorisnik(postaviKorisnika());
		d.setKategorija(postaviKategoriju());
		d.setDatum(Pomocno.unosDatuma("Unesi datum: "));
		d.setPotrosnja(Pomocno.unosFloat("Unesi potroseni iznos: ", "Pogreska "));
		boolean potvrda = Pomocno.unosBoolean("Zelite li dalje zadrzati promjene? ", "Pogreska! da ili ne?", "da", "ne");
		if (potvrda==false) {
			d.setId(d2.getId());
			d.setKorisnik(d2.getKorisnik());
			d.setKategorija(d2.getKategorija());
			d.setDatum(d2.getDatum());
			d.setPotrosnja(d2.getPotrosnja());
			boolean potvrda2 = Pomocno.unosBoolean("Zelite li dalje napraviti neku promjenu? ", "Pogreska! da ili ne?", "da", "ne");
			if (potvrda2) {
				promjenaDnevnaPotrosnja();
			}
		}
	}
	
	private void brisanjeDnevnaPotrosnja() {
		pregledDnevnihPotrosnji();
		int index = Pomocno.unosBroja("Unesi broj potrosnje koju zelite obrisati ", "Pogreska ", 1, dnevnePotrosnje.size());
		dnevnePotrosnje.remove(index-1);
	}
	
	private float mjesecnaPotrosnja(int godina, int mjesec, Kategorija k, Korisnik korisnik) {
		LocalDate pocetniDatum = LocalDate.of(godina, mjesec, 1);
		LocalDate zavrsniDatum = (mjesec!=12) ? LocalDate.of(godina, mjesec+1, 1) : LocalDate.of(godina+1, mjesec-11, 1);
		//Period period = Period.between(pocetniDatum, zavrsniDatum);
		float zbroj=0f;
		for (DnevnaPotrosnja d: dnevnePotrosnje) {
			LocalDate x = Pomocno.DateLocalDate(d.getDatum());
			if (d.getKorisnik()==korisnik && d.getKategorija()==k && x.isBefore(zavrsniDatum) && x.isAfter(pocetniDatum)) {
				zbroj+=d.getPotrosnja();
			}
		}
		return zbroj;
	}
	
	private void statistikaPotrosnja() {
		izbornik.getObradaKorisnik().pregledKorisnik();
		int index = Pomocno.unosBroja("Unesite broj korisnika: ", "Pogreska!", 1, izbornik.getObradaKorisnik().getKorisnici().size());
		Korisnik korisnik = izbornik.getObradaKorisnik().getKorisnici().get(index-1);
		int godina = Pomocno.unosBroja("Unesi godinu: ", "Pogreska!", 1970, 2023);
		izbornik.getObradaKategorija().pregledKategorija();
		int index2= Pomocno.unosBroja("Unesite broj kategorije: ", "Pogreska!", 1, izbornik.getObradaKategorija().getKategorije().size());
		Kategorija k = izbornik.getObradaKategorija().getKategorije().get(index2-1);
		Pomocno.naslovSredina(k.getNaziv(), "+", "|", 62);
		for (int i=1; i<=12; i++) {
			Pomocno.tablicaSredina(Pomocno.mjesec(i), " ", "|", 30);
			Pomocno.tablicaSredina(""+mjesecnaPotrosnja(godina,i,k,korisnik), " ", "|", 30);
			System.out.println();
			//System.out.println(Pomocno.mjesec(i)+": "+mjesecnaPotrosnja(godina,i,k,korisnik));
		}
		Pomocno.naslovSredina("", "+", "|", 62);
		//DRUGA IDEJA OBRADE SVIH KATEGORIJA U GODINI
//		for (Kategorija k: izbornik.getObradaKategorija().getKategorije()) {
//			System.out.println(k.getNaziv()+": ");
//			for (int i=1; i<=12; i++) {
//				System.out.println(i+": "+mjesecnaPotrosnja(godina,i,k,korisnik));
//			}
//		}
		
	}
	
}
