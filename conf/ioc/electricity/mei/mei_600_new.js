var ioc = {
	mei_600 : {
		type : "com.cnooc.lca.electricity.excel.Template",
		fields  : {
			sheetIndex : 1,
			stationName : "煤电",
			unit : "600MW",
			totalConsumption : "G,121",
			influences : {"温室" : "G,177", "xxx" : "G,177"},
			emissions : {
				"CO2" : {
					"电厂建设" : {"建材生产安装":"G,122", "建材生产安装":"G,122"}
				},
				"CH4" : {
					"电厂建设" : {"建材生产安装":"G,122", "建材生产安装":"G,122"}
				}
			}
		}
	}
	
};