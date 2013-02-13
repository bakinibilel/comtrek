package comtrek



import java.util.List;

import org.junit.*
import spock.lang.Specification
import grails.test.mixin.*
import spock.lang.Unroll


@TestFor(CommentController)
@Mock(Comment)
class CommentControllerUnitSpec extends Specification{

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params["comment"] = "Voici mon commentaire..."
		params["writing_date"]= new Date()-1
    }

    @Unroll("test commentController index")
	def "test commentController index"() {
		when:
		doAction(obj, actionString, paramList)
		print "url expected : /comment/list , obtained  : " + response.redirectedUrl

		then:
		assert expectedUrl == response.redirectedUrl
		where:
		obj        | actionString | paramList | expectedUrl
		controller | "index"      | null      | "/comment/list"

	}
	
	@Unroll("test commentController List")
	def "test commentController List"() {
		when:
		def model = controller.list()


		then:
		assert model.commentInstanceList.size() == listSize
		assert model.commentInstanceTotal == instanceTotal
		where:
		obj        | actionString | paramList | listSize | instanceTotal
		controller | "list"       | null      | 0        | 0

	}

	
	@Unroll("test commentController Create  #expectedInstance")
	def "test commentController Create"() {
		when:
		def model = controller.create()

		then:
		assert model.commentInstance != unexpectedInstance
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
