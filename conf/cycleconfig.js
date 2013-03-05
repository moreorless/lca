var ioc = {

		cycleconfig : {
	        "electric" : {                         // 生命周期编码
	                name : "发电",                 // 生命周期名称
	                excel : "electricity.xls",     // 对应的excel文件
	                cycleNameList : ["mei_600", "hedian"]    // 对应excel中的各个sheet

	        },
	        "transport" : {
	                name : "交通燃料",
	                excel : "transport.xls",
	                cycleNameList : []

	        },
	        "gas" : {
	                name : "天然气产业链",
	                excel : "gas.xls",
	                cycleNameList : []

	            }
	    }
};