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
		testniPodaci();
	}
	
	
	public ObradaKorisnik() {
		korisnici = new ArrayList<Korisnik>();
		
	}
	
	private void testniPodaci() {
		korisnici.add(new Korisnik(1,"Franko","Seljic","fseljic@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(1)));
		korisnici.add(new Korisnik(2,"Fiona","Lapsus","flapsus@hotmail.com",new Date(),false, izbornik.getObradaObitelj().getObitelji().get(2)));
		korisnici.add(new Korisnik(3,"Kremenko","Laprdic","krekhed@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(0)));
		korisnici.add(new Korisnik(4,"Goran","Gustic","gusti99@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(1)));
		korisnici.add(new Korisnik(5,"Petra","Adams","fseljic@hotmail.com",new Date(),false, izbornik.getObradaObitelj().getObitelji().get(4)));
		korisnici.add(new Korisnik(6,"Kremenko","Laprdic","fseljic@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(0)));
		korisnici.add(new Korisnik(7,"Ljubomir","Propeler","prpko@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(5)));
		korisnici.add(new Korisnik(8,"Franka","Propeler","prpa5@hotmail.com",new Date(),false, izbornik.getObradaObitelj().getObitelji().get(5)));
		korisnici.add(new Korisnik(9,"Gljevnka","Propeler","prop3@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(5)));
		korisnici.add(new Korisnik(10,"Franko","Seljic","fseljic@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(1)));
		korisnici.add(new Korisnik(11,"Fiona","Lapsus","fseljic@hotmail.com",new Date(),false, izbornik.getObradaObitelj().getObitelji().get(2)));
		korisnici.add(new Korisnik(12,"Kremenko","Laprdic","fseljic@hotmail.com",new Date(),true, izbornik.getObradaObitelj().getObitelji().get(0)));
	
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
			pregledKorisnikOpcije();
			prikaziIzbornik();
			break;
		case 2:
			unosKorisnik();
			prikaziIzbornik();
			break;
		case 3:
			promjenaKorisnik();
			prikaziIzbornik();
			break;
		case 4:
			brisanjeKorisnik();
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
		k.setSpol(Pomocno.unosBoolean("Musko ili Zensko","Pogreska","musko","zensko"));
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
		System.out.println("++++++++++++++++++");
		System.out.println("++++KORISNICI+++++");
		System.out.println("++++++++++++++++++");
		for (Korisnik k: korisnici) {
			System.out.println(b++ + ") "+k.getIme()+" "+k.getPrezime());
		}
		System.out.println("+++++++++++++++++");
		
	}
	
	public void pregledKorisnikOpcije() {
		System.out.println("Zahvaljuci navedenim opcijama mozete birati sto zelite vidjeti");
		boolean id=Pomocno.unosBoolean("id? ", "da ili ne","da","ne");
		boolean email=Pomocno.unosBoolean("email? ", "da ili ne","da","ne");
		boolean datumRodjenja=Pomocno.unosBoolean("datum rodjenja? ", "da ili ne","da","ne");
		boolean spol=Pomocno.unosBoolean("spol? ", "da ili ne","da","ne");
		boolean obitelj=Pomocno.unosBoolean("obitelj? ", "da ili ne","da","ne");
		if (id) {
			System.out.print("id|");
		}
		System.out.print("ime|");
		System.out.print("prezime|");
		
		if (email) {
			System.out.print("email|");
		}
		if (datumRodjenja) {
			System.out.print("Datum rodjenja|");
		}
		if (spol) {
			System.out.print("Spol|");
		}
		if (obitelj) {
			System.out.print("Obitelj|");
		}
		System.out.println();
		for (Korisnik k: korisnici) {
			if (id) {
				System.out.print(k.getId());
			}
			System.out.print("|"+k.getIme()+"|"+k.getPrezime()+"|");
			if (email) {
				System.out.print(k.getEmail());
			}
			if (datumRodjenja) {
				System.out.print("|"+k.getDatumRodjenja());
			}
			if (spol) {
				boolean s = k.isSpol();
				System.out.print("|");
				System.out.print(s?"musko":"zensko");
			}
			if (obitelj) {
				Obitelj o = k.getObitelj();
				System.out.print("|" +o.getObiteljskoPrezime());
			}
			System.out.println();
		}
	}
	
	public int[] popisId() {
		int[] sifre = new int[korisnici.size()];
		for (int i=0; i<korisnici.size(); i++) {
			Korisnik k = korisnici.get(i);
			sifre[i] = k.getId();
		}
		return sifre;
	}
	
	private void promjenaKorisnik() {
		pregledKorisnik();
		int index = Pomocno.unosBroja("Unesite broj korisnika iz tablice kojeg zelite promjeniti ", "Broj mora biti cijelobrojan iz tablice ", 1, korisnici.size());
		Korisnik  noviKorisnik = korisnici.get(index-1);
		int noviId;
		int[] sifre = popisId();
		
		while (true) {
			boolean fact=true;
			noviId = Pomocno.unosBroja("Unesite novi id ", "Error! Mora bit pozitivan cijelobrojan broj ", 1, Integer.MAX_VALUE);
			for (int id : sifre) {
				if (id==noviId) {
					System.out.println("Ovo je vec postojeci id");
					fact=false;
				}	
			}
			if (fact) {
				break;
			}
		}
		noviKorisnik.setId(noviId);
		noviKorisnik.setIme(Pomocno.unosStringa("Unesi ime novog korisnika ", "Pogreska "));
		noviKorisnik.setPrezime(Pomocno.unosStringa("Unesi prezime novog korisnika ", "Pogreska "));
		noviKorisnik.setEmail(Pomocno.unosStringa("Unesi email novog korisnika ", "Pogreska "));
		noviKorisnik.setDatumRodjenja(Pomocno.unosDatuma("Unesi datum rodjenja novog korisnika "));
		noviKorisnik.setSpol(Pomocno.unosBoolean("Musko ili zensko? ", "Unesite 'musko' ili 'zensko' (caps lock nije bitan)","musko","zensko"));
		noviKorisnik.setObitelj(postaviObitelj());
	}
	
	public void brisanjeKorisnik() {
		pregledKorisnik();
		int index = Pomocno.unosBroja("Unesite broj korisnika iz tablice kojeg zelite obrisati ", "Broj mora biti cijelobrojan iz tablice ", 1, korisnici.size());
		korisnici.remove(index-1);
	}
	
	
}
