var FusionChartsExt = {
	
	EMPTYDATA_IDENTIFIER: "NA",
	path: "",
	id: 0,
	
	setPath: function(path) {
		this.path = path;
	},
	
	renderNAChart : function(width, height, renderId) {
    chartRef = new FusionCharts(this.path + "/fusion/widgets/charts/cylinder.swf", "EmptyDataChart-" + this.id++, width, height, "0", "0");
    chartRef.setDataURL(this.path + "/fusion/widgets/styles/emptydata.xml");
    chartRef.render(renderId);
	},
	
	renderNAChartForAlertPie: function(width, height, renderId) {
    chartRef = new FusionCharts(this.path + "/fusion/charts/charts/doughnut2d.swf", "EmptyDataChart-" + this.id++, width, height, "0", "0");
    chartRef.setDataURL(this.path + "/fusion/charts/styles/emptydata.xml");
    chartRef.render(renderId);
	}
	
};
