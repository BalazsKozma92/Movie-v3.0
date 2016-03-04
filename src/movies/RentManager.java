package movies;

import java.util.*;
import movies.Movie.Genre;
import movies.Person.Gender;
import java.io.*;
import java.net.Socket;

public class RentManager {
  
	static Socket socket;
	static List<Product> productList;
	
    public static long calculatePrice(List<Buyable> buyableProducts) {
    	long price = 0;
    	for (Buyable buyable : buyableProducts) {
    		price += buyable.getPrice();
    	}
	return price;
    }
    
    public static void main(String[] args) {
    	Person csViki = new Person("Csoma", "Viki", Gender.FEMALE, 294);
    	Person pIstvan = new Person("Pasztor", "Istvan", Gender.MALE, 342);
    	Person sDalma = new Person("Simai", "Dalma", Gender.FEMALE, 351);
    	Person tHardy = new Person("Tom", "Hardy", Gender.MALE, 340);
    	Person leoCap = new Person("Leonardo", "DiCaprio", Gender.MALE, 1424);
    	Person jAckles = new Person("Jensen", "Ackles", Gender.MALE, 1211);
    	Person wAlice = new Person("Wonder", "Alice", Gender.FEMALE, 1637);
    	Person jPadelacki = new Person("Jared", "Padelacki", Gender.MALE, 1496);
    	Person jLawrence = new Person("Jennifer", "Atwell", Gender.FEMALE, 1641);
    	Person dRadcliffe = new Person("Daniel", "Radcliffe", Gender.MALE, 4611);
    	Person nDobrev = new Person("Nina", "Dobrev", Gender.FEMALE, 3248);
    	Person jDepp = new Person("Johnny", "Depp", Gender.MALE, 9348);
	
    	List<Person> theMistCast = new ArrayList<Person>();
    	theMistCast.add(leoCap);
    	theMistCast.add(jLawrence);
	
    	List<Person> thePunisherCast = new ArrayList<Person>();
    	thePunisherCast.add(leoCap);
    	thePunisherCast.add(jAckles);
	
    	List<Person> vanWilderCast = new ArrayList<Person>();
    	vanWilderCast.add(jAckles);
    	vanWilderCast.add(wAlice);
	
    	Product theMist = new Movie("The Mist", pIstvan, Genre.HORROR, 126, 7.2, theMistCast, 31);
    	//System.out.println(theMist);
	
    	Product thePunisher = new Movie("The Punisher", tHardy, Genre.ACTION, 124, 6.5, thePunisherCast, 24);
    	//System.out.println(thePunisher);
	
    	Product vanWilder = new Movie("Van Wilder", csViki, Genre.COMEDY, 112, 6.4, vanWilderCast, 24);
    	//System.out.println(vanWilder);
	
    	List<Person> redAlertStaff = new ArrayList<Person>();
    	redAlertStaff.add(sDalma);
    	redAlertStaff.add(jPadelacki);
	
    	List<Person> saintsRowStaff = new ArrayList<Person>();
    	saintsRowStaff.add(jLawrence);
    	saintsRowStaff.add(pIstvan);
	
    	List<Person> superMarioStaff = new ArrayList<Person>();
    	superMarioStaff.add(jAckles);
    	superMarioStaff.add(tHardy);
	
    	Product redAlert = new Game("Red Alert", sDalma, false, redAlertStaff, 17);
    	//System.out.println(redAlert);
	
    	Product saintsRow = new Game("Saints Row", leoCap, true, saintsRowStaff, 26);
    	//System.out.println(saintsRow);
	
    	Product superMario = new Game("Super Mario", jPadelacki, true, superMarioStaff, 114);
    	//System.out.println(superMario);
	
    	Product inferno = new Book("Inferno", pIstvan, dRadcliffe);
    	//System.out.println(inferno);
	
    	@SuppressWarnings("unused")
		Product picturesCountry = new Book("Pictures of Country Life", jLawrence, nDobrev);
    	//System.out.println(picturesCountry);
	
    	Product iRobot = new Book("I, Robot", csViki, jDepp);
    	//System.out.println(iRobot);
	
    	//System.out.println("\nThese are product investments:");
    	//System.out.println(redAlert.getTitle() + " " + redAlert.getInvestment());
    	//System.out.println(vanWilder.getTitle() + " " + vanWilder.getInvestment());
    	//System.out.println(iRobot.getTitle() + " " + iRobot.getInvestment());
	
    	List<Buyable> products = new ArrayList<Buyable>();
    	products.add((Buyable) theMist);
    	products.add((Buyable) thePunisher);
    	products.add((Buyable) redAlert);
    	products.add((Buyable) superMario);
	
    	//System.out.print("\nThe price of two movies (The Mist, The Punisher) and two games (Red Alert, Super Mario): ");
    	//System.out.println(RentManager.calculatePrice(products));
    	
    	List<Product> productList = new ArrayList<Product>();
    	productList.add(theMist);
    	productList.add(thePunisher);
    	productList.add(redAlert);
    	productList.add(superMario);
    	productList.add(iRobot);
    	productList.add(vanWilder);
    	productList.add(inferno);
    	productList.add(saintsRow);
    	
    	clientManager();
}
    public static void clientManager(){
		try {
			socket = new Socket("localhost", 811);
			System.out.println("[CLIENT] - Connected to the server.\n");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			send(Command.PUT);
			oos.writeObject(productList);
			send(Command.GET);
			@SuppressWarnings("unchecked")
			List<Product> productList2 = (List<Product>) ois.readObject();
			System.out.println(productList2.toString());
			send(Command.EXIT);			
			oos.close();
			ois.close();
			socket.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
    }

	public static void send(Command command) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(command);	
		}
 }
