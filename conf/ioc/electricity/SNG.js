var ioc = {
	SNG_NGlianhe : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 8,
			name : "SNG",
			unit : "联合循环",
			totalConsumption : "D244",
			influences : {"全球变暖" : "F165", "酸化" : "F176", "富营养化" : "F182", "粉尘" : "F183", "光化学臭氧" : "F191", "加权总计" : "F214"},
			emissions : {
				"CO2" : {
					"电厂建设" : {"SNG项目建设" : "D226", "SNG项目建材运输" : "E226", "天然气建设" : "F226", "天然气管道所用钢材运输" : "G226", "天然气电厂建设建材运输" : "M226", "天然气电厂建设" : "N226"},
					"原料开采" : {"煤炭开采加工" : "H226", "煤炭运输" : "I226", "煤转气阶段" : "J226"},
					"原料运输" : {"天然气运输" : "K226"},
					"发电" : {"天然气电厂运行" : "O226"},
					"电厂退役" : {"天然气电厂退役" : "P226", "SNG项目退役" : "L226"},
					"废弃物处理" : {"" : ""}
				},
				"CH4" : {
					"电厂建设" : {"SNG项目建设" : "H101", "SNG项目建材运输" : "H102", "天然气建设" : "H103", "天然气管道所用钢材运输" : "H104", "天然气电厂建设建材运输" : "H110", "天然气电厂建设" : "H111"},
					"原料开采" : {"煤炭开采加工" : "H105", "煤炭运输" : "H106", "煤转气阶段" : "H107"},
					"原料运输" : {"天然气运输" : "H108"},
					"发电" : {"天然气电厂运行" : "H112"},
					"电厂退役" : {"天然气电厂退役" : "H113", "SNG项目退役" : "H109"},
					"废弃物处理" : {"" : ""}
				}
			}
		}
	},

	SNG_NGrelian : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 8,
			name : "SNG",
			unit : "热电联产",
			totalConsumption : "D245",
			influences : {"全球变暖" : "G165", "酸化" : "G176", "富营养化" : "G182", "粉尘" : "G183", "光化学臭氧" : "G191", "加权总计" : "G214"},
			emissions : {
				"CO2" : {
					"电厂建设" : {"SNG项目建设" : "D228", "SNG项目建材运输" : "E228", "天然气建设" : "F228", "天然气管道所用钢材运输" : "G228", "天然气电厂建设建材运输" : "M228", "天然气电厂建设" : "N228"},
					"原料开采" : {"煤炭开采加工" : "H228", "煤炭运输" : "I228", "煤转气阶段" : "J228"},
					"原料运输" : {"天然气运输" : "K228"},
					"发电" : {"天然气电厂运行" : "O228"},
					"电厂退役" : {"天然气电厂退役" : "P228", "SNG项目退役" : "L228"},
					"废弃物处理" : {"" : ""}
				},
				"CH4" : {
					"电厂建设" : {"SNG项目建设" : "H120", "SNG项目建材运输" : "H121", "天然气建设" : "H122", "天然气管道所用钢材运输" : "H123", "天然气电厂建设建材运输" : "H129", "天然气电厂建设" : "H130"},
					"原料开采" : {"煤炭开采加工" : "H124", "煤炭运输" : "H125", "煤转气阶段" : "H126"},
					"原料运输" : {"天然气运输" : "H127"},
					"发电" : {"天然气电厂运行" : "H131"},
					"电厂退役" : {"天然气电厂退役" : "H132", "SNG项目退役" : "H128"},
					"废弃物处理" : {"" : ""}
				}
			}
		}
	}
};