var ioc = {
	transport_foreignlng : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 2,
			name : "进口LNG车",
			unit : "",
			totalConsumption : "J50",
			consumptions : {
				"原料开采" : "E50",
				"原料运输" : "F50",
				"燃料制备" : "G50",
				"燃料运输" : "H50",
				"燃料使用" : "I50"
			},
			emissions : {
				"CO2" : {
					"原料开采" : {"原料开采" : "E52"},
					"原料运输" : {"原料运输" : "F52"},
					"燃料制备" : {"燃料制备" : "G52"},
					"燃料运输" : {"燃料运输" : "H52"},
					"燃料使用" : {"燃料使用" : "I52"}
				},
				"CH4" : {
					"原料开采" : {"原料开采" : "E53"},
					"原料运输" : {"原料运输" : "F53"},
					"燃料制备" : {"燃料制备" : "G53"},
					"燃料运输" : {"燃料运输" : "H53"},
					"燃料使用" : {"燃料使用" : "I53"}
				},
				"N2O" : {
					"原料开采" : {"原料开采" : "E54"},
					"原料运输" : {"原料运输" : "F54"},
					"燃料制备" : {"燃料制备" : "G54"},
					"燃料运输" : {"燃料运输" : "H54"},
					"燃料使用" : {"燃料使用" : "I54"}
				}
			}
		}
	}
};