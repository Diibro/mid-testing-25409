package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBorrowerController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBorrowBook() {
		System.out.println("Testing the borrow new book function");
		String res = new BorrowerController().borrowBook();
		System.out.println(res);
		assertEquals("Borrower saved successfully", res);
	}
	
	@Test
	public void testCanBorrowMoreBooks() {
		System.out.println("Testing if a user cannot borrow  more books or not");
		String res = new BorrowerController().canBorrowMoreBooks();
		System.out.println(res);
		assertEquals("user can borrow more books", res);
	}

}
