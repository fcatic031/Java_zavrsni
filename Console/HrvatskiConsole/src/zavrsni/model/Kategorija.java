package zavrsni.model;

public class Kategorija extends Entitet{
	
	private String naziv;
	
	public Kategorija() {
		super();
	}

	public Kategorija(int id, String naziv) {
		super(id);
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
