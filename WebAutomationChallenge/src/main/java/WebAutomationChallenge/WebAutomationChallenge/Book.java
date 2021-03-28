package WebAutomationChallenge.WebAutomationChallenge;

public class Book {
	
	String name; 
	String author; 
	String publisher; 
	int fromTotalOf;
	
	public Book (String name, String author, String publisher, int total) { 
		
		this.name = name;
		this.author = author; 
		this.publisher = publisher;
		this.fromTotalOf = total;
	}
	
	public int getTotal () { 
		
		return this.fromTotalOf;
	}
	
	public String getName () { 
		
		return this.name;
	}
	
	public String getAuthor () { 
		
		return this.author;
	}
	
	public String getPublisher () { 
		
		return this.publisher;
	}

}
