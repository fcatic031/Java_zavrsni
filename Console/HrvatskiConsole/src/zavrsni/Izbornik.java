package zavrsni;

import java.util.Scanner;

public class Izbornik {

	private ObradaObitelj obradaObitelj;
	private ObradaKategorija obradaKategorija;
	private ObradaKorisnik obradaKorisnik;
	private ObradaDnevnaPotrosnja obradaDnevnaPotrosnja;

	public Izbornik() {
		obradaObitelj = new ObradaObitelj();
		obradaKategorija= new ObradaKategorija();
		obradaKorisnik= new ObradaKorisnik(this);
		obradaDnevnaPotrosnja= new ObradaDnevnaPotrosnja(this);
		Pomocno.ulaz = new Scanner(System.in);
		prikaziIzbornik();
		Pomocno.ulaz.close();
	}

	public void prikaziIzbornik() {
		System.out.println("++++IZBORNIK+++");
		System.out.println("\t1) Potrosnja");
		System.out.println("\t2) Kategorije");
		System.out.println("\t3) Obitelji");
		System.out.println("\t4) Korisnici");
		System.out.println("\t5) Izlaz");
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
			System.out.println("GOODBYE");
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
	
	
	

}
