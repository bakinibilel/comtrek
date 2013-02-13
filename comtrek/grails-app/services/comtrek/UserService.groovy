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

    User createUser(String firstName, String lastName, String gender, String email, String login, String password, Date birthDate) {
    User user = new User(firstName : firstName, lastName : lastName, gender : gender, email : email, login : login, password : password, birthDate : birthDate)
    user.save()
	user
  }
	
}
