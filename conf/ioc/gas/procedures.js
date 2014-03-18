var ioc = {
	/**
	 * 天然气产业链各阶段基础数据配置
	 */
	gas_procedures : {
		type : "com.cnooc.lca.excel.ProcedureTemplate",
		fields  : {
			name : "天然气产业链各阶段基础数据",
			procedures : [
					{	type : "com.cnooc.lca.excel.ProcedureParam",
						fields : {
							name : "开采",
							multiple : false,			// 是否允许多选
							items : [
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "陆上气", consumption : "0,B4", emission : "0,B21"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "海上气", consumption : "0,B5", emission : "0,B22"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "煤制气", consumption : "0,B6", emission : "0,B23"
							    	}
							    }
							]
						}
					},
					{	type : "com.cnooc.lca.excel.ProcedureParam",
						fields : {
							name : "制备",
							multiple : false,			// 是否允许多选
							items : [
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "LNG", consumption : "0,D5", emission : "0,D22"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "CNG", consumption : "0,D4", emission : "0,D21"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "管道气", consumption : "0,D6", emission : "0,D23"
							    	}
							    }
							]
						}
					},
					{	type : "com.cnooc.lca.excel.ProcedureParam",
						fields : {
							name : "运输",
							multiple : true,			// 是否允许多选
							items : [
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "水运", consumption : "0,F4", emission : "0,F21"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "陆运", consumption : "0,F5", emission : "0,F22"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "管道运输", consumption : "0,F6", emission : "0,F23"
							    	}
							    }
							]
						}
					},
					{	type : "com.cnooc.lca.excel.ProcedureParam",
						fields : {
							name : "利用",
							multiple : false,			// 是否允许多选
							items : [
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "发电", consumption : "0,H4", emission : "0,H21"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "交通", consumption : "0,H5", emission : "0,H22"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "工业用气", consumption : "0,H6", emission : "0,H23"
							    	}
							    },
							    {
							    	type : "com.cnooc.lca.excel.ProcedureParamItem",
							    	fields : {
							    		name : "城镇燃气", consumption : "0,H7", emission : "0,H24"
							    	}
							    }
							]
						}
					}
				]
		}
	}
};