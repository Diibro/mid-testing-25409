package model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import util.enums.EGender;

@MappedSuperclass
public class Person {
	@Id
	@Column(name="person_id", nullable = false)
	private String personId;
	
	@Column(name="first_name")
	private  String firstName;
	
	@Column(name="last_name")
	private  String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private EGender gender;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	
	public Person() {
		super();
	}


	public Person(String personId, String firstName, String lastName, EGender gender, String phoneNumber) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}


	public String getPersonId() {
		return personId;
	}


	public void setPersonId(String personId) {
		this.personId = personId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public EGender getGender() {
		return gender;
	}


	public void setGender(EGender gender) {
		this.gender = gender;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
