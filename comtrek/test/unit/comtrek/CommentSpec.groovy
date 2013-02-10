package comtrek

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

class CommentSpec extends Specification{
	
	def setup() {
		//mock a comment with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(Comment, [new Comment( )])
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
	
	@Unroll("test Comment all constraints #field is #error using #val")
	def "test Event all constraints"() {
		when:
		def obj = new Comment("$field": val)

		then:
		validateConstraints(obj, field, error)

		where:
		error                  | field                | val
		"size"                 |"comment"             | 2
		"size"                 |"comment"             | 31
		"nullable"             |"writing_date"        | null

	}

}
