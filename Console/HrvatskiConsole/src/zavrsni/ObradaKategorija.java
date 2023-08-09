package zavrsni;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("KATEGORIJA");
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
		case 5:
			System.out.println("GOODBYE");
			break;
			
		}
		
	}

	private void brisanjeKategorije() {
		pregledKategorija();
		int index=Pomocno.unosBroja("Unesi broj kategorije koji zelite obrisati ", "Greska", 1, kategorije.size());
		kategorije.remove(index-1);
		
	}

	private void promjenaKategorije() {
		pregledKategorija();
		int index=Pomocno.unosBroja("Unesi broj kategorije koji zelis promjeniti ", "Greska", 1, kategorije.size());
		Kategorija k = kategorije.get(index-1);
		Kategorija k2 = new Kategorija();
		k2.setId(k.getId());
		k2.setNaziv(k.getNaziv());
		k.setId(Pomocno.unosBroja("Unesi ID koji zelis da bude ", "greska", 1, Integer.MAX_VALUE));
		k.setNaziv(Pomocno.unosStringa("Unesi novi naziv kategorije ", "Greska"));
		boolean potvrda = Pomocno.unosBoolean("Zelite li zadrzati promjene ", "Pogreska! da ili ne?", "da", "ne");
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
		k.setId(Pomocno.unosBroja("Unesi broj ID koji zelis ", "Pozitivan broj", 1, Integer.MAX_VALUE));
		k.setNaziv(Pomocno.unosStringa("Unesi naziv kategorije koju zelis", "Greska"));
		kategorije.add(k);
		
	}

	public void pregledKategorija() {
		System.out.println("+++++++++++++++++");
		System.out.println("+++KATEGORIJE++++");
		System.out.println("+++++++++++++++++");
		int b=1;
		for (Kategorija k : kategorije) {
			System.out.println(b++ + ") " + k.getNaziv());
		}
		
		System.out.println("++++++++++++++++++");
	}
}
