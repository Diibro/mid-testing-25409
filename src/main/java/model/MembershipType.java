package model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity()
public class MembershipType {
	@Id
	@Column(name="membership_type")
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID membershipTypeId;
	
	@Column(name="membeshrip_name")
	private String membershipName;
	
	@Column(name="max-books")
	private int maxBooks;
	
	@Column(name="price")
	private int price;
	
	@OneToMany
	private List<Membership> memberships;

	public MembershipType() {
		super();
	}

	public MembershipType(String membershipName, int maxBooks, int price) {
		super();
		this.membershipName = membershipName;
		this.maxBooks = maxBooks;
		this.price = price;
	}

	public UUID getMembershipTypeId() {
		return membershipTypeId;
	}

	public void setMembershipTypeId(UUID membershipTypeId) {
		this.membershipTypeId = membershipTypeId;
	}

	public String getMembershipName() {
		return membershipName;
	}

	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}

	public int getMaxBooks() {
		return maxBooks;
	}

	public void setMaxBooks(int maxBooks) {
		this.maxBooks = maxBooks;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}
	
	
	
}
