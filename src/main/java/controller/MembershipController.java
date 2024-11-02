package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Membership;
import model.MembershipType;
import model.User;
import service.MembershipService;
import service.MembershipTypeService;
import service.UserService;
import util.enums.EMembershipStatus;

public class MembershipController {
	private UserService userService = new UserService();
	private MembershipTypeService membershipTypeService = new MembershipTypeService();
	private MembershipService membershipService = new MembershipService();
	
	public String create() {
		try {
			Scanner scanner = new Scanner(System.in);
			Membership newMembership = new Membership();
			
			System.out.print("Please enter your id: ");
			User user = this.userService.getById(scanner.nextLine());
			
			if(user == null) {
				return "User not found. First register create an account";
			}
			
			newMembership.setReader(user);
			
			List<MembershipType> membershipTypes = this.membershipTypeService.getAll();
			
			HashMap<Integer, MembershipType> typesMap = new HashMap<Integer, MembershipType>();
			int count = 0;
			System.out.println("Please select the membership type: just number");
			for(MembershipType type: membershipTypes) {
				count++;
				typesMap.put(count, type);
				System.out.println(count+". "+type.toString());
				
			}
			newMembership.setMembershipType(typesMap.get(scanner.nextInt()));
			newMembership.setMembershipStatus(EMembershipStatus.PENDING);
			
			scanner.nextLine();
			System.out.print("Enter the membership code: ");
			newMembership.setMembershipCode(scanner.nextLine());
			
			LocalDate regiDate = LocalDate.now();
			
			newMembership.setRegistrationDate(Date.from(regiDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
			System.out.print("Enter the number of days: ");
			int daysNo = scanner.nextInt();
			scanner.nextLine();
			LocalDate expirationDate = regiDate.plus(daysNo, ChronoUnit.DAYS);
			
			newMembership.setExpiringDate(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
			
			System.out.println("You will pay: "+daysNo * newMembership.getMembershipType().getPrice());
			System.out.println("And You borrow up to: "+ newMembership.getMembershipType().getMaxBooks());
			
			System.out.print("Save membership: 1: yes & 0: no");
			int saveChoice = scanner.nextInt();
			if(saveChoice == 1) {
				return this.membershipService.save(newMembership);
			}else {
				return "You chose not to save the membership";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error occurred";
		}
		
	}
}
