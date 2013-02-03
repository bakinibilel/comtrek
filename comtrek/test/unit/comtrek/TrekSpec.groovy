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
class TrekSpec extends Specification{

	String getLongString(Integer length) {
		'a' * length
	}

	
	def setup() {
		//mock a Trek with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(Trek, [new Trek(firstName: 'Hamza')])
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
	
	@Unroll("test trek all constraints #field is #error using #val")
	def "test trek all constraints"() {
		when:
		def obj = new Trek("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field        | val
		'size'                 | 'name'       | getLongString(21)
		'nullable'             | 'name'       | null
		'size'                 | 'name'       | getLongString(2)
		'nullable'             | 'level'      | null
		'nullable'             | 'level'      | null

	}
	

	
}
