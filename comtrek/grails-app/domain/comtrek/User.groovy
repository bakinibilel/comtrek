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

	String firstName
	String lastName
	String bbb
	String email
	String login
	String password
	Date birthDate
	
	static constraints = {
		birthDate (nullable: true, max: new Date(Calendar.getInstance().getTime().year, Calendar.getInstance().getTime().month, Calendar.getInstance().getTime().date))
		firstName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		lastName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		email email:true
		login size: 5..15, blank: false, unique: true
        password size: 5..15, blank: false
	}

	String toString(){
		return firstName;
	}
}
