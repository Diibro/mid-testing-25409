package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity()
public class Room {
	@Id
	@Column(name="room_id")
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID roomId;
	
	@Column(name="room_code")
	private String roomCode;
	
	@OneToMany(mappedBy = "room",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Shelf> shelves;

	public Room() {
		super();
	}

	
	public Room(String roomCode) {
		super();
		this.roomCode = roomCode;
	}

	public UUID getRoomId() {
		return roomId;
	}

	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void setShelves(ArrayList<Shelf> shelves) {
		this.shelves = shelves;
	}

	
	
	
}
