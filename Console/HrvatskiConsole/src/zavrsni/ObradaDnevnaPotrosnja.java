package zavrsni;

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
		dnevnePotrosnje.add(new DnevnaPotrosnja(8,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(3),Pomocno.datum(13,3, 1997),5.10f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(9,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(2),new Date(),20.00f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(10,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(2),new Date(),3.60f));
		dnevnePotrosnje.add(new DnevnaPotrosnja(11,izbornik.getObradaKorisnik().getKorisnici().get(2),izbornik.getObradaKategorija().getKategorije().get(1),new Date(),17.20f));
		
	}
	

	public void prikaziIzbornik() {
		System.out.println("++DNEVNA+POTROSNJA++");
		System.out.println("1)Pregled");
		System.out.println("2)Unos");
		System.out.println("3)Promjena");
		System.out.println("4)Obrisati");
		System.out.println("5)Izlaz");
		odabirStavke();
	}

	private void odabirStavke() {
		switch (Pomocno.unosBroja("Unesi broj stavke ", "Između 1 i 5", 1, 5)) {
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
		case 6:
			//statistikaPotrosnja();
			prikaziIzbornik();
			break;
		case 5:
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
		int index = Pomocno.unosBroja("Unesi broj potrosnje koju zelite promjeniti", "Pogreska ", 1, dnevnePotrosnje.size());
		DnevnaPotrosnja d = dnevnePotrosnje.get(index-1);
		DnevnaPotrosnja d2= new DnevnaPotrosnja();
		d2.setId(d.getId());
		d2.setKorisnik(d.getKorisnik());
		d2.setKategorija(d.getKategorija());
		d2.setDatum(d.getDatum());
		d2.setPotrosnja(d.getPotrosnja());
		d.setId(Pomocno.unosBroja("Unesi id koji zelis za zeljenu potrosnju ", "Pogreska! Mora biti pozitivan broj", 1, Integer.MAX_VALUE));
		d.setKorisnik(postaviKorisnika());
		d.setKategorija(postaviKategoriju());
		d.setDatum(Pomocno.unosDatuma("Unesi datum "));
		d.setPotrosnja(Pomocno.unosFloat("Unesi potroseni iznos ", "Pogreska "));
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
	
}
