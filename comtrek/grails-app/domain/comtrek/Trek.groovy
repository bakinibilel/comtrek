package comtrek

import java.util.Date;


class Trek {
	
    int id
    String name
	Float distance
	Integer level
	Integer attitude_combined
	Integer max_altitude
	String weather_link
	Integer average_note
	Integer average_time
	
	static constraints = {
		
		name blank:false, nullable:false, size:3..20, matches:"[a-zA-Z1-9_]+"
		distance blank:false, nullable:false
		level range: 1..5, nullable:false
		max_altitude range: 0..10000
		average_note range: 0..5
		
	}

	static mapping = {
		table "Treck"
		name column: "name"
		distance column: "distance"
		level column: "level"
		attitude_combined column: "attitude_combined"
		max_altitude column: "max_altitude"
		weather_link column: "weather_link"
		average_note column: "average_note"
		average_time column: "average_time"
	}
	
	String toString(){
		return 
				"\nName : "+ name +
				"\ndate : "+average_time +
				"\ndistance : "+ distance ;
	}
}
