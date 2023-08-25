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
		Pomocno.naslovSredina("", "=", "||", 30);
		Pomocno.naslovSredina("IZBORNIK", "=","||", 30);
		Pomocno.naslovSredina("", "=", "||", 30);
		Pomocno.naslovSredina("1)Potrosnja", " ","||", 30);
		Pomocno.naslovSredina("2)Kategorija", " ","||", 30);
		Pomocno.naslovSredina("3)Obitelj", " ","||", 30);
		Pomocno.naslovSredina("4)Korisnik", " ","||", 30);
		Pomocno.naslovSredina("5)Izlaz", " ","||", 30);
		Pomocno.naslovSredina("", "=", "||", 30);
		ucitajStavkuIzbornika();
	}

	private void ucitajStavkuIzbornika() {
		switch (Pomocno.unosBroja("Unesi broj stavke: ", "Mora biti izmedju 1 i 5", 1, 6)) {
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
			Pomocno.naslovSredina("DOVIDJENJA", "=", "||", 30);
			break;
		case 6:
			Pomocno.naslovSredina("DOVIDJENJA", "=", "||", 30);
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
