package movies;

import java.io.Serializable;

public class Book extends Product {
    private Person author;
    
    public Book(String title, Person reader, Person author) {
    	super(title, reader);
    	this.author = author;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public long getInvestment() {
	return author.getSalary();
    }

    @Override
    public String toString() {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("Book [author=").append(author).append(", ID=").append(getId())
    	.append(", title=").append(getTitle()).append(", reader=").append(getPerson()).append("]");
    	return buffer.toString();
    	}

}