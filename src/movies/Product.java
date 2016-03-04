package movies;

public abstract class Product implements java.io.Serializable {
    private String id;
    private String title;
    private Person reader;
    
    public Product(String title, Person reader) {
    	this.title = title;
    	this.reader = reader;
    	this.id = IdGenerator.generate(this);
    }

    public String getId() {
    	return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Person getPerson() {
        return reader;
    }
    
    public abstract long getInvestment();
    
}