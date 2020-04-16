package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Test {

	private final StringProperty pFirstname;
	private final StringProperty pLastname;

	private final StringProperty pGender;
	private final StringProperty pPosition;
        
	private final IntegerProperty pID;
        private final StringProperty pEmail;
        private final StringProperty pPassword;
	
	public Test(String pFirstname, String pLastname, String pGender, String pPosition, int pID, String pEmail, String pPassword) {
		this.pFirstname = new SimpleStringProperty(pFirstname);
		this.pLastname = new SimpleStringProperty(pLastname);
		this.pGender = new SimpleStringProperty(pGender);
		this.pPosition = new SimpleStringProperty(pPosition);
		this.pID = new SimpleIntegerProperty(pID);
                this.pEmail = new SimpleStringProperty(pEmail);
                this.pPassword = new SimpleStringProperty(pPassword);
	}
	
	public StringProperty getpFirstname() {
		return pFirstname;
	}
	
	public StringProperty getpLastname() {
		return pLastname;
	}
	
	public StringProperty getpGender() {
		return pGender;
	}
	
	public StringProperty getpPosition() {
		return pPosition;
	}
	public IntegerProperty getpID() {
		return pID;
	}
        public StringProperty getpEmail() {
		return pEmail;
	}
        public StringProperty getpPassword() {
		return pPassword;
	}
}
