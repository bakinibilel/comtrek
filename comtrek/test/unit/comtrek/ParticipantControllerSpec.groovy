package comtrek



import java.util.List;

import org.junit.*
import grails.test.mixin.*
import grails.test.mixin.support.*


import spock.lang.Specification
import spock.lang.Unroll



@TestFor(ParticipantController)
@Mock(Participant)
@TestMixin(GrailsUnitTestMixin)
class ParticipantControllerSpec extends Specification{

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
		//params["id"]="202"
        params["note"]="1"
		params["events"]=new Event()
		params["user"]=new User ()		
    }

	@Unroll("test participantController index")
	def "test participantController index"() {
		when:
		//controller.index()
		doAction(obj, actionString, paramList)
		print "url expected : /participant/list , obtained  : " + response.redirectedUrl

		then:
		assert expectedUrl == response.redirectedUrl
		where:
		obj        | actionString | paramList | expectedUrl 
		controller | "index"      | null      | "/participant/list" 

	}

	
	@Unroll("test participantController List")
	def "test participantController List"() {
		when:
		def model = controller.list()
				
		then:
		assert model.participantInstanceList.size() == listSize
		assert model.participantInstanceTotal == instanceTotal
		
		where:
		obj        | actionString | paramList | listSize | instanceTotal
		controller | "list"       | null      | 0        | 0

	}
	
	@Unroll("test participantController Create  #expectedInstance")
	def "test participantController Create"() {
		when:
		def model = controller.create()

		then:
		assert model.participantInstance != unexpectedInstance
		where:
		obj        | actionString | paramList | unexpectedInstance
		controller | "create"       | null      | null
	}



	@Unroll("test participantController Save")
	def "test participantController Save"() {
		when:
		controller.save()
		assert model.participantInstance != null
		assert view == '/participant/create'
		response.reset()
		populateValidParams(params)
		controller.save()
		then:
		assert response.redirectedUrl == '/participant/show/1'
		assert controller.flash.message != null
		assert Participant.count() == 1
		//where:
		//obj        | actionString | paramList | unExpectedInstance
		//controller | "list"       | null      | null

	}
	
	def "test participantController Show"() {
		when:
		controller.show()
        assert flash.message != null
        assert response.redirectedUrl == '/participant/list'
        populateValidParams(params)
        def participant = new Participant(params)
        assert participant.save() != null
        params.id = participant.id
        def model = controller.show()
				then:
				assert model.participantInstance == participant
	}
	
	
	def "test participantController Edit"() {
		when:
		controller.edit()
		
				assert flash.message != null
				assert response.redirectedUrl == '/participant/list'
		
				populateValidParams(params)
				def participant = new Participant(params)
		
				assert participant.save() != null
		
				params.id = participant.id
		
				def model = controller.edit()
		then:
		assert model.participantInstance == participant
	}

	
	def "test participantController Update"() {
		when:
		controller.update()
		
				assert flash.message != null
				assert response.redirectedUrl == '/participant/list'
		
				response.reset()
		
				populateValidParams(params)
				def participant = new Participant(params)
		
				assert participant.save() != null
		
				// test invalid parameters in update
				params.id = participant.id
				//TODO: add invalid values to params object
				params["note"] = "100"
				controller.update()
		
				assert view == "/participant/edit"
				assert model.participantInstance != null
		
				participant.clearErrors()
		
				populateValidParams(params)
				controller.update()
		
				assert response.redirectedUrl == "/participant/show/$participant.id"
				assert flash.message != null
		
				//test outdated version number
				response.reset()
				participant.clearErrors()
		
				populateValidParams(params)
				params.id = participant.id
				params.version = -1
				controller.update()
				
		then:
		assert view == "/participant/edit"
		assert model.participantInstance != null
		assert model.participantInstance.errors.getFieldError('version')
		assert flash.message != null
	}


	
	def "test participantController Delete"() {
		when:
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/participant/list'

		response.reset()

		populateValidParams(params)
		def participant = new Participant(params)

		assert participant.save() != null
		assert Participant.count() == 1

		params.id = participant.id

		controller.delete()
		
		then:
		assert Participant.count() == 0
		assert Participant.get(participant.id) == null
		assert response.redirectedUrl == '/participant/list'
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
