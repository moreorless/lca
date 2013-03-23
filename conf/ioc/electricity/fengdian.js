var ioc = {
	fengdian : {
		type : "com.cnooc.lca.excel.CommonTemplate",
		fields  : {
			sheetIndex : 6,
			name : "风电",
			unit : "",
			totalConsumption : "C197",
			consumptions : {
				"电厂建设" : "C206",
				"原料开采" : "C207",
				"原料运输" : "C208",
				"发电" :     "C209",
				"电厂退役" : "C210",
				"废弃物处理" : "C211"
			},
			influences : {"全球变暖" : "E135", "酸化" : "E145", "富营养化" : "E153", "粉尘" : "E154", "光化学臭氧" : "E162", "加权总计" : "E183"},
			// 分工序的影响潜能（加权）
			procInfluences : {
				"电厂建设" : "E206",
				"原料开采" : "E207",
				"原料运输" : "E208",
				"发电" :     "E209",
				"电厂退役" : "E210",
				"废弃物处理" : "E211"
			},
			emissions : {
				"CO2" : {
					"电厂建设" : {"原材料开采、运输、冶炼和加工、风机制造过程" : "C201", "风机制造材料运输半径估算" : "D201", "风机运输过程" : "E201", "风电场建设过程" : "F201"},
					"原料开采" : {"" : ""},
					"原料运输" : {"" : ""},
					"发电" : {"风电场运行维护阶段" : "G201"},
					"电厂退役" : {"风电场报废" : "H201"},
					"废弃物处理" : {"" : ""}
				},
				"CH4" : {
					"电厂建设" : {"原材料开采、运输、冶炼和加工、风机制造过程" : "C202", "风机制造材料运输半径估算" : "D202", "风机运输过程" : "E202", "风电场建设过程" : "F202"},
					"原料开采" : {"" : ""},
					"原料运输" : {"" : ""},
					"发电" : {"风电场运行维护阶段" : "G202"},
					"电厂退役" : {"风电场报废" : "H202"},
					"废弃物处理" : {"" : ""}
				}
			}
		}
	}
};