package zavrsni.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Obitelj extends Entitet{
	
        @Column(nullable = false)
	private String obiteljskoPrezime;

	@OneToMany(mappedBy = "Obitelj")
	private List<Korisnik> clanovi = new ArrayList<>();
	
	public Obitelj(Integer id, String obiteljskoPrezime) {
		super(id);
		this.obiteljskoPrezime = obiteljskoPrezime;
	}

	public Obitelj() {
		super();
	}

	public String getObiteljskoPrezime() {
		return obiteljskoPrezime;
	}

	public void setObiteljskoPrezime(String obiteljskoPrezime) {
		this.obiteljskoPrezime = obiteljskoPrezime;
	}

	public List<Korisnik> getClanovi() {
		return clanovi;
	}

	public void setClanovi(List<Korisnik> clanovi) {
		this.clanovi = clanovi;
	}

}
