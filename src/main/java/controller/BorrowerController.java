package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Book;
import model.Borrower;
import model.Membership;
import model.User;
import service.BookService;
import service.BorrowerService;
import service.UserService;

public class BorrowerController {
	
	public String borrowBook() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("enter your id: ");
			User user = new UserService().getById(scanner.nextLine());
			if(user == null) {
				scanner.close();
				return "First register an account please";
			}
			
			if(user.getMemberships().size() == 0) {
				scanner.close();
				return "First buy a membership please";
			}
			
			Membership userMembership = user.getMemberships().getFirst();
			if(userMembership == null) {
				scanner.close();
				return "Your membership has expired";
			}
			
			Borrower newBorrower = new Borrower();
			newBorrower.setUser(user);
			LocalDate regiDate = LocalDate.now();
			newBorrower.setPickDate(Date.from(regiDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			System.out.println("Enter how days to spend with book: ");
			int daysNo = scanner.nextInt();
			
			LocalDate expirationDate = regiDate.plus(daysNo, ChronoUnit.DAYS);
			
			newBorrower.setDueDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			newBorrower.setReturnDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
			List<Book> books = new BookService().getAll();
			if(books.size()==0) {
				scanner.close();
				return "no Books available";
			}
			
			int bkCount = 0;
			HashMap<Integer, Book> booksMap = new HashMap<Integer, Book>();
			System.out.println("Please the book you would to borrow: ");
			for(Book bk: books) {
				if(bk.getShelf().getAvailableStock() > 0) {
					bkCount++;
					booksMap.put(bkCount, bk);
					System.out.println(bkCount +". "+ bk.getTitle() + " "+ bk.getPublisherName());
				}
			}
			
			if(bkCount == 0) {
				System.out.println("Opps all books have been borrowed!!");
				return "All books borrowed";
			}
			
			newBorrower.setBook(booksMap.get(scanner.nextInt()));
			
			newBorrower.setFine(0);
			
			scanner.close();
			return new BorrowerService().save(newBorrower);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error recording new borrower";
		}
	}
	
	public String canBorrowMoreBooks() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter user id: ");
			User user = new UserService().getById(scanner.nextLine());
			
			if(user == null) {
				scanner.close();
				return "User not found";
			}

			Membership currentMembership = user.getMemberships().getFirst();
			if(currentMembership.getMembershipType().getMaxBooks() > user.getBorrowers().size()) {
				scanner.close();
				return "user can borrow more books";
			}else {
				scanner.close();
				return "user cannot borrow more books";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error checking if the user can borrow more more books";
		}
	}
	
	public void returnBook () {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter the user id: ");
			String userId = scanner.nextLine();
			User user = new UserService().getById(userId);
			if(user == null) {
				scanner.close();
				System.out.println("User not found");
				return;
			}
			
			if(user.getBorrowers().size() == 0) {
				scanner.close();
				System.out.println("User did not borrow any book");
				return;
			}
			List<Borrower> userBorrowers = user.getBorrowers();
			
			System.out.print("Please enter the date return date the fines(dd/MM/yy): ");
			String dateStr = scanner.nextLine();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	        dateFormat.setLenient(false);
	        Date returnDate = dateFormat.parse(dateStr);

	        LocalDate returnLocalDate = returnDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        
	        int brCount = 0;
	        HashMap<Integer, Borrower> borrowerMap = new HashMap<Integer, Borrower>();
	        System.out.println("Please select the book being returned: ");
	        for(Borrower borrower : userBorrowers) {
	        	brCount++;
	        	borrowerMap.put(brCount, borrower);
	        	System.out.println(brCount + ". "+"Date: "+ borrower.getPickDate().toString() + " bookName: " + borrower.getBook().getTitle());
	        }
	        
	        Borrower borrower = borrowerMap.get(scanner.nextInt());

	        Date dueDate = borrower.getDueDate();
            LocalDate dueLocalDate = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            if (dueLocalDate.isBefore(returnLocalDate)) {
                long daysExceeded = ChronoUnit.DAYS.between(dueLocalDate, returnLocalDate);
                int fine = (int) daysExceeded * user.getMemberships().getFirst().getMembershipType().getPrice();
                System.out.println("Borrowed book: " + borrower.getBook().getTitle() + ", Fine: rwf " + fine);
                
                // Update the borrower fine (assuming there's a setFine method in Borrower class)
                borrower.setFine(fine);
            } else {
                System.out.println("Borrower ID: " + borrower.getId() + " has no fine.");
                borrower.setFine(0);
            }

		} catch(ParseException ex) {
			System.out.println("Enter invalid date");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Seems you entered invalid date data");
		}
	}
}
