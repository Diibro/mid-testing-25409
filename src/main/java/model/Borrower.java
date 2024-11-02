package model;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity()
public class Borrower {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name="due_date")
	private Date dueDate;
	
	@Column(name="fine")
	private int fine;
	
	@Column(name="pick_date")
	private Date pickDate;
	
	@Column(name="return_date")
	private Date returnDate;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="reader_id")
	private User user;

	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Borrower(Date dueDate, Date pickDate, Date returnDate, Book book, User user) {
		super();
		this.dueDate = dueDate;
		this.pickDate = pickDate;
		this.returnDate = returnDate;
		this.book = book;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public Date getPickDate() {
		return pickDate;
	}

	public void setPickDate(Date pickDate) {
		this.pickDate = pickDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
