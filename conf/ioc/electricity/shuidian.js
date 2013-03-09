var ioc = {
	shuidian_dazhongxing : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 4,
			name : "水电",
			unit : "大中型",
			totalConsumption : "E384",
			influences : {"全球变暖" : "H330", "酸化" : "H333", "富营养化" : "H335", "粉尘" : "H339", "光化学臭氧" : "H336", "加权总计" : "M330"},
			emissions : {
				"CO2" : {
					"电厂建设" : {"开挖土石" : "I251", "填筑土石" : "I252", "运输过程" : "I253", "建设耗材" : "I254"},
					"原料开采" : {"" : ""},
					"原料运输" : {"" : ""},
					"发电" : {"电厂运行" : "I255"},
					"电厂退役" : {"" : ""},
					"废弃物处理" : {"" : ""}
				},
				"CH4" : {
					"电厂建设" : {"开挖土石" : "M251", "填筑土石" : "M252", "运输过程" : "M253", "建设耗材" : "M254"},
					"原料开采" : {"" : ""},
					"原料运输" : {"" : ""},
					"发电" : {"电厂运行" : "M255"},
					"电厂退役" : {"" : ""},
					"废弃物处理" : {"" : ""}
				}
			}
		}
	},

	shuidian_choushui : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 4,
			name : "水电",
			unit : "抽水蓄能",
			totalConsumption : "E391",
			influences : {"全球变暖" : "H340", "酸化" : "H343", "富营养化" : "H345", "粉尘" : "H349", "光化学臭氧" : "H346", "加权总计" : "M340"},
			emissions : {
				"CO2" : {
					"电厂建设" : {"开挖土石" : "I265", "填筑土石" : "I266", "运输过程" : "I267", "建设耗材" : "I268"},
					"原料开采" : {"" : ""},
					"原料运输" : {"" : ""},
					"发电" : {"电厂运行" : "I269"},
					"电厂退役" : {"" : ""},
					"废弃物处理" : {"" : ""}
				},
				"CH4" : {
					"电厂建设" : {"开挖土石" : "M265", "填筑土石" : "M266", "运输过程" : "M267", "建设耗材" : "M268"},
					"原料开采" : {"" : ""},
					"原料运输" : {"" : ""},
					"发电" : {"电厂运行" : "M269"},
					"电厂退役" : {"" : ""},
					"废弃物处理" : {"" : ""}
				}
			}
		}
	}
};