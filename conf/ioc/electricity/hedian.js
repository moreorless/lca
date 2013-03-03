var ioc = {
	hedian : {
		type : "com.cnooc.lca.electricity.excel.Template",
		fields  : {
			sheetIndex : 2,
			stationName : "核电",
			unit : "",
			procedures : [
				{
					name : "建材生产与电厂建设",
					// 能耗列表
					consumption : [
					          {name : "天然气", cell : "B,45"},
					          {name : "标煤", cell : "C,45"},
					          {name : "汽油", cell : "D,45"},
					          {name : "柴油", cell : "E,45"}
					       ],
				    // 排放列表
		            emission : [				          
		                      {name : "CO2",  cell : "F,45"},
					          {name : "CH4", cell : "G,45"}
					       ]
				},
				{
					name : "燃料生产过程",
					// 能耗列表
					consumption : [
					          {name : "天然气", cell : "B,76"},
					          {name : "标煤", cell : "C,76"},
					          {name : "汽油", cell : "D,76"},
					          {name : "柴油", cell : "E,76"}
					       ],
				    // 排放列表
		            emission : [						          
		                      {name : "CO2",  cell : "F,76"},
					          {name : "CH4", cell : "G,76"}
					       ]
				}
			]
		}
	}
	
};