package start

class StartController {

    def ex100Service

    def index() { }

    def filter(){
        LinkedHashMap result = ex100Service.filter(params)

        render view: "/start/filter", model: [rows: result.rows]
    }

    /**
     * 新增資料
     */
    def createUseDomain(){
        LinkedHashMap result = ex100Service.insert(params)
        println result

        redirect(controller: "start" , action: "index")
    }
}
