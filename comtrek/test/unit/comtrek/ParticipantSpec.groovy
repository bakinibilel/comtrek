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
@TestFor(Participant)
class ParticipantSpec extends Specification{

	def setup() {
		//mock a person with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(Participant, [new Participant(note:2 )])
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
	
	@Unroll("test Participant all constraints #field is #error using #val")
	def "test Participant all constraints"() {
		when:
		def obj = new Participant("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field        | val
		"range"                |"note"        | -1
		"range"                |"note"        | 11
		"valid"                |"note"        | 10
		"valid"                |"note"        | 0
		"valid"                |"note"        | 2
	}
}
