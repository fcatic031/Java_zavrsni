package zavrsni;

import java.util.Scanner;

public class Izbornik {

	private ObradaObitelj obradaObitelj;
	private ObradaKategorija obradaKategorija;
	private ObradaKorisnik obradaKorisnik;
	private ObradaDnevnaPotrosnja obradaDnevnaPotrosnja;

	public Izbornik() {
		obradaObitelj = new ObradaObitelj(this);
		obradaKategorija= new ObradaKategorija(this);
		obradaKorisnik= new ObradaKorisnik(this);
		obradaDnevnaPotrosnja= new ObradaDnevnaPotrosnja(this);
		Pomocno.ulaz = new Scanner(System.in);
		prikaziIzbornik();
		Pomocno.ulaz.close();
	}

	public void prikaziIzbornik() {
		System.out.println("===++++IZBORNIK+++===");
		System.out.println("|||1) Potrosnja   |||");
		System.out.println("|||2) Kategorije  |||");
		System.out.println("|||3) Obitelji    |||");
		System.out.println("|||4) Korisnici   |||");
		System.out.println("|||5) Izlaz       |||");
		System.out.println("===+++++++++++++++===");
		ucitajStavkuIzbornika();
	}

	private void ucitajStavkuIzbornika() {
		switch (Pomocno.unosBroja("Unesi broj stavke ", "Mora biti izmedju 1 i 5", 1, 5)) {
		case 1:
			obradaDnevnaPotrosnja.prikaziIzbornik();
			prikaziIzbornik();
			break;
		case 2:
			obradaKategorija.prikaziIzbornik();
			prikaziIzbornik();
			break;
		case 3:
			obradaObitelj.prikaziIzbornik();
			prikaziIzbornik();
			break;
		case 4:
			obradaKorisnik.prikaziIzbornik();
			prikaziIzbornik();
			break;
		case 5:
			System.out.println("===++DOVIDJENJA+++===");
			break;
		}

	}

	public ObradaObitelj getObradaObitelj() {
		return obradaObitelj;
	}

	public ObradaKategorija getObradaKategorija() {
		return obradaKategorija;
	}

	public ObradaKorisnik getObradaKorisnik() {
		return obradaKorisnik;
	}
	
	public ObradaDnevnaPotrosnja getObradaDnevnaPotrosnja() {
		return obradaDnevnaPotrosnja;
	}
	
	
	

}
