package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class DnevnaPotrosnja extends Entitet{
	
        @ManyToOne
	private Korisnik korisnik;
	@ManyToOne
	private Kategorija kategorija;
	private Date datum;
	private BigDecimal potrosnja;
	
	public DnevnaPotrosnja() {
		super();
	}
	public DnevnaPotrosnja(Integer id, Korisnik korisnik, Kategorija kategorija, Date datum, BigDecimal potrosnja) {
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
	public BigDecimal getPotrosnja() {
		return potrosnja;
	}
	public void setPotrosnja(BigDecimal potrosnja) {
		this.potrosnja = potrosnja;
	}

	@Override
	public String toString() {
		Date date = getDatum();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy. ");
		String strDate = dateFormat.format(date);
		return strDate+" "+getKorisnik().getPrezime()+" "+getKorisnik().getIme()+"-->"+getPotrosnja().toString();
	}
}
