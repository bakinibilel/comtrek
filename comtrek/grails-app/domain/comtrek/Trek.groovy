package comtrek

import java.util.Date;


class Trek {

   String name
	Float distance
	Integer level
	Integer attitude_combined
	Integer max_altitude
	String weather_link
	Integer average_note
	Date average_time
	
	static constraints = {
		
		name blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		lastName blank:false, nullable:false, size:3..30, matches:"[a-zA-Z1-9_]+"
		level range: 1..5
		max_altitude range: 0..10000
		average_note range: 0..5
		average_time (nullable: false, max: new Date(Calendar.getInstance().getTime().year, Calendar.getInstance().getTime().month, Calendar.getInstance().getTime().date))
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
