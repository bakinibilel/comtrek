package comtrek



import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Event)
class EventSpec extends Specification{

    def setup() {
		//mock an event with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(Event, [new Event()])
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
	
	@Unroll("test Event all constraints #field is #error using #val")
	def "test Event all constraints"() {
		when:
		def obj = new Event("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field                | val
		"range"                |"averageNote"        | -1
		"range"                |"averageNote"         | 11
		"nullable"             |"effectiveTime"       | null

	}
}
