/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.la3ajltin.tools;

import java.util.Random;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;




public class PasswordUtils {
    
      	// Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
	private static final int workload = 12;
private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	
	public  String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);

                String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}

            public boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}
        
        
        
 public String randomcode() {
StringBuilder builder = new StringBuilder();
int count =4;
   while (count-- != 0) {
int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
builder.append(ALPHA_NUMERIC_STRING.charAt(character));
}
return builder.toString();
}
 
 
  public void forgetpassSMS() {
      PasswordUtils p= new PasswordUtils();
  
      String ch=p.randomcode();


      String ch2;
      System.out.println("Write your code");
      Scanner sc = new Scanner(System.in);
      ch2=sc.nextLine();

      if (ch2.equals(ch))
      {
      System.out.println("change your pass");
      }
      
      else
          
      {
      System.out.println("check your code");

      }
      
  }
     
  
  
    public void forgetpassMail() {
      PasswordUtils p= new PasswordUtils();
      
      String ch=p.randomcode();
   
      String ch2;
      System.out.println("Write your mail");
      Scanner sc = new Scanner(System.in);
      ch2=sc.nextLine();

      if (ch2.equals(ch))
      {
      System.out.println("change your pass");
      }
      
      else
          
      {
      System.out.println("check your code");

      }
      
  }
  
  
  
}

