package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Location;
import util.enums.ELocationType;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserController {

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
	public void test_1_CreateUser() {
		System.out.println("Testing user creation");
		
		assertEquals("User saved successfully", new UserController().createUser());
	}

	@Test
	public void test_2_GetUserLocation() {
		System.out.println("Testing get user province by user user id");
		Location location = new UserController().getUserLocation(); 
		 assertNotNull("Location should not be null", location);
	     assertTrue("Location type should be Province", location.getLocationType().equals(ELocationType.PROVINCE));
	}

	@Test
	public void test_3_Login() {
		System.out.println("Testing the user authentication");
		assertEquals("Authentication successful", new UserController().login());
	}

}
