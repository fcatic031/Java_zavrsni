package zavrsni;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("5)Izlaz");
		odabirStavke();
	}
	
	private void odabirStavke() {
		switch(Pomocno.unosBroja("Unesi broj stavke ", "Mora biti izmedju 1 i 5", 1, 5)) {
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
		case 5:
			System.out.println("GOODBYE");
			break;
		}
	}

	private void brisanjeObitelji() {
		pregledObitelj();
		int index = Pomocno.unosBroja("Unesi broj obitelji koji zelis promjeniti ", "Izmedju "+1+" i "+ obitelji.size(), 1, obitelji.size());
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
		Obitelj o = obitelji.get(index-1);
		int noviId=0;
		int[] sifre = popisId();
		Pomocno.unosIdPetlja(noviId,sifre);
		
		o.setId(noviId);
		o.setObiteljskoPrezime(Pomocno.unosStringa("Unesi novo obiteljsko ime ", "Greska"));
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
}
