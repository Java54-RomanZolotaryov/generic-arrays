package telran.employees.test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.employees.Company;
import telran.employees.Employee;

class CompanyTest {
	private static final long ID1 = 123;
	private static final long ID2 = 120;
	private static final long ID3 = 125;
	private static final long ID4 = 122;
	private static final int SALARY1 = 1000;
	private static final int SALARY2 = 2000;
	private static final int SALARY3 = 3000;
	private static final int SALARY4 = 2500;
	private static final String DEPARTMENT1 = "Development";
	private static final String DEPARTMENT2 = "QA";
	private static final String DEPARTMENT3 = "Directors";
	
	Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
	Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
	Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
	Employee empl4 = new Employee(ID4, SALARY4, DEPARTMENT1);
	
	Company company;
	
	@BeforeEach
	void intCompany() {
		company = new Company(new Employee[] {empl1, empl2, empl3});
	}

	@Test
	void testAddEmployee() {
		assertThrowsExactly(NullPointerException.class, () -> company.addEmployee(null));
		assertThrowsExactly(IllegalStateException.class, () -> company.addEmployee(empl1));
		assertNull(company.getEmployee(ID4));
		company.addEmployee(empl4);
		assertEquals(empl4, company.getEmployee(ID4));
	}

	@Test
	void testGetEmployee() {
		assertEquals(empl1, company.getEmployee(ID1));
		assertEquals(empl2, company.getEmployee(ID2));
		assertEquals(empl3, company.getEmployee(ID3));
		assertNull(company.getEmployee(ID4));
	}

	@Test
	void testRemoveEmployee() {
		assertEquals(empl1, company.removeEmployee(ID1));
		assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(ID1));
		assertEquals(empl2, company.removeEmployee(ID2));
		assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(ID2));
		assertEquals(empl3, company.removeEmployee(ID3));
		assertThrowsExactly(NoSuchElementException.class, () -> company.removeEmployee(ID3));
	}

	@Test
	void testGetDepartmentBudget() {
		assertEquals(3000, company.getDepartmentBudget(DEPARTMENT1));
		assertEquals(3000, company.getDepartmentBudget(DEPARTMENT2));
		assertEquals(0, company.getDepartmentBudget(DEPARTMENT3));
		assertEquals(0, company.getDepartmentBudget("    "));
		assertEquals(0, company.getDepartmentBudget(null));
	}

	@Test
	void testIterator() {
		Iterator<Employee> iterator = company.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(empl2, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(empl1, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(empl3, iterator.next());
		assertFalse(iterator.hasNext());
		assertThrowsExactly(NoSuchElementException.class, () -> iterator.next());
	}

}
