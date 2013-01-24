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
	
	static constraints = {
		birthDate (nullable: true, max: new Date(Calendar.getInstance().getTime().year, Calendar.getInstance().getTime().month, Calendar.getInstance().getTime().date))
		firstName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z _]+"
		lastName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z _]+"
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
		return firstName;
	}
}
