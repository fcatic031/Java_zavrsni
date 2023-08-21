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
		Pomocno.naslovSredina("KORISNIK", "+","||", 30);
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
		switch (Pomocno.unosBroja("Unesi broj stavke ", "Između 1 i 5", 1, 6)) {
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
		case 6:
			Pomocno.naslovSredina("DOVIDJENJA", "+", "||", 30);
			break;
		case 5:
			System.out.println("STAT");
			prikaziIzbornik();
			break;
		}
		
	}

	private void unosKorisnik() {
		Korisnik k= new Korisnik();
		//Poseban ID
		int noviId=0;
		int[] sifre = popisId();
		Pomocno.unosIdPetlja(noviId,sifre);
		k.setId(noviId);
		//k.setId(Pomocno.unosBroja("Unesi novi id ", "Pozitivan broj", 1, Integer.MAX_VALUE));
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
		Pomocno.naslovSredina("", "+","||", 30);
		Pomocno.naslovSredina("KORISNICI", "+","||", 30);
		Pomocno.naslovSredina("", "+","||", 30);
		for (Korisnik k: korisnici) {
			Pomocno.naslovSredina(b++ + ") "+k.getIme()+" "+k.getPrezime(), " ", "||", 30);
			//System.out.println(b++ + ") "+k.getIme()+" "+k.getPrezime());
		}
		Pomocno.naslovSredina("", "+","||", 30);
	}
	
	public void pregledKorisnikOpcije() {
		System.out.println("Zahvaljuci navedenim opcijama mozete birati sto zelite vidjeti");
		boolean id=Pomocno.unosBoolean("id? ", "da ili ne","da","ne");
		boolean email=Pomocno.unosBoolean("email? ", "da ili ne","da","ne");
		boolean datumRodjenja=Pomocno.unosBoolean("datum rodjenja? ", "da ili ne","da","ne");
		boolean spol=Pomocno.unosBoolean("spol? ", "da ili ne","da","ne");
		boolean obitelj=Pomocno.unosBoolean("obitelj? ", "da ili ne","da","ne");
		int sirina=44;
		if (id) {
			//System.out.print("id|");
			Pomocno.tablicaSredina("ID", " ", "|", 10);
			sirina+=12;
		}
		//System.out.print("ime|");
		//System.out.print("prezime|");
		Pomocno.tablicaSredina("Ime", " ", "|", 20);
		Pomocno.tablicaSredina("Prezime", " ", "|", 20);
		if (email) {
			//System.out.print("email|");
			Pomocno.tablicaSredina("E-mail", " ", "|", 20);
			sirina+=22;
		}
		if (datumRodjenja) {
			//System.out.print("Datum rodjenja|");
			Pomocno.tablicaSredina("Datum rodjenja", " ", "|", 20); //20
			sirina+=22; //22
		}
		if (spol) {
//			System.out.print("Spol|");
			Pomocno.tablicaSredina("Spol", " ", "|", 8);
			sirina+=10;
		}
		if (obitelj) {
			//System.out.print("Obitelj|");
			Pomocno.tablicaSredina("Obitelj", " ", "|", 20);
			sirina+=22;
		}
		System.out.println();
		Pomocno.naslovSredina("", "=", "", sirina);
		for (Korisnik k: korisnici) {
			if (id) {
				//System.out.print(k.getId());
				Pomocno.tablicaSredina(""+k.getId(), " ", "|", 10);
			}
			//System.out.print("|"+k.getIme()+"|"+k.getPrezime()+"|");
			Pomocno.tablicaSredina(k.getIme(), " ", "|", 20);
			Pomocno.tablicaSredina(k.getPrezime(), " ", "|", 20);
			if (email) {
				//System.out.print(k.getEmail());
				Pomocno.tablicaSredina(k.getEmail(), " ", "|", 20);
			}
			if (datumRodjenja) {
//				System.out.print("|"+k.getDatumRodjenja());
				Pomocno.tablicaSredina(k.getDatumRodjenja().getDate()+"."+(k.getDatumRodjenja().getMonth()+1)+"."+(k.getDatumRodjenja().getYear()+1900), " ", "|", 20);
			}
			if (spol) {
				boolean s = k.isSpol();
//				System.out.print("|");
//				System.out.print(s?"musko":"zensko");
				if (s) {
					Pomocno.tablicaSredina("Musko", " ", "|", 8);
				} else {
					Pomocno.tablicaSredina("Zensko", " ", "|", 8);
				}
				//s ? Pomocno.naslovSredina("Musko", " ", "|", 8) : Pomocno.naslovSredina("Zensko", " ", "|", 8);
			}
			if (obitelj) {
				Obitelj o = k.getObitelj();
				//System.out.print("|" +o.getObiteljskoPrezime());
				Pomocno.tablicaSredina(o.getObiteljskoPrezime(), " ", "|", 20);
			}
			System.out.println();
		}
		Pomocno.naslovSredina("", "=", "", sirina);
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
		//Poseban ID
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
		Korisnik noviKorisnik2 = new Korisnik();
		noviKorisnik2.setId(noviKorisnik.getId());
		noviKorisnik2.setIme(noviKorisnik.getIme());
		noviKorisnik2.setPrezime(noviKorisnik.getPrezime());
		noviKorisnik2.setDatumRodjenja(noviKorisnik.getDatumRodjenja());
		noviKorisnik2.setEmail(noviKorisnik.getEmail());
		noviKorisnik2.setObitelj(noviKorisnik.getObitelj());
		noviKorisnik2.setSpol(noviKorisnik.isSpol());
		noviKorisnik.setId(noviId);
		noviKorisnik.setIme(Pomocno.unosStringa("Unesi ime novog korisnika ", "Pogreska "));
		noviKorisnik.setPrezime(Pomocno.unosStringa("Unesi prezime novog korisnika ", "Pogreska "));
		noviKorisnik.setEmail(Pomocno.unosStringa("Unesi email novog korisnika ", "Pogreska "));
		noviKorisnik.setDatumRodjenja(Pomocno.unosDatuma("Unesi datum rodjenja novog korisnika "));
		noviKorisnik.setSpol(Pomocno.unosBoolean("Musko ili zensko? ", "Unesite 'musko' ili 'zensko' (caps lock nije bitan)","musko","zensko"));
		noviKorisnik.setObitelj(postaviObitelj());
		boolean potvrda = Pomocno.unosBoolean("Zelite li dalje zadrzati promjene? ", "Pogreska! da ili ne?", "da", "ne");
		if (potvrda==false) {
			noviKorisnik.setId(noviKorisnik2.getId());
			noviKorisnik.setIme(noviKorisnik2.getIme());
			noviKorisnik.setPrezime(noviKorisnik2.getPrezime());
			noviKorisnik.setDatumRodjenja(noviKorisnik2.getDatumRodjenja());
			noviKorisnik.setEmail(noviKorisnik2.getEmail());
			noviKorisnik.setObitelj(noviKorisnik2.getObitelj());
			noviKorisnik.setSpol(noviKorisnik2.isSpol());
			boolean potvrda2 = Pomocno.unosBoolean("Zelite li dalje napraviti neku promjenu? ", "Pogreska! da ili ne?", "da", "ne");
			if (potvrda2) {
				promjenaKorisnik();
			}
		}
	}
	
	public void brisanjeKorisnik() {
		pregledKorisnik();
		int index = Pomocno.unosBroja("Unesite broj korisnika iz tablice kojeg zelite obrisati ", "Broj mora biti cijelobrojan iz tablice ", 1, korisnici.size());
		korisnici.remove(index-1);
	}
	
	
	
	
}
