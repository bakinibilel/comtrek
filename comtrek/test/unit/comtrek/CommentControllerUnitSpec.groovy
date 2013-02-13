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
		params["event"]= new Event()
		params["trek"]= new Trek()
		params["participant"]= new Participant()
    }

    @Unroll("test commentController index")
	def "test commentController index"() {
		when:
		//controller.index()
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
		controller | "create"       | null      | null
	}



	@Unroll("test commentController Save")
	def "test commentController Save"() {
		when:
		controller.save()
		assert model.commentInstance != null
		assert view == '/comment/create'
		response.reset()
		populateValidParams(params)
		controller.save()
		then:
		assert response.redirectedUrl == '/comment/show/1'
		assert controller.flash.message != null
		assert Comment.count() == 1
		//where:
		//obj        | actionString | paramList | unExpectedInstance
		//controller | "list"       | null      | null

	}
	
	def "test commentController Show"() {
		when:
		controller.show()
        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'
        populateValidParams(params)
        def comment = new Comment(params)
        assert comment.save() != null
        params.id = comment.id
        def model = controller.show()
				then:
				assert model.commentInstance == comment
	}
	
	
	def "test commentController Edit"() {
		when:
		controller.edit()
		
				assert flash.message != null
				assert response.redirectedUrl == '/comment/list'
		
				populateValidParams(params)
				def comment = new Comment(params)
		
				assert comment.save() != null
		
				params.id = comment.id
		
				def model = controller.edit()
		then:
		assert model.commentInstance == comment
	}

	
	def "test commentController Update"() {
		when:
		controller.update()
		
				assert flash.message != null
				assert response.redirectedUrl == '/comment/list'
		
				response.reset()
		
				populateValidParams(params)
				def comment = new Comment(params)
		
				assert comment.save() != null
		
				// test invalid parameters in update
				params.id = comment.id
				//TODO: add invalid values to params object
				params["comment"] = null
				controller.update()
		
				assert view == "/comment/edit"
				assert model.commentInstance != null
		
				comment.clearErrors()
		
				populateValidParams(params)
				controller.update()
		
				assert response.redirectedUrl == "/comment/show/$comment.id"
				assert flash.message != null
		
				//test outdated version number
				response.reset()
				comment.clearErrors()
		
				populateValidParams(params)
				params.id = comment.id
				params.version = -1
				controller.update()
				
		then:
		assert view == "/comment/edit"
		assert model.commentInstance != null
		assert model.commentInstance.errors.getFieldError('version')
		assert flash.message != null
	}


	
	def "test commentController Delete"() {
		when:
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/comment/list'

		response.reset()

		populateValidParams(params)
		def comment = new Comment(params)

		assert comment.save() != null
		assert comment.count() == 1

		params.id = comment.id

		controller.delete()
		
		then:
		assert comment.count() == 0
		assert comment.get(comment.id) == null
		assert response.redirectedUrl == '/comment/list'
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
