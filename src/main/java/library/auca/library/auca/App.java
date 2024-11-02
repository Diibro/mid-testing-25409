package library.auca.library.auca;

import controller.BorrowerController;
import controller.RoomController;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
        System.out.println( "Welcome to Auca Library Management" );
        new RoomController().checkBooksInRoom();
        System.out.println("the room with few books: "+new RoomController().getRoomWithFewBooks().getRoomCode());
        new BorrowerController().returnBook();
    }
    
}
