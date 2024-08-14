package ex


class Ex100 {

    static auditable = [ignore: ['dateCreated', 'lastUpdated', 'manCreated', 'manLastUpdated']]
    String		manCreated
    Date		dateCreated = new Date()
    String		manLastUpdated
    Date		lastUpdated
    String		note
    String		strings
    Integer		integers
    Integer		atms
    Integer		status = 0
    String		article


    static mapping = {
        table 'EX100'
        comment '範例表單'
        version true
        //---1.objid PK0
        id					column:"OBJID"
        manCreated			column:"MAN_CREATED",		comment:"建檔人員"
        dateCreated			column:"DATE_CREATED",		comment:"建檔時間"
        manLastUpdated		column:"MAN_LAST_UPDATED",	comment:"最後異動人員"
        lastUpdated			column:"LAST_UPDATED",		comment:"最後異動時間"
        note				column:"NOTE",				comment:"資料註記"
        strings				column:"STRINGS",			comment:"字串"
        integers			column:"INTEGERS",			comment:"數字"
        atms				column:"ATMS",				comment:"金額"
        status				column:"STATUS",			comment:"流程狀態"
        article				column:"ARTICLE",			comment:"文章",	type:"text"
    }


    static constraints = {
        manCreated			(nullable:false, blank: false, maxSize: 20)
        dateCreated			(nullable:false, blank: false)
        manLastUpdated		(nullable:true, maxSize: 20)
        lastUpdated			(nullable:true)
        note				(nullable:true, maxSize: 50)
        strings				(nullable:true, maxSize: 50)
        integers			(nullable:true)
        atms				(nullable:true)
        status				(nullable:true, attributes: [bs101: "ex100_stasus"] )
        article				(nullable:true)
    }

}

