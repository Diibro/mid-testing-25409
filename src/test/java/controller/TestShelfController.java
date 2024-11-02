package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestShelfController {

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
	public void testAssignShelfToRoom() {
		System.out.println("Testing the assign shelf to room method");
		String res = new ShelfController().assignShelfToRoom();
		System.out.println(res);
		assertEquals("Shelf save successfully", res);
	}

}
