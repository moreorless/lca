var ioc = {
	transport_locallng : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 2,
			name : "国产LNG车",
			unit : "",
			totalConsumption : "J61",
			consumptions : {
				"原料开采" : "E61",
				"原料运输" : "F61",
				"燃料制备" : "G61",
				"燃料运输" : "H61",
				"燃料使用" : "I61"
			},
			emissions : {
				"CO2" : {
					"原料开采" : {"原料开采" : "E63"},
					"原料运输" : {"原料运输" : "F63"},
					"燃料制备" : {"燃料制备" : "G63"},
					"燃料运输" : {"燃料运输" : "H63"},
					"燃料使用" : {"燃料使用" : "I63"}
				},
				"CH4" : {
					"原料开采" : {"原料开采" : "E64"},
					"原料运输" : {"原料运输" : "F64"},
					"燃料制备" : {"燃料制备" : "G64"},
					"燃料运输" : {"燃料运输" : "H64"},
					"燃料使用" : {"燃料使用" : "I64"}
				},
				"N2O" : {
					"原料开采" : {"原料开采" : "E65"},
					"原料运输" : {"原料运输" : "F65"},
					"燃料制备" : {"燃料制备" : "G65"},
					"燃料运输" : {"燃料运输" : "H65"},
					"燃料使用" : {"燃料使用" : "I65"}
				}
			}
		}
	}
};