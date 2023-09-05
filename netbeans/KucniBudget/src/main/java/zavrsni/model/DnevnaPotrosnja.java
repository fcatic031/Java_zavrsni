package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class DnevnaPotrosnja extends Entitet{
	
        @ManyToOne
	private Korisnik korisnik;
	@ManyToOne
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
