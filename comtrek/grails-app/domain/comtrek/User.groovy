/*******************************************************************************
 * Copyright (c) 2013 ComTrek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ComTrek - initial API and implementation
 ******************************************************************************/
package comtrek

/**
 * 
 * @author Paolo
 *
 */
class User {

	static hasMany = [events: Event, participants: Participant]
	
	String firstName
	String lastName
    String gender
	String email
	String login
	String password
	Date birthDate
	
	static constraints = {
		birthDate (nullable: false, max: new Date(Calendar.getInstance().getTime().year, Calendar.getInstance().getTime().month, Calendar.getInstance().getTime().date))
		firstName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		lastName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		gender inList: ["Male", "Female"]
		email email:true
		login size: 5..15, blank: false, unique: true
        password size: 5..15, blank: false, password: true
	}

	static mapping = {
		table "User"
		firstName column: "firstName"
		lastName column: "lastName"
		email column: "email"
		login column: "login"
		password column: "password"
		birthDate column: "birthDate"
	}
	
	String toString(){
		return "Login : "+login+
				"\nName : "+ firstName +" "+lastName+ 
				"\nbirth date : "+ birthDate+
				"\nEmail : "+ email ;
	}
}
