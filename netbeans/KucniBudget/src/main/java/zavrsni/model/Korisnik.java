package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Korisnik extends Entitet{

	private String ime;
	private String prezime;
	private String email;
	private Date datumRodjenja;
	private Boolean spol;
	//ULOGA -> false operater, true korisnik
	private Boolean uloga;
	private String lozinka;
	@ManyToOne
	private Obitelj obitelj;

	@OneToMany(mappedBy = "korisnik")
	private List<DnevnaPotrosnja> potrosnje;
	
	public Korisnik() {
		super();
	}

	public Korisnik(Integer id, String ime, String prezime, String email, Date datumRodjenja, Boolean spol,Boolean uloga,String lozinka,Obitelj obitelj) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.datumRodjenja = datumRodjenja;
		this.spol = spol;
		this.uloga = uloga;
		this.lozinka = lozinka;
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

	public Boolean getUloga() {
		return uloga;
	}

	public void setUloga(Boolean uloga) {
		this.uloga = uloga;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public Obitelj getObitelj() {
		return obitelj;
	}
	public void setObitelj(Obitelj obitelj) {
		this.obitelj = obitelj;
	}


	public List<DnevnaPotrosnja> getPotrosnje() {
		return potrosnje;
	}

	public void setPotrosnje(List<DnevnaPotrosnja> potrosnje) {
		this.potrosnje = potrosnje;
	}

	@Override
	public String toString() {
		return getIme() + " "+ getPrezime();
	}
}
