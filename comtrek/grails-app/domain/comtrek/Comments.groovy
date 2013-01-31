package comtrek

class Comments {

	int id
	String comment
	Date writing_date
	
    static constraints = {
		
		id blank: false, nullable: false, unique: true
		comment blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		writing_date nullable:false
		
    }
}
