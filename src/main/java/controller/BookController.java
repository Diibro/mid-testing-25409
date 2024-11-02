package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Book;
import model.Shelf;
import service.BookService;
import service.ShelfService;
import util.enums.EBookStatus;

public class BookController {
	
	public String assignBookToShelf() {
		try {
			Scanner scanner = new Scanner(System.in);
			Book book = new Book();
			book.setStatus(EBookStatus.AVAILABLE);
			System.out.print("Enter book title: ");
			book.setTitle(scanner.nextLine());
			System.out.print("Enter the edition: (number) ");
			book.setEdition(scanner.nextInt());
			scanner.nextLine();
			System.out.println("Enter the ISBNCode: ");
			book.setISBNCODE(scanner.nextLine());
			System.out.println("Enter the publication year: ");
			int year = scanner.nextInt();

			// Set the date to January 1st of the entered year
			LocalDate publicationDate = LocalDate.of(year, 1, 1);
			Date publicationYearDate = Date.from(publicationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

			book.setPublicationYear(publicationYearDate);
			scanner.nextLine();
			System.out.println("Enter the publisher name: ");
			book.setPublisherName(scanner.nextLine());
			
			List<Shelf> shelves = new ShelfService().getAll();
			if(shelves.size() == 0) {
				scanner.close();
				return "No shelves saved";
			}
			
			int count = 0;
			HashMap<Integer, Shelf> shelfMap = new HashMap<Integer, Shelf>();
			System.out.println("Please select the shelf to assign the book to: ");
			for(Shelf sh: shelves) {
				count++;
				shelfMap.put(count, sh);
				System.out.println(count+ ". "+sh.getBookCategory() + " in room - "+ sh.getRoom());
			}
			
			book.setShelf(shelfMap.get(scanner.nextInt()));
			scanner.close();
			String bkRes =  new BookService().save(book);
			if(bkRes == "New book saved") {
				Shelf bookShelf = book.getShelf();
				bookShelf.setAvailableStock(bookShelf.getAvailableStock() + 1);
				bookShelf.setInitialStock(bookShelf.getInitialStock() + 1);
				String shelfUpdate = new ShelfService().update(bookShelf);
				if(shelfUpdate == "Shelf updated successfully") {
					return bkRes;
				}else {
					return "Error updating the shelf";
				}
			}else {
				return "Error saving the new book";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error assigning book to shelf";
		}
	}
}
