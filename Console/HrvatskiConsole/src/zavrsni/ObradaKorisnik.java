package zavrsni;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zavrsni.model.Korisnik;
import zavrsni.model.Obitelj;

public class ObradaKorisnik {
	
	private List<Korisnik> korisnici;
	
	public List<Korisnik> getKorisnici() {
		return korisnici;
	}


	private Izbornik izbornik;
	
	public ObradaKorisnik(Izbornik izbornik) {
		this();
		this.izbornik=izbornik;
	}
	
	
	public ObradaKorisnik() {
		korisnici = new ArrayList<Korisnik>();
		testniPodaci();
		
	}
	
	private void testniPodaci() {
		//korisnici.add(new Korisnik(1,"Franko","Seljic","fseljic@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(1)));
		//korisnici.add(new Korisnik(2,"Fiona","Lapsus","fseljic@hotmail.com",new Date(),false, izbornik.getObradaObitelj().getObitelji().get(2)));
		//korisnici.add(new Korisnik(3,"Kremenko","Laprdic","fseljic@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(0)));
	}
	
	public void prikaziIzbornik() {
		System.out.println("KORISNIK");
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
			pregledKorisnik();
			prikaziIzbornik();
			break;
		case 2:
			unosKorisnik();
			prikaziIzbornik();
			break;
		case 3:
			//promjenaKorisnik();
			prikaziIzbornik();
			break;
		case 4:
			//brisanjeKorisnik();
			prikaziIzbornik();
			break;
		case 5:
			System.out.println("");
			break;
			
		}
		
	}

	private void unosKorisnik() {
		Korisnik k= new Korisnik();
		k.setId(Pomocno.unosBroja("Unesi novi id ", "Pozitivan broj", 1, Integer.MAX_VALUE));
		k.setIme(Pomocno.unosStringa("Unesi ime ", "Greska"));
		k.setPrezime(Pomocno.unosStringa("Unesi prezime ", "Greska"));
		k.setEmail(Pomocno.unosStringa("Unesi email ", "Greska"));
		k.setSpol(Pomocno.unosSpol("Musko ili Zensko","Pogreska"));
		k.setDatumRodjenja(Pomocno.unosDatuma("Unesi datum rodjenja "));
		k.setObitelj(postaviObitelj());
		korisnici.add(k);
		
	}


	private Obitelj postaviObitelj() {
		izbornik.getObradaObitelj().pregledObitelj();
		int index=Pomocno.unosBroja("Unesi broj obitelji koji zelis ", "Pogreska", 1, izbornik.getObradaObitelj().getObitelji().size());
		return izbornik.getObradaObitelj().getObitelji().get(index-1);
	}


	public void pregledKorisnik() {
		int b=1;
		System.out.println("+++++++++++++++++");
		System.out.println("++++KORISNIK+++++");
		System.out.println("+++++++++++++++++");
		for (Korisnik k: korisnici) {
			System.out.println(b++ + ") "+k.getIme()+" "+k.getPrezime());
		}
		
		System.out.println("+++++++++++++++++");
		
	}
	
	
	
}
