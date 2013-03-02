var ioc = {
	mei_600 : {
		type : "com.cnooc.lca.electricity.excel.Template",
		fields  : {
			sheetIndex : 1,
			stationName : "煤电",
			unit : "600MW",
			procedures : [
				{
					name : "建材生产与电厂建设",
					// 能耗列表
					consumption : [
					          {name : "天然气", cell : "C,177"},
					          {name : "标煤", cell : "D,177"},
					          {name : "油", cell : "E,177"}
					       ],
				    // 排放列表
		            emission : [				          
		                      {name : "CO2",  cell : "G,177"},
					          {name : "CH4", cell : "H,177"}
					       ]
				},
				{
					name : "建材运输",
					// 能耗列表
					consumption : [
					          {name : "天然气", cell : "C,178"},
					          {name : "标煤", cell : "D,178"},
					          {name : "油", cell : "E,178"}
					       ],
				    // 排放列表
		            emission : [						          
		                      {name : "CO2",  cell : "G,178"},
					          {name : "CH4", cell : "H,178"}
					       ]
				}
			]
		}
	}
	
};