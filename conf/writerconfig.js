var ioc = {
		// 自定义参数的配置文件
		writerconfig : {
	        "electric" : {          			// 生命周期编码
	        	excel : "electricity.xls",					    // excel文件
	            sheets : [				        // excel sheets
			     { index : 1,     			    // sheet序号，0表示第一页
			       name : '煤电',               // sheet名称 
	               params : {"年运行时间(小时)":"E6", "年总发电量":"E7"}  // 参数名与位置的映射
	             }
	            ]
	        },
	        "transport" : {
	        	excel : "transport.xls",					    
	             sheets : []
	        },
	        "gas" : {
	        	excel : "gas.xls",					    
	             sheets : []
    		}
		}
};