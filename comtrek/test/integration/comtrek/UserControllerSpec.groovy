package comtrek

import static org.junit.Assert.*
import spock.lang.Specification
import spock.lang.Unroll

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
class UserControllerSpec extends Specification{

	def "test creating a user"() {
		when:
		def userCtrl = new UserController()
		userCtrl.params.

		then:
		validateConstraints(obj, field, error)

		where:
		error      | field       | val
		'nullable' | 'birthDate' | null
		'valid'    | 'birthDate' | new Date() - 1
		'valid'    | 'birthDate' | new Date()
	}
}
