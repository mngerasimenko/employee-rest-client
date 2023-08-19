package ru.mngerasimenko;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mngerasimenko.entity.Employee;

import java.util.List;


@Component
public class Communication {
	private static final String URL = "http://localhost:8080/employee-rest/api/employees";

	private final RestTemplate restTemplate;

	@Autowired
	public Communication(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	public List<Employee> getAllEmployees() {
		ResponseEntity<List<Employee>> responseEntity =
				restTemplate.exchange(URL, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<Employee>>() {
						});

		return responseEntity.getBody();
	}

	public Employee getEmployee(int id) {
		return restTemplate.getForObject(URL + "/" + id, Employee.class);
	}

	public void saveEmployee(Employee employee) {
		if (employee.getId() == 0) {
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
			System.out.println("New employee was added to db");
			System.out.println(responseEntity.getBody());
		} else {
			restTemplate.put(URL, employee);
			System.out.println("Employee with ID = " + employee.getId() + " was updated");
		}
	}

	public void deleteEmployee(int id) {
		restTemplate.delete(URL + "/" + id);
		System.out.println("Employee with ID = " + id + " was deleted");
	}
}
