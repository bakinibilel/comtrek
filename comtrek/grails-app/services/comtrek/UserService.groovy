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
