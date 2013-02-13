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
		params["averageNote"] = "3"
		params["effectiveTime"]= new Date()-1
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
		//print "expected list size :  "listSize+
		//" obtained : "+model.userInstanceList.size().toString()+
		//" total instance expected : "+instanceTotal+" obtained : "+model.userInstanceTotal

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
		controller | "list"       | null      | null
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
