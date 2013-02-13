package comtrek



import grails.test.mixin.support.*
import org.junit.*
import grails.test.mixin.*
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(UserController)
@Mock(User)
@TestMixin(GrailsUnitTestMixin)
class UserControllerUnitSpec extends Specification{

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
		params["firstName"] = "hamza"
		params["lastName"]= "Bey"
		params["gender"]= "Male"
		params["email"]= "bey.hamza.s@gmail.com"
		params["login"]= "hamzatest"
		params["password"] = "hamzatestMDP"
		params["birthDate"]= new Date()-1
    }

    /*void testIndex() {
        controller.index()
        assert "/user/list" == response.redirectedUrl
    }*/
	
	@Unroll("test userController index")
	def "test userController index"() {
		when:
		//controller.index()
		doAction(obj, actionString, paramList)
		print "url expected : /user/list , obtained  : " + response.redirectedUrl

		then:
		assert expectedUrl == response.redirectedUrl
		where:
		obj        | actionString | paramList | expectedUrl 
		controller | "index"      | null      | "/user/list" 

	}

    /*void testList() {

        def model = controller.list()

        assert model.userInstanceList.size() == 0
        assert model.userInstanceTotal == 0
    }*/
	
	@Unroll("test userController List")
	def "test userController List"() {
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

   /* void testCreate() {
        def model = controller.create()

        assert model.userInstance != null
    }*/
	
	@Unroll("test userController Create  #expectedInstance")
	def "test userController Create"() {
		when:
		def model = controller.create()

		then:
		assert model.userInstance != unexpectedInstance
		where:
		obj        | actionString | paramList | unexpectedInstance 
		controller | "list"       | null      | null        
	}

   /* void testSave() {
        controller.save()

        assert model.userInstance != null
        assert view == '/user/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/user/show/1'
        assert controller.flash.message != null
        assert User.count() == 1
    }*/

	@Unroll("test userController Save")
	def "test userController Save"() {
		when:
		controller.save()
		assert model.userInstance != null
		assert view == '/user/create'
		response.reset()
		populateValidParams(params)
		controller.save()


		then:
		assert response.redirectedUrl == '/user/show/1'
		assert controller.flash.message != null
		assert User.count() == 1
		//where:
		//obj        | actionString | paramList | unExpectedInstance 
		//controller | "list"       | null      | null      

	}
	
    /*void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.show()

        assert model.userInstance == user
    }*/
	
	def "test userController Show"() {
		when:
		controller.show()
		assert flash.message != null
		assert response.redirectedUrl == '/user/list'
		populateValidParams(params)
		def user = new User(params)		
		assert user.save() != null
		params.id = user.id
		def model = controller.show()

		then:
		assert model.userInstance == user
		
		//where:
		
	}

    /*void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        params.id = user.id

        def model = controller.edit()

        assert model.userInstance == user
    }*/
	
	def "test userController Edit"() {
		when:
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'
		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null

		params.id = user.id

		def model = controller.edit()

		then:
		assert model.userInstance == user
		//where:

	}

   /* void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        // test invalid parameters in update
        params.id = user.id
        //TODO: add invalid values to params object
		params["password"] = "hamza_test_MDP_Invalid_Car_Trop_Long"

        controller.update()

        assert view == "/user/edit"
        assert model.userInstance != null

        user.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/user/show/$user.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        user.clearErrors()

        populateValidParams(params)
        params.id = user.id
        params.version = -1
        controller.update()

        assert view == "/user/edit"
        assert model.userInstance != null
        assert model.userInstance.errors.getFieldError('version')
        assert flash.message != null
    }*/
	
	def "test userController Update"() {
		when:
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null

        // test invalid parameters in update
        params.id = user.id
        //TODO: add invalid values to params object
		params["password"] = "hamza_test_MDP_Invalid_Car_Trop_Long"

        controller.update()

        assert view == "/user/edit"
        assert model.userInstance != null

        user.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/user/show/$user.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        user.clearErrors()

        populateValidParams(params)
        params.id = user.id
        params.version = -1
        controller.update()

		then:
		
		assert view == "/user/edit"
		assert model.userInstance != null
		assert model.userInstance.errors.getFieldError('version')
		assert flash.message != null
		
		//where:
	}

    /*void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/user/list'

        response.reset()

        populateValidParams(params)
        def user = new User(params)

        assert user.save() != null
        assert User.count() == 1

        params.id = user.id

        controller.delete()

        assert User.count() == 0
        assert User.get(user.id) == null
        assert response.redirectedUrl == '/user/list'
    }*/
	
	def "test userController Delete"() {
		when:
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/user/list'

		response.reset()

		populateValidParams(params)
		def user = new User(params)

		assert user.save() != null
		assert User.count() == 1

		params.id = user.id

		controller.delete()
		then:
		assert User.count() == 0
		assert User.get(user.id) == null
		assert response.redirectedUrl == '/user/list'
		//where:	
	}
	
/*	def setup() {
		//mock a user with some data (put unique violations in here so they can be tested, the others aren't needed)
		mockForConstraintsTests(User, [new User(firstName: 'Hamza')])
	}*/
	
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
