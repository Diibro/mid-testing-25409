package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import util.enums.EGender;
import util.enums.ERole;

@Entity
@Table(name="users")
public class User extends Person {
	
	@Column(name="user_name")
	private String username;
	
	@Column
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private ERole role;
	
	@ManyToOne
	@JoinColumn(name="village_id")
	private Location village;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Borrower> borrowers = new ArrayList<>();

	@OneToMany(mappedBy = "reader", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Membership> memberships = new ArrayList<>();

	public User() {
		super();
	}

	public User(String personId, String firstName, String lastName, EGender gender, String phoneNumber) {
		super(personId, firstName, lastName, gender, phoneNumber);
	}

	public User(String username, String password, ERole role, Location village) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.village = village;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	public Location getVillage() {
		return village;
	}

	public void setVillage(Location village) {
		this.village = village;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(ArrayList<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(ArrayList<Membership> memberships) {
		this.memberships = memberships;
	}
	
}
