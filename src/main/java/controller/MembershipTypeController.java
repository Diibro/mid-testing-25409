package controller;

import java.util.Scanner;

import model.MembershipType;
import service.MembershipTypeService;

public class MembershipTypeController {

	private MembershipTypeService membershipTypeService = new MembershipTypeService() ;
	public String create() {
		MembershipType newType = new MembershipType();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the membership_type name: ");
		newType.setMembershipName(scanner.nextLine());
		
		System.out.println("Enter the maxbooks: ");
		newType.setMaxBooks(scanner.nextInt());
		
		System.out.println("Enter the membership_type price: ");
		newType.setPrice(scanner.nextInt());
		
		
		return this.membershipTypeService.save(newType);
		
	}
}
