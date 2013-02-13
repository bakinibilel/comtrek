package comtrek



import java.util.List;

import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(EventController)
@Mock(Event)
class EventControllerUnitSpec extends Specification{

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
		params["effectiveTime"] = new Date()-1
		params["averageNote"]= 4
		params["trek"]= new Trek()
		params["user"]= new User()
    }

	@Unroll("test eventController index")
	def "test eventController index"() {
		when:
		//controller.index()
		doAction(obj, actionString, paramList)
		print "url expected : /event/list , obtained  : " + response.redirectedUrl

		then:
		assert expectedUrl == response.redirectedUrl
		where:
		obj        | actionString | paramList | expectedUrl 
		controller | "index"      | null      | "/event/list" 

	}

	
	@Unroll("test eventController List")
	def "test eventController List"() {
		when:
		def model = controller.list()
				
		then:
		assert model.eventInstanceList.size() == listSize
		assert model.eventInstanceTotal == instanceTotal
		
		where:
		obj        | actionString | paramList | listSize | instanceTotal
		controller | "list"       | null      | 0        | 0

	}
	
	@Unroll("test eventController Create  #expectedInstance")
	def "test eventController Create"() {
		when:
		def model = controller.create()

		then:
		assert model.eventInstance != unexpectedInstance
		where:
		obj        | actionString | paramList | unexpectedInstance
		controller | "create"       | null      | null
	}



	@Unroll("test eventController Save")
	def "test eventController Save"() {
		when:
		controller.save()
		assert model.eventInstance != null
		assert view == '/event/create'
		response.reset()
		populateValidParams(params)
		controller.save()
		then:
		assert response.redirectedUrl == '/event/show/1'
		assert controller.flash.message != null
		assert Event.count() == 1
		//where:
		//obj        | actionString | paramList | unExpectedInstance
		//controller | "list"       | null      | null

	}
	
	def "test eventController Show"() {
		when:
		controller.show()
        assert flash.message != null
        assert response.redirectedUrl == '/event/list'
        populateValidParams(params)
        def event = new Event(params)
        assert event.save() != null
        params.id = event.id
        def model = controller.show()
				then:
				assert model.eventInstance == event
	}
	
	
	def "test eventController Edit"() {
		when:
		controller.edit()
		
				assert flash.message != null
				assert response.redirectedUrl == '/event/list'
		
				populateValidParams(params)
				def event = new Event(params)
		
				assert event.save() != null
		
				params.id = event.id
		
				def model = controller.edit()
		then:
		assert model.eventInstance == event
	}

	
	def "test eventController Update"() {
		when:
		controller.update()
		
				assert flash.message != null
				assert response.redirectedUrl == '/event/list'
		
				response.reset()
		
				populateValidParams(params)
				def event = new Event(params)
		
				assert event.save() != null
		
				// test invalid parameters in update
				params.id = event.id
				//TODO: add invalid values to params object
				params["averageNote"] = "100"
				controller.update()
		
				assert view == "/event/edit"
				assert model.eventInstance != null
		
				event.clearErrors()
		
				populateValidParams(params)
				controller.update()
		
				assert response.redirectedUrl == "/event/show/$event.id"
				assert flash.message != null
		
				//test outdated version number
				response.reset()
				event.clearErrors()
		
				populateValidParams(params)
				params.id = event.id
				params.version = -1
				controller.update()
				
		then:
		assert view == "/event/edit"
		assert model.eventInstance != null
		assert model.eventInstance.errors.getFieldError('version')
		assert flash.message != null
	}


	
	def "test eventController Delete"() {
		when:
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/event/list'

		response.reset()

		populateValidParams(params)
		def event = new Event(params)

		assert event.save() != null
		assert Event.count() == 1

		params.id = event.id

		controller.delete()
		
		then:
		assert Event.count() == 0
		assert Event.get(event.id) == null
		assert response.redirectedUrl == '/event/list'
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
