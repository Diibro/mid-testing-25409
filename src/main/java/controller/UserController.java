package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Location;
import model.User;
import service.LocationService;
import service.UserService;
import util.enums.EGender;
import util.enums.ELocationType;
import util.enums.ERole;

public class UserController {
	
	private LocationService locationService = new LocationService();
	private UserService userService = new UserService();

	public String createUser() {
		try {
			User newUser = new User();
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Please enter the person id: ");
			newUser.setPersonId(scanner.nextLine());
			
			System.out.print("Please enter the first name: ");
			newUser.setFirstName(scanner.nextLine());
			
			System.out.print("Please enter the Last name: ");
			newUser.setLastName(scanner.nextLine());
			
			System.out.print("Please enter the user name ");
			newUser.setUsername(scanner.nextLine());
			
			System.out.print("Please enter the phone number: ");
			newUser.setPhoneNumber(scanner.nextLine());
			
			
			System.out.print("Please enter the password ");
			newUser.setPassword(scanner.nextLine());
			
			HashMap<Integer, EGender> genderHashMap = new HashMap<Integer, EGender>();
			genderHashMap.put(1, EGender.FEMALE);
			genderHashMap.put(2, EGender.MALE);
			
			System.out.println("Please select your gender: (select the number) ");
			System.out.println("1 : FEMALE");
			System.out.println("2 : MALE");
			newUser.setGender(genderHashMap.get(scanner.nextInt()));
			
			HashMap<Integer, ERole> roleHashMap = new HashMap<Integer, ERole>();
			roleHashMap.put(1, ERole.HOD);
			roleHashMap.put(2, ERole.DEAN);
			roleHashMap.put(3, ERole.MANAGER);
			roleHashMap.put(4, ERole.TEACHER);
			roleHashMap.put(5, ERole.STUDENT);
			
			System.out.println("Please select your role: (select the number) ");
			System.out.println("1 : HOD");
			System.out.println("2 : DEAN");
			System.out.println("3 : MANAGER");
			System.out.println("4 : TEACHER");
			System.out.println("5 : STUDENT");
			newUser.setRole(roleHashMap.get(scanner.nextInt()));
			
			List<Location> locations = this.locationService.getAllByType(ELocationType.VILLAGE);
			if(locations == null || locations.size() == 0) {
				System.out.println("/n No villages found.");
				scanner.close();
				return null;
			}else {
				System.out.println("/n Select the village, just type the number: ");
				HashMap<Integer, Location> villagesMap = new HashMap<Integer, Location>();
				int count = 0;
				for(Location loc : locations) {
					count++;
					villagesMap.put(count, loc);
					System.out.println("-- "+ count + " : "+ loc.toString());
				}
				
				int selectedCount = scanner.nextInt();
				newUser.setVillage(villagesMap.get(selectedCount));
			}
			
			scanner.close();
			return this.userService.save(newUser);
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Location getUserLocation() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Please the person id: ");
		String id = scanner.nextLine();
		
		scanner.close();
		
		User user = this.userService.getById(id);
		if(user != null) {
			Location loc = user.getVillage();
			while(loc.getLocationType() != ELocationType.PROVINCE) {
				loc = this.locationService.getById(loc.getLocationId()).getParentLocation();
			}
			System.out.println("User province name: "+ loc.getLocationName());
			return loc;
		}else {
			System.out.println("User not found");
			return null;
		}
	}
	
	public String login() {
		Scanner scanner = new Scanner(System.in);
		User user = new User();
		System.out.print("Username: ");
		user.setUsername(scanner.nextLine());
		
		System.out.print("Password: ");
		user.setPassword(scanner.nextLine());
		
		scanner.close();
		
		return this.userService.authenticate(user);
	}
}

