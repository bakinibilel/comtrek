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

class Comment {

	//int id
	String comment
	Date writing_date
	static hasOne = [event: Event, trek: Trek, participant: Participant]
	
    static constraints = {
		
		//id blank: false, nullable: false, unique: true
		comment blank:false, nullable:false, size:3..500//, matches:"[a-zA-Z1-9_]+"
		writing_date nullable:false
		
    }
}
