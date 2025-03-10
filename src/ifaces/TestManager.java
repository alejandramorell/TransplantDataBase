package ifaces;

import java.util.List;

import pojos.Test;
import pojos.Treatment;

public interface TestManager {

	public void insertTest(Test test);

	public void insertTreatment(Treatment treatment);

	List<Treatment> searchTreatmentById(Integer id);

}
