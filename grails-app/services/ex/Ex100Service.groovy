package ex

import grails.web.databinding.DataBinder
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.context.MessageSource

/**
 * 範例domain 資料處理流程
 */
@Transactional
class Ex100Service implements DataBinder {

    MessageSource messageSource

    LinkedHashMap filter(GrailsParameterMap params){

        println params

        LinkedHashMap result = [:]

        String[] strL = ['strings']
        String[] booleanL = []
        String[] likeL = []
        String[] longL = []

        String[] dtL = []
        def dtSEL = []
        dtL.each {
            dtSEL << "${it}1"
            dtSEL << "${it}2"
        }

        def ex100L = Ex100.createCriteria().list() {

            strL.each {
                if(params?."${it}"){
                    eq(it, params?."${it}")
                }
            }

            longL.each {
                if(params?."${it}"){
                    eq(it, params?.long("${it}"))
                }
            }

            booleanL.each {
                if(params?."${it}"){
                    eq(it, Boolean.parseBoolean(params?."${it}"))
                }
            }

            likeL.each {
                if(params?."${it}"){
                    ilike("${it}", "%"+params?."${it}"+"%")
                }
            }

            dtL.each {
                if(params?."${it}1"){
                    ge("${it}",params?."${it}S")
                }
                if(params?."${it}2"){
                    le("${it}",params?."${it}E")
                }
            }

            order("id", "desc") // 新資料放在前面, 可依照系統調整
        }

        result.rows = ex100L.collect { it ->
            [

                    id          : it?.id,
                    strings     : it?.strings,
                    integers    : it?.integers,
                    atms        : it?.atms,
                    status      : it?.status,
            ]
        }


        return result

    }

    /**
     * 新增資料
     * @param params
     * @return
     */
    LinkedHashMap insert(GrailsParameterMap params) {
        return _saveInstance(new Ex100(), params, { Ex100 ex100I ->
            ex100I.manCreated = '系統管理員'
            ex100I.validate()
        })
    }

    /**
     * 更新資料
     * @param params
     * @return
     */
    LinkedHashMap update(GrailsParameterMap params){
        return _saveInstance(Ex100.get(params.id as long), params, { Ex100 ex100I ->
            ex100I.manLastUpdated = '系統管理員'
            ex100I.validate()
        })
    }

    /**
     * 共同處理
     * @param ex100I
     * @param params
     * @param closure
     * @return
     */
    LinkedHashMap _saveInstance(Ex100 ex100I, GrailsParameterMap params, Closure<?> closure) {
        LinkedHashMap result = [
                actionType:false,
                acrtionMessage:''
        ]
        result.bean = ex100I
        closure(ex100I)

        List include_col = [
                'strings','integers','atms','status','article'
        ]

        bindData(ex100I, params["ex100"], [include:include_col])
        int pageDataVersion = params.ex100?.version?(params.ex100?.version as int):0
        if(ex100I.version != pageDataVersion && params.ex100?.id){

            result.dataVersionDifferent = true
            ex100I.discard()
        } else if (ex100I.hasErrors()) { //失敗

            def errorColumn = []
            ex100I.errors.allErrors.eachWithIndex  {item, index ->
                errorColumn[index] = [item?.arguments,item?.defaultMessage]
            }
            ex100I.discard()
            result.actionType = false
        }
        else{
            try{
                ex100I.save(flush: true)
                result.actionType = true
            }catch(Exception ex){
                result.actionType = false
                ex.printStackTrace()
                ex100I.discard()
            }

            if(result.actionType){
                result.actionMessage = messageSource.getMessage("default.updated.message", [] as Object[], Locale.TAIWAN)
            }
            else{
                result.actionMessage = messageSource.getMessage("default.updated.message", [] as Object[], Locale.TAIWAN)
            }
        }

        return result
    }

    /**
     * 刪除資料
     * @param params
     * @return
     */
    LinkedHashMap delete(GrailsParameterMap params){

        LinkedHashMap result = [
                actionType:false,
                acrtionMessage:''
        ]

        Ex100 ex100I = result.bean = Ex100.get(params.id as long)
        int pageDataVersion = params.ex100.version?(params.ex100?.version as int):0
        if(ex100I.version != pageDataVersion && params.ex100.id){

            result.dataVersionDifferent = true
            ex100I.discard()
        }else{

            try{
                ex100I.delete()
                result.actionType = true
            }catch(Exception ex){
                result.actionType = false
                ex.printStackTrace()
                ex100I.discard()
            }

            if(result.actionType){
                result.actionMessage = messageSource.getMessage("default.deleted.message", [] as Object[], Locale.TAIWAN)
            }
            else{
                result.actionMessage = messageSource.getMessage("default.not.deleted.message", [] as Object[], Locale.TAIWAN)
            }
        }

        return result
    }
}
