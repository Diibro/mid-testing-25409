package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import util.enums.ELocationType;

public class TestLocationController {

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
	public void testCreate() {
		System.out.println("Testing LocationController creation");
		String result = new LocationController().create();
		System.out.println(result);
		assertEquals("Location Saved Successfully", result);
	}

	@Test
	public void testGetProvinceByVillage() {
		assertEquals(
				new LocationController().getProvinceByVillage().getLocationType(),
				ELocationType.PROVINCE
				);
	}

}
