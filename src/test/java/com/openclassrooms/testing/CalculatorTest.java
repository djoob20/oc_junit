package com.openclassrooms.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

public class CalculatorTest {

	private Calculator calculatorUnderTest;
	private static Instant startedAt;

	@BeforeEach
	public void initCalculator(){
		calculatorUnderTest = new Calculator();
		System.out.println("Avant chaque test...........");
	}

	@AfterEach
	public void undefCalculator(){
		calculatorUnderTest = null;
		System.out.println("Apres chaque test...........");
	}

	@BeforeAll
	public static void initStartingTime(){
		System.out.println("Avant tous les tests..........");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration(){
		System.out.println("Apres tous les tests..........");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des test: {0} ms", duration));
	}
	@Test
	public void testAddTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;

		// Act
		int somme = calculatorUnderTest.add(a, b);

		// Assert
		assertEquals(5, somme);
	}

	@Test
	public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
		// Arrange
		int a = 42;
		int b = 11;

		// Act
		int produit = calculatorUnderTest.multiply(a, b);

		// Assert
		assertEquals(462, produit);
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à zéro}")
	@ValueSource(ints = {1, 3, 56, 675, 9})
	public void testMultiplyWithZero(int value){
		//arrange

		//act
		final int actualResult = calculatorUnderTest.multiply(value, 0);

		//assert
		assertEquals(0, actualResult);
	}

	@ParameterizedTest(name="{0} + {1} doit être égal à {2}")
	@CsvSource({"1,1,2", "2,4,6", "8,4,12"})
	public void testAddition(int arg1, int arg2, int epectedResult){
		//arrange
		//act
		int actualResult = calculatorUnderTest.add(arg1, arg2);
		//assert
		assertEquals(actualResult, epectedResult);

	}

	@Timeout(3)
	@Test
	public void testLongCalculation(){
		calculatorUnderTest.longCalculation();
	}

}
