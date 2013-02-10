package comtrek

class TrekService {

	Trek[] getTrek() {
		Trek.list() as Trek[]
	}
	
    def createTrek(params) {
		[trekInstance:new Trek(params)]

    }
	

}
