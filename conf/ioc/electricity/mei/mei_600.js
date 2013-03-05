var ioc = {
	mei_600 : {                                   // 这个名字需要与cycleconfig中的cycleNameList的配置保持一致
	    type : "com.cnooc.lca.excel.CommonTemplate",
	    fields  : {
	        sheetIndex : 1,                       // 对应excel中的sheet页,0表示第一个
	        name : "煤电",                         // 发电方式
	        unit : "600MW",                       // 机组
	        totalConsumption : "D305",           // 综合能耗单元格位置
	        influences : {"全球变暖" : "F227", "酸化" : "F238"},  // 影响因子
	        emissions : {                          // 排放
	            "CO2" : {
	                "电厂建设" : {"建材生产":"G189", "建材运输":"G190"},
	                "统一后的工序": {"原始工序1":"G189", "原始工序":"G122"}
	            },
	            "CH4" : {
	                "电厂建设" : {"建材生产":"H189", "建材运输":"H190"}
	            }
	        }
	    }
	}
};