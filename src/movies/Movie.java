package movies;

import java.util.List;
import java.io.Serializable;

public class Movie extends Product implements Buyable, java.io.Serializable {
    enum Genre {
	ACTION, DRAMA, SCI_FI, HORROR, THRILLER, ROMANTIC, COMEDY
    };
    private Genre genre;
    private long duration;
    private double rate;
    private List<Person> cast;
    private int price;
    
    public Movie(String title, Person reader, Genre genre, long duration, double rate, List<Person> cast, int price) {
    	super(title, reader);
    	this.genre = genre;
    	this.duration = duration;
    	this.rate = rate;
    	this.cast = cast;
    	this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

    public void setPrice(int price) {
	this.price = price;
    }
    
    @Override
    public int getPrice() {
	return price;
    }

    @Override
    public long getInvestment() {
    	long movieInvestment = 0;
    	for (Person person : cast) {
    		movieInvestment += person.getSalary();
    	}
	return movieInvestment;
    }

    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("Movie genre = ").append(genre).append(", duration = ").append(duration).append(", rate = ")
    	.append(rate).append(", price = ").append(price).append(", ID = ").append(getId())
    	.append(", title=").append(getTitle()).append("]");
    	return buffer.toString();
    }
}