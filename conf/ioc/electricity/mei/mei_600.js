var ioc = {
	mei_600 : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 1,
			name : "煤电",
			unit : "600MW",
			totalConsumption : "D,305",
			influences : {"全球变暖" : "F,227", "酸化" : "F,238"},
			emissions : {
				"CO2" : {
					"电厂建设" : {"建材生产":"G,189", "建材运输":"G,190"}
				},
				"CH4" : {
					"电厂建设" : {"建材生产":"H,189", "建材运输":"H,190"}
				}
			}
		}
	}
};