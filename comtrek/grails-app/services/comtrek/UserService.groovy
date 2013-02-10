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

import org.springframework.transaction.annotation.Transactional

class UserService {

    def createUser(params) {
		[userInstance:new User(params)]
    }
	
	@Transactional(readOnly = true)
	def listUsers() {
		
	}
}
