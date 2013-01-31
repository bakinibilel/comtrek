package comtrek

class Event {
	
	int id
	Date effectiveTime
	int averageNote
	static hasOne = [trek: Trek, user: User]
	static hasMany = [comments: Comment, participants: Participant]
	

    static constraints = {
		id blank: false, nullable: false, unique: true
		effectiveTime blank: false, nullable: false
		averageNote min: 0, max: 10, nullable: true
    }
}
