var ioc = {
		// 自定义参数的配置文件
		customparam : {
	        "electric" : {          			// 生命周期编码
	        	excel : "",					    // excel文件
	            sheets : [				        // excel sheets
			     { index : 1,     			    // sheet序号，0表示第一页
			       name : '煤电',               // sheet名称 
	            	 params : {"参数1":"G12", "参数1":"G12"}  // 参数名与位置的映射
	             }
	            ]
	        },
	        "transport" : {
	        	excel : "",					    
	             sheets : [{				    
	            	 index : 1,     			
	            	 params : {"参数名":"G12"}  
	             }]
	        },
	        "gas" : {
	        	excel : "",					    
	             sheets : [{				    
	            	 index : 1,     			
	            	 params : {"参数名":"G12"}  
	             }]
    		}
		}
};