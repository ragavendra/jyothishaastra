
package org.learn;

public record Person(String firstName, String lastName){
	public String toString(){
		return firstName + " " + lastName;
	}
};

/* 
   public class Perso{
   private String firstName;

   private String lastName;

   public Perso(String fir, String las){
   firstName = fir;
   lastName = las;
   }

   public String getFirstName() {
   return firstName;
   }

   public void setFirstName(String firstName) {
   this.firstName = firstName;
   }

   public String getLastName() {
   return lastName;
   }

   public void setLastName(String lastName) {
   this.lastName = lastName;
   }

   }
   */


