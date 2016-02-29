package movies;

import java.util.List;
import java.io.Serializable;

public class Game extends Product implements Buyable {
    private static final float DISCOUNT = 0.2f;
    
    private boolean preOrdered;
    private List<Person> staff;
    private int price;
    
    public Game(String title, Person borrower, boolean preOrdered, List<Person> staff, int price) {
    	super(title, borrower);
    	this.preOrdered = preOrdered;
    	this.staff = staff;
    	this.price = price;
    }

    public boolean isPreOrdered() {
        return preOrdered;
    }

    public void setPreOrdered(boolean preOrdered) {
        this.preOrdered = preOrdered;
    }

    public List<Person> getStaff() {
        return staff;
    }

    public void setStaff(List<Person> staff) {
        this.staff = staff;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPrice() {
    	return preOrdered ? (int) ((1 - DISCOUNT) * price) : price;
    };

    @Override
    public long getInvestment() {
    	long gameInvestment = 0;
    	for (Person person : staff) {
    		gameInvestment += person.getSalary();
	}
    	return gameInvestment;
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	buffer.append("Game [preOrdered=");
	buffer.append(preOrdered);
	buffer.append(", price=");
	buffer.append(price);
	buffer.append(", ID=");
	buffer.append(getId());
	buffer.append(", title=");
	buffer.append(getTitle());
	buffer.append(", borrower=");
	buffer.append(getPerson());
	buffer.append("]");
	return buffer.toString();
    }
}