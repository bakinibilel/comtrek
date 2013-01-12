package comtrek

/**
 * 
 * @author Paolo
 *
 */
class User {

	String firstName
	String lastName
	String email
	String login
	String password
	Date birthDate
	String test
	
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
