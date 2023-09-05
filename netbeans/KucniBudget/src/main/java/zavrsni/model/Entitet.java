package zavrsni.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entitet {
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public Entitet() {
		super();
	}

	public Entitet(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
