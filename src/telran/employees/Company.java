package telran.employees;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import telran.util.Arrays;

public class Company implements Iterable<Employee>{
	
	private final Comparator<Employee> naturalOrderComparator = Employee::compareTo;
	
	private Employee[] employees;
	
	public Company(Employee[] employees) {
		super();
		this.employees = Arrays.copy(Objects.requireNonNull(employees));
		Arrays.bubbleSort(this.employees, naturalOrderComparator);
	}
	
	public void addEmployee(Employee employee) {
		Objects.requireNonNull(employees, "Employee cannot be null!");
		if (Arrays.binarySearch(employees, employee, naturalOrderComparator) > -1) {
			throw new IllegalStateException(String.format("Employee with '%d' already exists!", employee.getId()));
		}
		employees = Arrays.insertSorted(employees, employee, Employee::compareTo);
	}
	
	public Employee getEmployee(long id) {
		int indexOfElement = Arrays.indexOfSortedArray(employees, new Employee(id, 0, ""), naturalOrderComparator);
		return indexOfElement < 0 ? null : employees[indexOfElement];
	}
	
	public Employee removeEmployee(long id) {
		int indexOfElement = Arrays.indexOfSortedArray(employees, new Employee(id, 0, ""), naturalOrderComparator);
		if (indexOfElement < 0) {
			throw new NoSuchElementException(String.format("Employee with '%d' doesn't exist!", id));
		}
		Employee removedEmployee = employees[indexOfElement];
		employees = Arrays.removeByIndex(employees, indexOfElement);
		return removedEmployee;
	}
	
	public long getDepartmentBudget(String department) {
		long sumOfDepartmentSalaries = 0L;
		if (department != null && !department.isBlank()) {
			for (Employee employee : this) {
				if (employee.getDepartment().equals(department)) {
					sumOfDepartmentSalaries += employee.getBasicSalary();
				}
			}
		}
		return sumOfDepartmentSalaries;
	}

	@Override
	public Iterator<Employee> iterator() {
		return new Iterator<Employee>() {

			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < employees.length;
			}

			@Override
			public Employee next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return employees[index++];
			}
			
		};
	}
}
