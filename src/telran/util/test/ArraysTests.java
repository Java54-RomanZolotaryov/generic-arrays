package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import telran.util.Arrays;

class ArraysTests {
	Integer[] numbers = { 100, -3, 23, 4, 8, 41, 56, -7 };
	String[] strings = { "abc", "lmn", "123", null, "a" };
	String[] stringsMin = { "abc", "lmn", "123", "y" };

	@Test
	void indexOfTest() {
		assertEquals(1, Arrays.indexOf(strings, "lmn"));
		assertEquals(3, Arrays.indexOf(strings, null));
		assertEquals(-1, Arrays.indexOf(numbers, 150));
		assertEquals(4, Arrays.indexOf(numbers, 8));
	}

	@Test
	void minValueTest() {
		Comparator<String> compLength = (s1, s2) -> s1.length() - s2.length();
		assertEquals("y", Arrays.min(stringsMin, compLength));
		Comparator<String> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals("123", Arrays.min(stringsMin, compNative));
	}

	@Test
	void bubbleSortTest() {
		Integer[] expected = { 4, 8, 56, 100, 41, 23, -3, -7 };
		Integer[] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
		/* lambda expression example */
		// Comparator<Integer> evenOddComp = (num1, num2) ->
		// evenOddComparator(num1, num2);
		/*************************************/
		/* method reference */
		Comparator<Integer> evenOddComp = ArraysTests::evenOddComparator;
		/*********************************************/
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);

	}

	static int evenOddComparator(Integer num1, Integer num2) {
		// first even numbers sorted in the ascending order
		// last odd numbers sorted in the descending order
		boolean isNum1Even = num1 % 2 == 0;
		boolean isNum2Even = num2 % 2 == 0;
		int res = 1;
		if (isNum1Even && isNum2Even) {
			res = Integer.compare(num1, num2);
		} else if (!isNum1Even && !isNum2Even) {
			res = Integer.compare(num2, num1);
		} else if (isNum1Even) {
			res = -1;
		}
		return res;
	}

	@Test
	void searchTest() {
		Integer[] expectedEven = { 100, 4, 8, 56 };
		assertArrayEquals(expectedEven, Arrays.search(numbers, a -> a % 2 == 0));
		Integer[] expectedNegative = { -3, -7 };
		assertArrayEquals(expectedNegative, Arrays.search(numbers, a -> a < 0));

	}

	@Test
	void binarySearchTest() {
		Integer[] sortedNumbers = { 10, 20, 30, 40, 50 };
		Comparator<Integer> comp = Integer::compare;
		assertEquals(0, Arrays.binarySearch(sortedNumbers, 10, comp));
		assertEquals(4, Arrays.binarySearch(sortedNumbers, 50, comp));
		assertEquals(2, Arrays.binarySearch(sortedNumbers, 30, comp));
		assertEquals(-1, Arrays.binarySearch(sortedNumbers, 5, comp));
		assertEquals(-4, Arrays.binarySearch(sortedNumbers, 35, comp));
		assertEquals(-6, Arrays.binarySearch(sortedNumbers, 55, comp));
	}

	@Test
	void removeIfTest() {
		Integer[] expectedEven = { 100, 4, 8, 56 };
		assertArrayEquals(expectedEven, Arrays.removeIf(numbers, a -> a % 2 != 0));
		Integer[] expectedNegative = { -3, -7 };
		assertArrayEquals(expectedNegative, Arrays.removeIf(numbers, a -> a > 0));
	}

	@Test
	void addTest() {
		Integer[] expected = { 100, -3, 23, 4, 8, 41, 56, -7, 150 };
		Integer[] actual = Arrays.add(numbers, 150);
		assertArrayEquals(expected, actual);
	}

}

class Person {
	long id;
	int birthYear;

	public Person(long id, int birthYear) {
		this.id = id;
		this.birthYear = birthYear;
	}

}
