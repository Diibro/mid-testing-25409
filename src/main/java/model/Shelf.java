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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity()
public class Shelf {
	@Id
	@Column(name="sheld_id")
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID shelfId;
	
	@Column(name="initial_stock")
	private int initialStock;
	
	@Column(name="borrowed_number")
	private int borrowedNumber;
	
	@Column(name="book_category")
	private String bookCategory;
	
	@Column(name="available_stock")
	private int availableStock;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	
	@OneToMany(mappedBy="shelf", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Book> books;
	
	
	public Shelf() {
		super();
	}


	public Shelf(int initialStock, int borrowedNumber, String bookCategory, int availableStock, Room room) {
		super();
		this.initialStock = initialStock;
		this.borrowedNumber = borrowedNumber;
		this.bookCategory = bookCategory;
		this.availableStock = availableStock;
		this.room = room;
	}


	public UUID getShelfId() {
		return shelfId;
	}


	public void setShelfId(UUID shelfId) {
		this.shelfId = shelfId;
	}


	public int getInitialStock() {
		return initialStock;
	}


	public void setInitialStock(int initialStock) {
		this.initialStock = initialStock;
	}


	public int getBorrowedNumber() {
		return borrowedNumber;
	}


	public void setBorrowedNumber(int borrowedNumber) {
		this.borrowedNumber = borrowedNumber;
	}


	public String getBookCategory() {
		return bookCategory;
	}


	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}


	public int getAvailableStock() {
		return availableStock;
	}


	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	
	
}
