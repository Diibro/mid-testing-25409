package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Location;
import service.LocationService;
import util.enums.ELocationType;

public class LocationController {
	private LocationService locationService = new LocationService();
	
	public String create() {
		try {
			Scanner scanner = new Scanner(System.in);
		    Location location = new Location();

		    System.out.println("/n Please enter the location details.");

		    System.out.print("Location Code: ");
		    location.setLocationCode(scanner.nextLine());

		    System.out.print("Location Name: ");
		    location.setLocationName(scanner.nextLine());

		    System.out.print("Location Type (Enter a number: 1 for PROVINCE, 2 for DISTRICT, 3 for SECTOR, 4 for CELL, 5 for VILLAGE): ");
		    int typeInput = scanner.nextInt();
		    scanner.nextLine();  

		    ELocationType locationType = null;
		    switch (typeInput) {
		        case 1:
		            locationType = ELocationType.PROVINCE;
		            break;
		        case 2:
		            locationType = ELocationType.DISTRICT;
		            break;
		        case 3:
		        	locationType = ELocationType.SECTOR;
		        	break;
		        case 4: 
		        	locationType = ELocationType.CELL;
		        	break;
		        case 5: 
		        	locationType = ELocationType.VILLAGE;
		        	break;
		        default:
		            System.out.println("Invalid type. Location wont be saved");
		    }
		    
		    if(locationType != null) location.setLocationType(locationType);
		    
		    Location parentLocation = null;
		    
		    if(locationType != ELocationType.PROVINCE ) {
		    	if(locationType == ELocationType.DISTRICT) {
		    		List<Location> parentLocations = this.locationService.getAllByType(ELocationType.PROVINCE);
		    		if(parentLocations == null) {
		    			System.out.println("Please first register PROVINCES");
		    		}else {
		    			System.out.println("Select the parent Location, just type the number: ");
		    			HashMap<Integer, Location> parentLocationsMap = new HashMap<Integer, Location>();
		    			int count = 0;
		    			for(Location loc : parentLocations) {
		    				count++;
		    				parentLocationsMap.put(count, loc);
		    				System.out.println("-- "+ count + ": "+ loc.toString());
		    			}
		    			
		    			int selectedCount = scanner.nextInt();
		    			parentLocation = parentLocationsMap.get(selectedCount);
		    		}
		    		
		    	}else if(locationType == ELocationType.SECTOR) {
		    		List<Location> parentLocations = this.locationService.getAllByType(ELocationType.DISTRICT);
		    		if(parentLocations == null) {
		    			System.out.println("Please first register DISTRICTS");
		    		}else {
		    			System.out.println("Select the parent Location, just type the number: ");
		    			HashMap<Integer, Location> parentLocationsMap = new HashMap<Integer, Location>();
		    			int count = 0;
		    			for(Location loc : parentLocations) {
		    				count++;
		    				parentLocationsMap.put(count, loc);
		    				System.out.println("--"+ count + ": "+ loc.toString());
		    			}
		    			
		    			int selectedCount = scanner.nextInt();
		    			parentLocation = parentLocationsMap.get(selectedCount);
		    		}
		    		
		    	}else if(locationType == ELocationType.CELL) {
		    		List<Location> parentLocations = this.locationService.getAllByType(ELocationType.SECTOR);
		    		if(parentLocations == null) {
		    			System.out.println("Please first register SECTORS");
		    		}else {
		    			System.out.println("Select the parent Location, just type the number: ");
		    			HashMap<Integer, Location> parentLocationsMap = new HashMap<Integer, Location>();
		    			int count = 0;
		    			for(Location loc : parentLocations) {
		    				count++;
		    				parentLocationsMap.put(count, loc);
		    				System.out.println("--"+ count + ": "+ loc.toString());
		    			}
		    			
		    			int selectedCount = scanner.nextInt();
		    			parentLocation = parentLocationsMap.get(selectedCount);
		    		}
		    		
		    	}else if(locationType == ELocationType.VILLAGE) {
		    		List<Location> parentLocations = this.locationService.getAllByType(ELocationType.CELL);
		    		if(parentLocations == null) {
		    			System.out.println("Please first register CELLS");
		    		}else {
		    			System.out.println("Select the parent Location, just type the number: ");
		    			HashMap<Integer, Location> parentLocationsMap = new HashMap<Integer, Location>();
		    			int count = 0;
		    			for(Location loc : parentLocations) {
		    				count++;
		    				parentLocationsMap.put(count, loc);
		    				System.out.println("--"+ count + ": "+ loc.toString());
		    			}
		    			
		    			int selectedCount = scanner.nextInt();
		    			parentLocation = parentLocationsMap.get(selectedCount);
		    		}
		    		
		    	}
		    	if (parentLocation == null) {
		    		scanner.close();
		    		System.out.println("No parent location selected");
		    		return null;
		    	}
		    }
		    
		    location.setParentLocation(parentLocation);
		    System.out.println("Please wait...");
		    String result = this.locationService.save(location);
		    
		    scanner.close();
		    
		    return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Location getProvinceByVillage() {
		Scanner scanner = new Scanner(System.in);
		Location value = null;
		List<Location> locations = this.locationService.getAllByType(ELocationType.VILLAGE);
		if(locations == null || locations.size() == 0) {
			System.out.println("/n No villages found.");
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
			value = villagesMap.get(selectedCount).getParentLocation();
		}
		
		scanner.close();
		
		while(value.getLocationType() != ELocationType.PROVINCE) {
			value = this.locationService.getById(value.getLocationId()).getParentLocation();
		}
		
		System.out.println(value.toString());
		return value;
	}
}

