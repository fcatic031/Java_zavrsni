package zavrsni;

import java.util.ArrayList;
import java.util.List;

import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;

public class ObradaKategorija {
	
	private List<Kategorija> kategorije;
	private Izbornik izbornik;
	
	
	public List<Kategorija> getKategorije() {
		return kategorije;
	}

	public ObradaKategorija() {
		kategorije = new ArrayList<Kategorija>();
		testniPodaci();
	}
	
	public ObradaKategorija(Izbornik izbornik) {
		this();
		this.izbornik=izbornik;
	}
	
	private void testniPodaci() {
		kategorije.add(new Kategorija(1,"Hrana"));
		kategorije.add(new Kategorija(2,"Voda"));
		kategorije.add(new Kategorija(3,"Struja"));
		kategorije.add(new Kategorija(4,"Plin"));
		
	}
	
	public void prikaziIzbornik() {
		Pomocno.naslovSredina("KATEGORIJA", "+","||", 30);
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
			pregledKategorija();
			prikaziIzbornik();
			break;
		case 2:
			unosKategorija();
			prikaziIzbornik();
			break;
		case 3:
			promjenaKategorije();
			prikaziIzbornik();
			break;
		case 4:
			brisanjeKategorije();
			prikaziIzbornik();
			break;
		case 6:
			Pomocno.naslovSredina("DOVIDJENJA", "=", "||", 30);
			break;
		case 5:
			statistikaKategorija();
			prikaziIzbornik();
			break;
			
		}
		
	}

	private void brisanjeKategorije() {
		pregledKategorija();
		int index=Pomocno.unosBroja("Unesi broj kategorije koji zelite obrisati: ", "Pogreska", 1, kategorije.size());
		kategorije.remove(index-1);
		
	}

	private void promjenaKategorije() {
		pregledKategorija();
		int index=Pomocno.unosBroja("Unesi broj kategorije koji zelis promjeniti: ", "Pogreska", 1, kategorije.size());
		Kategorija k = kategorije.get(index-1);
		Kategorija k2 = new Kategorija();
		k2.setId(k.getId());
		k2.setNaziv(k.getNaziv());
		k.setId(Pomocno.unosBroja("Unesi ID koji zelis da bude: ", "Pogreska", 1, Integer.MAX_VALUE));
		k.setNaziv(Pomocno.unosStringa("Unesi novi naziv kategorije: ", "Pogreska"));
		boolean potvrda = Pomocno.unosBoolean("Zelite li zadrzati promjene: ", "Pogreska! da ili ne?", "da", "ne");
		if (potvrda==false) {
			k.setId(k2.getId());
			k.setNaziv(k2.getNaziv());
			boolean potvrda2 = Pomocno.unosBoolean("Zelite li dalje napraviti neku promjenu ", "Pogreska! da ili ne?", "da", "ne");
			if (potvrda2) {
				promjenaKategorije();
			}
		}
	}

	private void unosKategorija() {
		Kategorija k = new Kategorija();
		k.setId(Pomocno.unosBroja("Unesi broj ID koji zelis: ", "Pozitivan broj", 1, Integer.MAX_VALUE));
		k.setNaziv(Pomocno.unosStringa("Unesi naziv kategorije koju zelis: ", "Greska"));
		kategorije.add(k);
		
	}

	public void pregledKategorija() {
		Pomocno.naslovSredina("", "+","||", 30);
		Pomocno.naslovSredina("KATEGORIJE", "+","||", 30);
		Pomocno.naslovSredina("", "+","||", 30);
		int b=1;
		for (Kategorija k : kategorije) {
			Pomocno.naslovSredina(b++ + ") " + k.getNaziv() ," ","||",30);
		}
		Pomocno.naslovSredina("", "+","||", 30);
	}
	
	public void statistikaKategorija() {
		pregledKategorija();
		//int odabir = Pomocno.unosBroja("Odaberite kategoriju o kojoj zelite vidjeti statistiku ", "Pogreska! Broj nije prihvacen!", 1, kategorije.size());
		float iznos;
		int n;
		Pomocno.naslovSredina("Statistika", "+","||", 30);
		Pomocno.naslovSredina("Prosjek po kategoriji", " ", "||", 30);
		for (Kategorija k: kategorije) {
			iznos = 0;
			n=0;
			for (DnevnaPotrosnja d: izbornik.getObradaDnevnaPotrosnja().getDnevnePotrosnje()) {
				if (d.getKategorija()==k) {
					iznos += d.getPotrosnja();
					n++;
				} 
			}
			Pomocno.naslovSredina(k.getNaziv()+": "+iznos/n, " ","||", 30);
			//System.out.println(k.getNaziv()+ ": " + iznos/n);
		}
	}
}
