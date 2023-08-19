package zavrsni;

import java.util.ArrayList;
import java.util.List;

import zavrsni.model.DnevnaPotrosnja;
import zavrsni.model.Kategorija;
import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;

public class ObradaObitelj {
	
	private List<Obitelj> obitelji;
	private Izbornik izbornik;
	
	
	public List<Obitelj> getObitelji() {
		return obitelji;
	}

	public ObradaObitelj(Izbornik izbornik) {
		this();
		this.izbornik=izbornik;
	}
	
	public ObradaObitelj() {
		obitelji= new ArrayList<Obitelj>();
		testniPodaci();
	}
	
	private void testniPodaci() {
		obitelji.add(new Obitelj(1,"Guzicari"));
		obitelji.add(new Obitelj(2,"Lavor"));
		obitelji.add(new Obitelj(3,"Papadopolis"));
		obitelji.add(new Obitelj(4,"Sizif"));
		obitelji.add(new Obitelj(5,"Adams"));
		obitelji.add(new Obitelj(6,"Propeler"));
		
	}
	
	
	public void prikaziIzbornik() {
		System.out.println("OBITELJ");
		System.out.println("1)Pregled");
		System.out.println("2)Unos");
		System.out.println("3)Promjena");
		System.out.println("4)Obrisati");
		System.out.println("5)Statistika");
		System.out.println("6)Nazad");
		odabirStavke();
	}
	
	private void odabirStavke() {
		switch(Pomocno.unosBroja("Unesi broj stavke ", "Mora biti izmedju 1 i 5", 1, 6)) {
		case 1:
			pregledObiteljOpcija();
			prikaziIzbornik();
			break;
		case 2:
			unosObitelj();
			prikaziIzbornik();
			break;
		case 3:
			promjenaObitelji();
			prikaziIzbornik();
			break;
		case 4:
			brisanjeObitelji();
			prikaziIzbornik();
			break;
		case 6:
			System.out.println("===+++++++++++++++===");
			break;
		case 5:
			statistikaObitelj();
			prikaziIzbornik();
			break;
		}
	}

	private void brisanjeObitelji() {
		pregledObitelj();
		int index = Pomocno.unosBroja("Unesi broj obitelji koji zelis promjeniti ", "Izmedju "+1+" i "+ obitelji.size(), 1, obitelji.size());
		//Brisanje clanova obitelji s obitelji
		boolean korisniciBrisanje = Pomocno.unosBoolean("Zelite li obrisati i sve clanove te obitelji? ", "Pogreska", "da", "ne");
		if (korisniciBrisanje) {
			izbornik.getObradaKorisnik().getKorisnici().removeIf(k -> k.getObitelj()==obitelji.get(index-1));
		}
		obitelji.remove(index-1);
	}
	

	public int[] popisId() {
		int[] sifre = new int[obitelji.size()];
		for (int i=0; i<obitelji.size(); i++) {
			Obitelj o = obitelji.get(i);
			sifre[i] = o.getId();
		}
		return sifre;
	}


	private void promjenaObitelji() {
		pregledObitelj();
		int index = Pomocno.unosBroja("Unesi broj obitelji koji zelis promjeniti ", "Izmedju "+1+" i "+ obitelji.size(), 1, obitelji.size());
		//Obitelj o = new Obitelj();
		Obitelj o = obitelji.get(index-1);
		Obitelj o2= new Obitelj();
		o2.setId(o.getId());
		o2.setObiteljskoPrezime(o.getObiteljskoPrezime());
		int noviId=0;
		int[] sifre = popisId();
		Pomocno.unosIdPetlja(noviId,sifre);
		
		o.setId(noviId);
		o.setObiteljskoPrezime(Pomocno.unosStringa("Unesi novo obiteljsko ime ", "Greska"));
		boolean potvrda = Pomocno.unosBoolean("Zelite li dalje zadrzati promjene? ", "Pogreska! da ili ne?", "da", "ne");
		if (potvrda==false) {
			o.setId(o2.getId());
			o.setObiteljskoPrezime(o2.getObiteljskoPrezime());
			boolean potvrda2 = Pomocno.unosBoolean("Zelite li dalje napraviti neku promjenu? ", "Pogreska! da ili ne?", "da", "ne");
			if (potvrda2) {
				promjenaObitelji();
			}
		}
		
	}


	private void unosObitelj() {
		Obitelj o = new Obitelj();
		int noviId=0;
		int[] sifre = popisId();
		Pomocno.unosIdPetlja(noviId,sifre);
		o.setId(noviId);
		o.setObiteljskoPrezime(Pomocno.unosStringa("Unesi novo obiteljsko ime ", "Greska"));
		obitelji.add(o);
	}

	public void pregledObitelj() {
		int b=1;
		System.out.println("+++++++++++++++++");
		System.out.println("++++OBITELJI+++++");
		System.out.println("+++++++++++++++++");
		for (Obitelj o: obitelji) {
			System.out.println(b++ + ") "+o.getObiteljskoPrezime());
		}
		System.out.println("+++++++++++++++++");
	}
	
	public void pregledObiteljOpcija() {
		boolean unos = Pomocno.unosBoolean("Zelite li vidjeti sve clanove obitelji ", "Pogreska! da ili ne!", "da", "ne");
		if (unos) {
			System.out.println("+++++++++++++++++");
			System.out.println("++++OBITELJI+++++");
			System.out.println("+++++++++++++++++");
			for (Obitelj o: obitelji) {
				System.out.print(o.getObiteljskoPrezime()+": ");
				for (Korisnik k:izbornik.getObradaKorisnik().getKorisnici()) {
					if (k.getObitelj()==o) {
						System.out.print(k.getIme()+" "+k.getPrezime()+", ");
					}
				}
				System.out.println();
			}
			System.out.println("+++++++++++++++++");
		}else {
			pregledObitelj();
		}
	}
	
	private void statistikaObitelj() {
		System.out.println("===OSNOVNA STATISTIKA===");
		float korisnik_obitelj = izbornik.getObradaKorisnik().getKorisnici().size()/obitelji.size();
		System.out.println("Prosječan broj clanova u obitelji: "+korisnik_obitelj);
		
		System.out.println("========================");
		pregledObitelj();
		int index = Pomocno.unosBroja("Odaberi obitelj o kojoj zelis vidjeti statistiku ", "Izmedju 1 i "+obitelji.size(), 1, obitelji.size());
		Obitelj o = obitelji.get(index-1);
		float iznos=0;
		for (Kategorija k : izbornik.getObradaKategorija().getKategorije()) {
			float iznos2=0;
			System.out.print(k.getNaziv()+": ");
			for (DnevnaPotrosnja d : izbornik.getObradaDnevnaPotrosnja().getDnevnePotrosnje()) {
				if (d.getKorisnik().getObitelj()==o && d.getKategorija()==k) {
					iznos2+=d.getPotrosnja();
				}
			}
			System.out.println(iznos2);
			iznos+=iznos2;
		}
		
		System.out.println("Ukupno potroseni iznos: " + iznos);
	}
}
