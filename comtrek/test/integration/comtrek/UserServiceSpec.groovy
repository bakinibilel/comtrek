package comtrek



import java.util.Date

import grails.plugin.spock.IntegrationSpec
import grails.test.mixin.*

import org.junit.*

class UserServiceSpec extends IntegrationSpec {

  def userService

  def "user create"() {

    expect:
    userService.createUser(firstName, lastName, gender, email, login, password, birthDate).hasErrors() == creationFailed

    where:
    firstName 	| lastName 	| gender 	| email  			|	login 		| password 		| birthDate 		| creationFailed
    "bill"		| "toto" 	| "Male" 	| "toto@toto.fr"	| "myLogin"		| "password"	| new Date() - 1	| false
    null	 	| "toto" 	| "Female"	| "toto@toto.fr"	| "myLogin"		| "password"	| new Date() - 1	| true
    "bill"    	|  null 	| "Female"	| "toto@toto.fr"	| "myLogin"		| "password"	| new Date() - 1	| true
    "bill"	  	| "toto" 	| "Female" 	| "toto@toto.fr"	| "myL"			| "password"	| new Date() - 1	| true
    "bill" 		| "toto" 	| "NotMale" | "toto@toto.fr"	| "myLogin"		| "password"	| new Date() - 1	| true
    "bill"    	| "toto"	| "Female"	| "toto@toto.fr"	| "myLogin"		| "password"	| null				| true
	"bill"    	| "toto"	| "Female"	| "toto@toto.fr"	| "myLogin"		| "pas"			| new Date() - 1	| true
  }
}
