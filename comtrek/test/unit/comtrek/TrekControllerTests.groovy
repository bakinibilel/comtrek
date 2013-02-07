package comtrek



import org.junit.*
import grails.test.mixin.*

@TestFor(TrekController)
@Mock(Trek)
class TrekControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
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
    }
}
