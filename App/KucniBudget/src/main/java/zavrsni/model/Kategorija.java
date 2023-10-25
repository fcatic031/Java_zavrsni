package zavrsni.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Kategorija extends Entitet{
	
	@Column(nullable = false)
	private String naziv;

	@OneToMany(mappedBy = "kategorija")
	private List<DnevnaPotrosnja> potrosnje = new ArrayList<>();
	
	public Kategorija() {
		super();
	}

	public Kategorija(Integer id, String naziv) {
		super(id);
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public List<DnevnaPotrosnja> getPotrosnje() {
		return potrosnje;
	}

	public void setPotrosnje(List<DnevnaPotrosnja> potrosnje) {
		this.potrosnje = potrosnje;
	}

	@Override
	public String toString() {
		return naziv;
	}
}
