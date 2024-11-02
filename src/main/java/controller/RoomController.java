package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Room;
import model.Shelf;
import service.RoomService;
import service.ShelfService;

public class RoomController {

	private ShelfService shelfService = new ShelfService();
	
	public static void main(String[] args) {
		Scanner scanner  = new Scanner(System.in);
		Room room = new Room();
		System.out.println("Register a new room");
		System.out.print("Enter the room code: ");
		room.setRoomCode(scanner.nextLine());
		
		System.out.println(new RoomService().save(room));
	}
	
	public void checkBooksInRoom() {
		try {
			Scanner scanner = new Scanner(System.in);
			List<Room> rooms = new RoomService().getAll();
			int count = 0;
			HashMap<Integer, Room> roomMap = new HashMap<Integer, Room>();
			System.out.println("Choose the room: ");
			for(Room rm: rooms) {
				count++;
				roomMap.put(count, rm);
				System.out.println(count +". "+rm.getRoomCode()+ " "+rm.getShelves().size()+" shelves");
			}
			
			Room room = roomMap.get(scanner.nextInt());
			int booksCount = 0;
			for(Shelf sh: room.getShelves()) {
				booksCount += sh.getBooks().size();
			}
			scanner.close();
			System.out.println("There are "+ booksCount + " books in Room: "+room.getRoomCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Room getRoomWithFewBooks() {
		try {
			List<Room> rooms = new RoomService().getAll();
			int count = 0;
			HashMap<Integer, Room> roomMap = new HashMap<Integer, Room>();
			Room fwRoom = rooms.getFirst();
			int prevCount =0;
			for(Room rm: rooms) {
				int booksCount = 0;
				for(Shelf sh: rm.getShelves()) {
					booksCount += sh.getBooks().size();
				}
				if(booksCount < prevCount) {
					fwRoom = rm;
				}
			}
			
			return fwRoom;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
