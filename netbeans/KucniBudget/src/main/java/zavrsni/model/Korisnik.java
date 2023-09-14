package zavrsni.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Korisnik extends Osoba{

        @ManyToOne
	private Obitelj obitelj;
	
	
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
	
	
	
	
}
