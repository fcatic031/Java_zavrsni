package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Korisnik extends Entitet{
	
	private String ime;
	private String prezime;
	private String email;
	private Date datumRodjenja;
	private boolean spol;
        @ManyToOne
	private Obitelj obitelj;
	
	
	public Korisnik() {
		super();
	}
	
	public Korisnik(int id, String ime, String prezime, String email, Date datumRodjenja, boolean spol,
			Obitelj obitelj) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.spol = spol;
		this.obitelj = obitelj;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	public boolean isSpol() {
		return spol;
	}
	public void setSpol(boolean spol) {
		this.spol = spol;
	}
	public Obitelj getObitelj() {
		return obitelj;
	}
	public void setObitelj(Obitelj obitelj) {
		this.obitelj = obitelj;
	}
	
	
	
	
}
