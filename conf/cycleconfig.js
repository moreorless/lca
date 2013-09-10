var ioc = {
	/**
	 * 生命周期配置
	 */
	cycleconfig : {
		"electric" : { // 生命周期编码
			name : "发电", // 生命周期名称
			excel : "electricity.xls", // 对应的excel文件
			cycleNameList : [ "meidian_600", "SNG_NGlianhe", "SNG_NGrelian",
					"qidian_NGlianhe", "qidian_LNGlianhe", "qidian_NGrelian",
					"youdian", "hedian", "shuidian_dazhongxing",
					"shuidian_choushui", "taiyangneng_guangfu",
					"taiyangneng_guangre", "fengdian" ]
		// 对应excel中的各个sheet

		},
		"transport" : {
			name : "交通燃料",
			excel : "transport.xls",
			cycleNameList : [ "transport_qiyou", "transport_chaiyou",
					"transport_diandong", "transport_lng",
					"transport_yumiyichun", "transport_shengwuchaiyou",
					"transport_cng" ]
		}
	/*
	 * , "gas" : { name : "天然气产业链", excel : "gas.xls", cycleNameList : [] }
	 */
	},

	/**
	 * 系统内默认的工序设置
	 */
	defaultProcedures : {
		"electric" : [ "电厂建设", "原料开采", "原料运输", "发电", "电厂退役", "废弃物处理" ], // 默认的工序阶段
		"transport" : [ "原料开采", "原料运输", "燃料制备", "燃料运输", "燃料使用" ],
		"gas" : []
	}
};