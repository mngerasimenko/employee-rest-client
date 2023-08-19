package ru.mngerasimenko;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mngerasimenko.configuration.AppConfig;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Communication communication = context.getBean("communication", Communication.class);

		System.out.println(communication.getAllEmployees());
		System.out.println(communication.getEmployee(6));

		//Employee employee = new Employee("Sveta", "Sokolova", "Sales", 780);
		//Employee employee = communication.getEmployee(12);
		//employee.setSalary(1000);
		//communication.saveEmployee(employee);

		//System.out.println(communication.getAllEmployees());

		//communication.deleteEmployee(12);
	}
}
