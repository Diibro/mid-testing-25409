package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import util.enums.ELocationType;

@Entity()
public class Location {
	@Id
	@Column(name="location_id")
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID locationId;
	
	@Column(name="location_code")
	private String locationCode;
	
	@Column(name="location_name")
	private String locationName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="location_type")
	private ELocationType locationType;
	
	@ManyToOne
	@JoinColumn(name="parent_id", referencedColumnName = "location_id")
	private Location parentLocation;
	
	@OneToMany(mappedBy="parentLocation")
	private List<Location> locations;
	
	@OneToMany(mappedBy="village", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<User>();
	
	

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(String locationCode, String locationName, ELocationType locationType, Location parentLocation) {
		super();
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.locationType = locationType;
		this.parentLocation = parentLocation;
	}

	public Location(String locationCode, String locationName, ELocationType locationType) {
		super();
		this.locationCode = locationCode;
		this.locationName = locationName;
		this.locationType = locationType;
	}

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public ELocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(ELocationType locationType) {
		this.locationType = locationType;
	}

	public Location getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(Location parentLocation) {
		this.parentLocation = parentLocation;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationCode=" + locationCode + ", locationName="
				+ locationName + ", locationType=" + locationType + "]";
	}
	
	
}
