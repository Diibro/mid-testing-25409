package model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import util.enums.EMembershipStatus;

@Entity()
public class Membership {
	@Id
	@Column(name="membership_id")
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID membershipId;
	
	@Column(name="membership_code")
	private String membershipCode;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name="membership_status")
	private EMembershipStatus membershipStatus;
	
	@Column(name="expiry_date")
	private Date expiringDate;
	
	@ManyToOne
	@JoinColumn(name="reader_id")
	private User reader;
	
	@ManyToOne
	@JoinColumn(name="membership_type_id")
	private MembershipType membershipType;

	public Membership() {
		super();
	}

	public Membership(String membershipCode, Date registrationDate, EMembershipStatus membershipStatus,
			Date expiringDate, User reader, MembershipType membershipType) {
		super();
		this.membershipCode = membershipCode;
		this.registrationDate = registrationDate;
		this.membershipStatus = membershipStatus;
		this.expiringDate = expiringDate;
		this.reader = reader;
		this.membershipType = membershipType;
	}

	public UUID getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(UUID membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipCode() {
		return membershipCode;
	}

	public void setMembershipCode(String membershipCode) {
		this.membershipCode = membershipCode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public EMembershipStatus getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(EMembershipStatus membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public Date getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	public User getReader() {
		return reader;
	}

	public void setReader(User reader) {
		this.reader = reader;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	
	
}
