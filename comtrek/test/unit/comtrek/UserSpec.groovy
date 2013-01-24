package comtrek

   import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class UserSpec extends Specification{

	String getLongString(Integer length) {
		'a' * length
	}
	String getEmail(Boolean valid) {
		valid ? "testVrai@testeur.com" : "testFaux@Faux"
	}
	
	def setup() {
		//mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(User, [new User(firstName: 'Hamza')])
	}
	
	void validateConstraints(obj, field, error) {
		def validated = obj.validate()
		if (error && error != 'valid') {
			assert !validated
			assert obj.errors[field]
			assert error == obj.errors[field]
		} else {
			assert !obj.errors[field]
		}
		//println ("erreur attendue : "+error+ ", pour le champ : "+field+ ", avec la valeur : "+obj."$field"+", erreur obtenu : "+obj.errors[field])
	}
	
	@Unroll("test user all constraints #field is #error using #val")
	def "test user all constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field        | val
		'size'                 | 'firstName'  | getLongString(31)
		'nullable'             | 'firstName'  | null
		'size'                 | 'lastName'   | getLongString(31)
		'nullable'             | 'lastName'   | null
		'email'                | 'email'      | getEmail(false)
		'inList'               | 'gender'     | 'Unknown'
		'max'                  | 'birthDate'  | new Date() + 1
		'nullable'             | 'password'   | null
		'nullable'             | 'login'      | null
	}
	
	@Unroll("User #field is #error using #val")
	def "test user birth date constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error      | field       | val
		'nullable' | 'birthDate' | null
		'valid'    | 'birthDate' | new Date() - 1
		//'valid'    | 'birthDate' | new Date()
	}
	
	@Unroll("user #field is #error using #val")
	def "test user gender constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error      | field    | val
		'inList'   | 'gender' | 'Unknown'
		'nullable' | 'gender' | null
		'valid'    | 'gender' | '' //blanks work for inList
		'valid'    | 'gender' | 'Male'
		'valid'    | 'gender' | 'Female'
	}
	
	@Unroll("user #field is #error using #val")
	def "test user username constraints"() {
		when:
		def obj = new User("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field      | val
		'nullable'             | 'login'    | null
		'valid'                | 'login'    | 'bobafet'
		'valid'                | 'login'    | 'bobajunior'
	}
}
