package zavrsni.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entitet {
	
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Entitet() {
		super();
	}

	public Entitet(Integer id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
