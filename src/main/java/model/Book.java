package model;

import java.util.ArrayList;
import java.util.Date;
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
import util.enums.EBookStatus;

@Entity
public class Book {
	@Id
	@Column(name="book_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID bookId = UUID.randomUUID();
	
	@Column(name="title")
	private String title;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private EBookStatus status;
	
	@Column(name="edition")
	private int edition;
	
	@Column(name="ISBNCode")
	private String ISBNCODE;
	
	@Column(name="publication_year")
	private Date publicationYear;
	
	@Column(name="publisher_name")
	private String publisherName;
	
	@ManyToOne()
	@JoinColumn(name="shelf_id")
	private Shelf shelf;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Borrower> borrowers = new ArrayList<>();

	public Book() {
		super();
	}

	public Book(String title, int edition, String iSBNCODE, Date publicationYear, String publisherName, Shelf shelf) {
		super();
		this.title = title;
		this.edition = edition;
		ISBNCODE = iSBNCODE;
		this.publicationYear = publicationYear;
		this.publisherName = publisherName;
		this.shelf = shelf;
	}

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EBookStatus getStatus() {
		return status;
	}

	public void setStatus(EBookStatus status) {
		this.status = status;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getISBNCODE() {
		return ISBNCODE;
	}

	public void setISBNCODE(String iSBNCODE) {
		ISBNCODE = iSBNCODE;
	}

	public Date getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Date publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public List<Borrower> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(ArrayList<Borrower> borrowers) {
		this.borrowers = borrowers;
	}

}
