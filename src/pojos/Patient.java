package pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = -6037002940808004660L;
	
	private Integer id;
	private String sex;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private String pathology;
	private String diagnosis;
    
	public Patient() {
		super();
	}
	
	public Patient(Integer id, String sex, String name, String surname, Date dateOfBirth, String pathology, 
			String diagnosis) {
		super();
		this.id = id;
		this.sex = sex;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.pathology = pathology;
		this.diagnosis = diagnosis;
	
	}

	public Patient(String sex, String name, String surname, Date dateOfBirth, String pathology, 
			String diagnosis) {
		super();
		this.sex = sex;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.pathology = pathology;
		this.diagnosis = diagnosis;
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPathology() {
		return pathology;
	}
	public void setPathology(String pathology) {
		this.pathology = pathology;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", sex=" + sex + ", name=" + name + ", surname=" + surname + ", dateOfBirth="
				+ dateOfBirth + ", pathology=" + pathology + ", diagnosis=" + diagnosis + "]";
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
		Patient other = (Patient) obj;
		return Objects.equals(id, other.id);
	}	
	
}