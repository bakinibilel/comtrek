package comtrek



import java.util.List;

import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(TrekController)
@Mock(Trek)
class TrekControllerUnitSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params["name"] = "bilel"
		params["distance"]= "2000"
		params["level"]= "4"
		params["attitude_combined"]= ""
		params["max_altitude"]= "500"
		params["weather_link"] = "www.meteo-toulouse.org"
		params["average_note"]= "3"
		params["average_time"]= "120"
		
    }
	
	@Unroll("test trekController index")
	def "test trekController index"() {
		when:
		doAction(obj, actionString, paramList)
		print "url expected : /trek/list , obtained  : " + response.redirectedUrl

		then:
		assert expectedUrl == response.redirectedUrl
		where:
		obj        | actionString | paramList | expectedUrl
		controller | "index"      | null      | "/trek/list"

	}
	
	@Unroll("test trekController List")
	def "test trekController List"() {
		when:
		def model = controller.list()
		//print "expected list size :  "listSize+
		//" obtained : "+model.userInstanceList.size().toString()+
		//" total instance expected : "+instanceTotal+" obtained : "+model.userInstanceTotal

		then:
		assert model.userInstanceList.size() == listSize
		assert model.userInstanceTotal == instanceTotal
		where:
		obj        | actionString | paramList | listSize | instanceTotal
		controller | "list"       | null      | 0        | 0

	}

	
	@Unroll("test trekController Create  #expectedInstance")
	def "test trekController Create"() {
		when:
		def model = controller.create()

		then:
		assert model.userInstance != unexpectedInstance
		where:
		obj        | actionString | paramList | unexpectedInstance
		controller | "list"       | null      | null
	}

	
	@Unroll("test trekController Save")
	def "test trekController Save"() {
		when:
		controller.save()
		assert model.trekInstance != null
		assert view == '/trek/create'
		response.reset()
		populateValidParams(params)
		controller.save()


		then:
		assert response.redirectedUrl == '/trek/show/1'
		assert controller.flash.message != null
		assert User.count() == 1
		
	}
		
	
	def "test TrekController Show"() {
		when:
		controller.show()
		assert flash.message != null
		assert response.redirectedUrl == '/trek/list'
		populateValidParams(params)
		def trek = new Trek(params)
		assert trek.save() != null
		params.id = trek.id
		def model = controller.show()

		then:
		assert model.trekInstance == trek
		
	}

    /*void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/trek/list'

        populateValidParams(params)
        def trek = new Trek(params)

        assert trek.save() != null

        params.id = trek.id

        def model = controller.edit()

        assert model.trekInstance == trek
    }*/
	
	def "test testController Edit"() {
		when:
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/test/list'
		populateValidParams(params)
		def trek = new Trek(params)

		assert trek.save() != null

		params.id = trek.id

		def model = controller.edit()

		then:
		assert model.trekInstance == trek

	}

	
	def "test trekController Update"() {
		when:
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/trek/list'

		response.reset()

		populateValidParams(params)
		def trek = new Trek(params)

		assert trek.save() != null

		// test invalid parameters in update
		params.id = trek.id
		
		controller.update()

		assert view == "/trek/edit"
		assert model.trekInstance != null

		trek.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/trek/show/$trek.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		user.clearErrors()

		populateValidParams(params)
		params.id = user.id
		params.version = -1
		controller.update()

		then:
		
		assert view == "/trek/edit"
		assert model.trekInstance != null
		assert model.trekInstance.errors.getFieldError('version')
		assert flash.message != null
		
	}

	
	def "test trekController Delete"() {
		when:
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/trek/list'

		response.reset()

		populateValidParams(params)
		def trek = new Trek(params)

		assert trek.save() != null
		assert Trek.count() == 1

		params.id = trek.id

		controller.delete()
		then:
		assert Trek.count() == 0
		assert Trek.get(trek.id) == null
		assert response.redirectedUrl == '/trek/list'
		//where:
	}
	
	Object doAction(obj, action,List param){
		Object ret = null;
		if(param){
			obj.action(*param)
		}else{
		obj."$action"()
		}
	}
	
	List doActions(List obj, List action, List param){
		List returnList;
		int taille = Math.min(Math.min(obj.size(),action.size()), param.size())
		for(i=0;i< taille;i++){
			returnList.add(doAction(obj[i], action[i], param[i]))
		}
		return returnList
	}
}
