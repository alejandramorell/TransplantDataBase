package pojos;

import java.io.Serializable;

import java.sql.Date;
import java.util.Objects;

public class Test implements Serializable{

	private static final long serialVersionUID = -3310068065836406234L;
	
	private Integer id;
	private String type;
	private String result;
	private Date dateOfTest;
	private Patient patient; //duda si utilizar el id del paciente como int o el paciente entero
	private Treatment treatmentId; //duda si utilizar el id del paciente como int o el paciente entero
	
	
	public Test(Integer id,String type, Date dateOfTest) {
		super();
		this.id = id;
		this.type = type;
		this.result = result;
		this.dateOfTest = dateOfTest;
		
	}
	
	
	public Test(String type, Date dateOfTest, Patient patient) {
		super();
		this.type = type;
		this.result = result;
		this.dateOfTest = dateOfTest;
		this.patient = patient;
		this.treatmentId = treatmentId;
	}

	public Test() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getDateOfTest() {
		return dateOfTest;
	}

	public void setDateOfTest(Date dateOfTest) {
		this.dateOfTest = dateOfTest;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	public Treatment getTreatmentId() {
		return treatmentId;
	}


	public void setTreatmentId(Treatment treatmentId) {
		this.treatmentId = treatmentId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
	@Override
	public String toString() {
		return "Test [id=" + id + ", type=" + type + ", date of test=" + dateOfTest + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return Objects.equals(id, other.id);
	}	

}
