package comtrek



import java.util.List;

import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.support.*


import spock.lang.Specification
import spock.lang.Unroll
import grails.test.mixin.*

@TestFor(TrekController)
@Mock(Trek)
@TestMixin(GrailsUnitTestMixin)
class TrekControllerSpec extends Specification{

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params["name"] = "hamza"
		params["distance"] = "300"
		params["level"] = "2"
		params["max_altitude"] ="1500"
		params["weather_link"] = "cest_quoi"
		params["average_note"] = "2"
		params["average_time"] = "180"
		params["user"] = new User()
		def event1 = new Event()
		def event2 = new Event()
		def comment1 = new Comment()
		def comment2 = new Comment()
		params["events"] = [event1, event2]
		params["comments"] = [comment1, comment2]
    }

	@Unroll("test trekController index")
	def "test trekController index"() {
		when:
		//controller.index()
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
				
		then:
		assert model.trekInstanceList.size() == listSize
		assert model.trekInstanceTotal == instanceTotal
		
		where:
		obj        | actionString | paramList | listSize | instanceTotal
		controller | "list"       | null      | 0        | 0

	}
	
	@Unroll("test trekController Create  #expectedInstance")
	def "test trekController Create"() {
		when:
		def model = controller.create()

		then:
		assert model.trekInstance != unexpectedInstance
		where:
		obj        | actionString | paramList | unexpectedInstance
		controller | "create"       | null      | null
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
		assert trek.count() == 1
		//where:
		//obj        | actionString | paramList | unExpectedInstance
		//controller | "list"       | null      | null

	}
	
	def "test trekController Show"() {
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
	
	
	def "test trekController Edit"() {
		when:
		controller.edit()
		
				assert flash.message != null
				assert response.redirectedUrl == '/trek/list'
		
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
				//TODO: add invalid values to params object
				params["average_note"] = "100"
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
				trek.clearErrors()
		
				populateValidParams(params)
				params.id = trek.id
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
		assert trek.count() == 0
		assert trek.get(trek.id) == null
		assert response.redirectedUrl == '/trek/list'
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

   /* void testIndex() {
        controller.index()
        assert "/trek/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.trekInstanceList.size() == 0
        assert model.trekInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.trekInstance != null
    }

    void testSave() {
        controller.save()

        assert model.trekInstance != null
        assert view == '/trek/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/trek/show/1'
        assert controller.flash.message != null
        assert Trek.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/trek/list'

        populateValidParams(params)
        def trek = new Trek(params)

        assert trek.save() != null

        params.id = trek.id

        def model = controller.show()

        assert model.trekInstance == trek
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/trek/list'

        populateValidParams(params)
        def trek = new Trek(params)

        assert trek.save() != null

        params.id = trek.id

        def model = controller.edit()

        assert model.trekInstance == trek
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/trek/list'

        response.reset()

        populateValidParams(params)
        def trek = new Trek(params)

        assert trek.save() != null

        // test invalid parameters in update
        params.id = trek.id
        //TODO: add invalid values to params object

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
        trek.clearErrors()

        populateValidParams(params)
        params.id = trek.id
        params.version = -1
        controller.update()

        assert view == "/trek/edit"
        assert model.trekInstance != null
        assert model.trekInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
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

        assert Trek.count() == 0
        assert Trek.get(trek.id) == null
        assert response.redirectedUrl == '/trek/list'
    }*/
}
