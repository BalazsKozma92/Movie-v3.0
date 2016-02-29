package movies;

import java.util.*;
import movies.Movie.Genre;
import movies.Person.Gender;
import java.io.*;
import java.net.Socket;

public class RentManager {
    
    public static long calculatePrice(List<Buyable> buyableProducts) {
    	long price = 0;
    	for (Buyable buyable : buyableProducts) {
    		price += buyable.getPrice();
    	}
	return price;
    }
    
    public static void main(String[] args) {
    	Person lTook = new Person("Laura", "Took", Gender.FEMALE, 294);
    	Person fGammidge = new Person("Flambard", "Gammidge", Gender.MALE, 342);
    	Person pHayward = new Person("Pervinca", "Hayward", Gender.FEMALE, 351);
    	Person tSandyman = new Person("Tobold", "Sandyman", Gender.MALE, 340);
    	Person thomasJane = new Person("Thomas", "Jane", Gender.MALE, 1424);
    	Person ryanReynolds = new Person("Ryan", "Reynolds", Gender.MALE, 1211);
    	Person emilyRutherfurd = new Person("Emily", "Rutherfurd", Gender.FEMALE, 1637);
    	Person tobyJones = new Person("Toby", "Jones", Gender.MALE, 1496);
    	Person hayleyAtwell = new Person("Hayley", "Atwell", Gender.FEMALE, 1641);
    	Person danBrown = new Person("Dan", "Brown", Gender.MALE, 4611);
    	Person aliceCary = new Person("Alice", "Cary", Gender.FEMALE, 3248);
    	Person isaacAsimov = new Person("Isaac", "Asimov", Gender.MALE, 9348);
	
    	List<Person> theMistCast = new ArrayList<Person>();
    	theMistCast.add(thomasJane);
    	theMistCast.add(hayleyAtwell);
	
    	List<Person> thePunisherCast = new ArrayList<Person>();
    	thePunisherCast.add(thomasJane);
    	thePunisherCast.add(ryanReynolds);
	
    	List<Person> vanWilderCast = new ArrayList<Person>();
    	vanWilderCast.add(ryanReynolds);
    	vanWilderCast.add(emilyRutherfurd);
	
    	Product theMist = new Movie("The Mist", fGammidge, Genre.HORROR, 126, 7.2, theMistCast, 31);
    	System.out.println(theMist);
	
    	Product thePunisher = new Movie("The Punisher", tSandyman, Genre.ACTION, 124, 6.5, thePunisherCast, 24);
    	System.out.println(thePunisher);
	
    	Product vanWilder = new Movie("Van Wilder", lTook, Genre.COMEDY, 112, 6.4, vanWilderCast, 24);
    	System.out.println(vanWilder);
	
    	List<Person> redAlertStaff = new ArrayList<Person>();
    	redAlertStaff.add(pHayward);
    	redAlertStaff.add(tobyJones);
	
    	List<Person> saintsRowStaff = new ArrayList<Person>();
    	saintsRowStaff.add(hayleyAtwell);
    	saintsRowStaff.add(fGammidge);
	
    	List<Person> superMarioStaff = new ArrayList<Person>();
    	superMarioStaff.add(ryanReynolds);
    	superMarioStaff.add(tSandyman);
	
    	Product redAlert = new Game("Red Alert", pHayward, false, redAlertStaff, 17);
    	System.out.println(redAlert);
	
    	Product saintsRow = new Game("Saints Row", thomasJane, true, saintsRowStaff, 26);
    	System.out.println(saintsRow);
	
    	Product superMario = new Game("Super Mario", tobyJones, true, superMarioStaff, 114);
    	System.out.println(superMario);
	
    	Product inferno = new Book("Inferno", fGammidge, danBrown);
    	System.out.println(inferno);
	
    	Product picturesCountry = new Book("Pictures of Country Life", hayleyAtwell, aliceCary);
    	System.out.println(picturesCountry);
	
    	Product iRobot = new Book("I, Robot", lTook, isaacAsimov);
    	System.out.println(iRobot);
	
    	System.out.println("\nThese are product investments:");
    	System.out.println(redAlert.getTitle() + " " + redAlert.getInvestment());
    	System.out.println(vanWilder.getTitle() + " " + vanWilder.getInvestment());
    	System.out.println(iRobot.getTitle() + " " + iRobot.getInvestment());
	
    	List<Buyable> products = new ArrayList<Buyable>();
    	products.add((Buyable) theMist);
    	products.add((Buyable) thePunisher);
    	products.add((Buyable) redAlert);
    	products.add((Buyable) superMario);
	
    	System.out.print("\nThe price of two movies (The Mist, The Punisher) and two games (Red Alert, Super Mario): ");
    	System.out.println(RentManager.calculatePrice(products));
    }
}