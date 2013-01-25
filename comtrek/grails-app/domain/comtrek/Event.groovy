package comtrek

class Event {
	
	int id
	Date effectiveTime
	int averageNote
//	static hasMany = [comments: Comment]
//	static hasOne Trek
//	static hasOne Comment
	

    static constraints = {
		id blank: false, nullable: false, unique: true
		effectiveTime blank: false, nullable: false
		averageNote min: 0, max: 10, nullable: true
    }
}
