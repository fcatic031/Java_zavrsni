package zavrsni;

import java.util.ArrayList;
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
	}

	public ObradaDnevnaPotrosnja() {
		dnevnePotrosnje = new ArrayList<DnevnaPotrosnja>();
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
			System.out.println(b++ + ") " + d.getKorisnik().getIme() + " " + d.getKorisnik().getPrezime() + " "
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
		d.setId(Pomocno.unosBroja("Unesi id koji zelis za zeljenu potrosnju ", "Pogreska! Mora biti pozitivan broj", 1, Integer.MAX_VALUE));
		d.setKorisnik(postaviKorisnika());
		d.setKategorija(postaviKategoriju());
		d.setDatum(Pomocno.unosDatuma("Unesi datum "));
		d.setPotrosnja(Pomocno.unosFloat("Unesi potroseni iznos ", "Pogreska "));
		
	}
	
	private void brisanjeDnevnaPotrosnja() {
		pregledDnevnihPotrosnji();
		int index = Pomocno.unosBroja("Unesi broj potrosnje koju zelite obrisati ", "Pogreska ", 1, dnevnePotrosnje.size());
		dnevnePotrosnje.remove(index-1);
	}
}
