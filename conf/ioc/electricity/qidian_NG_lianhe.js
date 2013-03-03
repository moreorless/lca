var ioc = {
	qidian_NG_lianhe : {
		type : "com.cnooc.lca.electricity.excel.Template",
		fields  : {
			sheetIndex : 3,
			stationName : "气电",
			unit : "NG联合",
			procedures : [
				{
					name : "电厂建设",
					// 能耗列表
					consumption : [
					          {name : "标煤", cell : "D,147"},
					          {name : "原油", cell : "E,147"},
					          {name : "天然气", cell : "F,147"},
					          {name : "燃料油", cell : "G,147"},
					          {name : "传统柴油", cell : "H,147"},
					          {name : "汽油", cell : "I,147"},
					          {name : "电力KWh", cell : "J,147"}
					       ],
				    // 排放列表
		            emission : [				          
		                      {name : "CO2",  cell : "D,178"},
					          {name : "CH4", cell : "E,178"}
					       ]
				},
				{
					name : "建材运输",
					// 能耗列表
					consumption : [
					               {name : "标煤", cell : "D,144"},
							       {name : "原油", cell : "E,144"},
							       {name : "天然气", cell : "F,144"},
							       {name : "燃料油", cell : "G,144"},
							       {name : "传统柴油", cell : "H,144"},
							       {name : "汽油", cell : "I,144"},
							       {name : "电力KWh", cell : "J,144"}
					       ],
				    // 排放列表
		            emission : [						          
		                      {name : "CO2",  cell : "D,175"},
					          {name : "CH4", cell : "E,175"}
					       ]
				}
			]
		}
	}
	
};