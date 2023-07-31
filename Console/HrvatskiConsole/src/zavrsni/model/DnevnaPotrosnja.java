package zavrsni.model;

import java.util.Date;

public class DnevnaPotrosnja extends Entitet{
	
	private Korisnik korisnik;
	private Kategorija kategorija;
	private Date datum;
	private float potrosnja;
	
	public DnevnaPotrosnja() {
		super();
	}
	public DnevnaPotrosnja(int id, Korisnik korisnik, Kategorija kategorija, Date datum, float potrosnja) {
		super(id);
		this.korisnik = korisnik;
		this.kategorija = kategorija;
		this.datum = datum;
		this.potrosnja = potrosnja;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Kategorija getKategorija() {
		return kategorija;
	}
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public float getPotrosnja() {
		return potrosnja;
	}
	public void setPotrosnja(float potrosnja) {
		this.potrosnja = potrosnja;
	}
	
	
}
