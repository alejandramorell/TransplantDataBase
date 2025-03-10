package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


public class Staff implements Serializable {

	
	private static final long serialVersionUID = 3276117047544912872L;
	private Integer id; 
	private String name; 
	private String surname;
	private String field;
	private Patient patient;
	//private int idPaciente o private Paciente paciente
	
	public Staff() {
		super();
	}


	public Staff(Integer id, String name, String surname, String field) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname; 
		this.field = field; 
	}


	public Staff(String name, String surname, String field) {
		super();
		this.name = name;
		this.surname = surname; 
		this.field = field; 
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	@Override
	public String toString() {
		return "Surgeon [id=" + id + ", name=" + name + ", surname=" + surname + ", field=" + field + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
