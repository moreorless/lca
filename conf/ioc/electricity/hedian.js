var ioc = {
	hedian : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 2,
			name : "核电",
			unit : "",
			totalConsumption : "E208",
			consumptions : {
				"电厂建设" : "C218",
				"原料开采" : "C219",
				"原料运输" : "C220",
				"发电" :     "C221",
				"电厂退役" : "C222",
				"废弃物处理" : "C223"
			},
			influences : {"全球变暖" : "F143", "酸化" : "F154", "富营养化" : "F160", "粉尘" : "F161", "光化学臭氧" : "F169", "加权总计" : "F192"},
			procInfluences : {
				"电厂建设" : "E218",
				"原料开采" : "E219",
				"原料运输" : "E220",
				"发电" :     "E221",
				"电厂退役" : "E222",
				"废弃物处理" : "E223"
			},
			emissions : {
				"CO2" : {
					"电厂建设" : {"建材生产运输与电厂建设" : "D197"},
					"原料开采" : {"燃料生产" : "E197"},
					"原料运输" : {"燃料运输" : "F197"},
					"发电" : {"" : ""},
					"电厂退役" : {"电厂退役" : "H197"},
					"废弃物处理" : {"乏燃料运输" : "G197"}
				},
				"CH4" : {
					"电厂建设" : {"建材生产运输与电厂建设" : "H106"},
					"原料开采" : {"燃料生产" : "H107"},
					"原料运输" : {"燃料运输" : "H108"},
					"发电" : {"" : ""},
					"电厂退役" : {"电厂退役" : "H110"},
					"废弃物处理" : {"乏燃料运输" : "H109"}
				}
			}
		}
	}
};