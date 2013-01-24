package comtrek

/**
 * 
 * @author Paolo
 *
 */
class User {

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
        password size: 5..15, blank: false
	}

	String toString(){
		return "Login : "+login+
				"\nName : "+ firstName +" "+lastName+ 
				"\nbirth date : "+ birthDate+
				"\nEmail : "+ email ;
	}
}
