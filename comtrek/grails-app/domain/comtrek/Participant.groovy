package comtrek

class Participant {

	int id
	int note
	static hasOne = [user:User]
    static constraints = {
		note(blank : true , range : 0..10)
    }
}
