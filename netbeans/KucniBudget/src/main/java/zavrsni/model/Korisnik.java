package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Korisnik extends Osoba{

	@ManyToOne
	private Obitelj obitelj;

	@OneToMany(mappedBy = "korisnik")
	private List<DnevnaPotrosnja> potrosnje;
	
	public Korisnik() {
		super();
	}

	public Korisnik(Integer id, String ime, String prezime, String email, Date datumRodjenja, boolean spol, Obitelj obitelj) {
		super(id, ime, prezime, email, datumRodjenja, spol);
		this.obitelj = obitelj;
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
	
	
}
