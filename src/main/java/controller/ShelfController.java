package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Room;
import model.Shelf;
import service.RoomService;
import service.ShelfService;

public class ShelfController {
	private RoomService roomService = new RoomService();
	private ShelfService shelfService = new ShelfService();
	
	public String assignShelfToRoom() {
		try {
			Scanner scanner = new Scanner(System.in);
			Shelf newShelf = new Shelf();
			newShelf.setAvailableStock(0);
			newShelf.setBorrowedNumber(0);
			newShelf.setInitialStock(0);
			System.out.print("Enter books category: ");
			newShelf.setBookCategory(scanner.nextLine());
			
			List<Room> rooms = this.roomService.getAll();
			HashMap<Integer, Room> roomMap = new HashMap<Integer, Room>();
			int count = 0;
			System.out.print("Please select the room to assign the shelf: ");
			for(Room rm : rooms) {
				count++;
				roomMap.put(count, rm);
				System.out.print(count+". "+rm.getRoomCode());
			}
			
			newShelf.setRoom(roomMap.get(scanner.nextInt()));
			
			return this.shelfService.save(newShelf);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Error assigning shelf to room";
		}
	}
}
