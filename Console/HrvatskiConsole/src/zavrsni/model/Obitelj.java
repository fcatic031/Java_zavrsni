package zavrsni.model;

public class Obitelj extends Entitet{
	
	private String obiteljskoPrezime;
	
	
	
	public Obitelj(int id, String obiteljskoPrezime) {
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
	
}
