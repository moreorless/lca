package com.cnooc.lca.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cnooc.lca.excel.ProcedureParamItem;
import com.cnooc.lca.excel.parser.WritableExcel;
import com.cnooc.lca.service.CycleType;


/**
 * 全周期模板
 * @author gaoxl
 *
 */
public class T_Cycle {

	public T_Cycle(CycleType cycleType){
		this.cycleType = cycleType;
	}
	
	private CycleType cycleType;
	
	public CycleType getCycleType() {
		return cycleType;
	}
	public void setCycleType(CycleType cycleType) {
		this.cycleType = cycleType;
	}

	/**
	 * excel表页序号，从0开始
	 */
	private int sheetIndex;
	
	
	/**
	 * 发电站名称
	 */
	private String name;
	
	/**
	 * 发电站编码（程序内部使用） 使用js配置文件中的对象名，如mei_600
	 */
	private String code;
	
	/**
	 * 电站机组
	 */
	private String unit;
	
	/**
	 * 工序集合
	 */
	private List<T_Procedure> procedures;
	
	/**
	 * 天然气产业链使用的阶段列表
	 */
	private List<ProcedureParamItem[]> procedureParamItemList;
	
	/**
	 * 天然气产业链使用的运输距离数据，按照gas/procedures.js配置的运输次序写入，以逗号分割，默认值1000,1000,1000
	 */
	private String transDistStr;
	
	/**
	 * 天然气产业链使用的工序序号集合(阶段1序号|阶段2序号1,阶段2序号2|...|阶段n序号)
	 */
	private String procedureIndexStr;
	
	/**
	 * 综合能耗
	 */
	private double totalConsumption;
	
	/**
	 * 总排放
	 */
	private double totalEmission;
	
	/**
	 * 排放的分类汇总，交通燃料统计时，没有分阶段的统计项，所以，先用一个总的统计值
	 * <p>{CO2, xx} {CH4, xx}</p>
	 */
	private Map<String, Double> totalEmissionMap;
	
	/**
	 * 总影响潜能
	 */
	private double totalInfluence;
	
	/**
	 * 影响潜能集合 <code>{影响潜能， 值}</code>
	 */
	private Map<String, Double> influenceMap;
	
	/**
	 * 分阶段的影响潜能 <code>{工序名称， 影响潜能汇总}</code>
	 */
	private Map<String, Double> procInfluenceMap;
	
	/**
	 * 综合能耗结合 <code>{工序， 能耗值}</code>
	 */
	private Map<String, Double> consumptionMap;
	
	/**
	 * 排放集合  <code>{排放物, {工序， 排放值}}</code>
	 */
	private Map<String, Map<String, Double>> emissionMap;
	
	/**
	 * 合并后的排放，将各排放物加和
	 */
	private Map<String, Double> mergedEmissionMap;
	
	/**
	 * 参数配置
	 */
	private WritableExcel paramConfigure;
	
	public int getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<T_Procedure> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<T_Procedure> procedures) {
		this.procedures = procedures;
	}
	
	public List<ProcedureParamItem[]> getProcedureParamItemList() {
		return procedureParamItemList;
	}
	public void setProcedureParamItemList(
			List<ProcedureParamItem[]> procedureParamItemList) {
		this.procedureParamItemList = procedureParamItemList;
	}
	
	
	public String getTransDistStr() {
		return transDistStr;
	}
	public void setTransDistStr(String transDistStr) {
		this.transDistStr = transDistStr;
	}
	public String getProcedureIndexStr() {
		return procedureIndexStr;
	}
	public void setProcedureIndexStr(String procedureIndexStr) {
		this.procedureIndexStr = procedureIndexStr;
	}
	
	public double getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	
	public Map<String, Double> getInfluenceMap() {
		return influenceMap;
	}
	public void setInfluenceMap(Map<String, Double> influenceMap) {
		this.influenceMap = influenceMap;
	}
	
	
	
	
	public Map<String, Double> getProcInfluenceMap() {
		return procInfluenceMap;
	}
	public void setProcInfluenceMap(Map<String, Double> procInfluenceMap) {
		this.procInfluenceMap = procInfluenceMap;
	}
	public Map<String, Double> getConsumptionMap() {
		return consumptionMap;
	}
	public void setConsumptionMap(Map<String, Double> consumptionMap) {
		this.consumptionMap = consumptionMap;
	}
	public Map<String, Map<String, Double>> getEmissionMap() {
		return emissionMap;
	}
	public void setEmissionMap(Map<String, Map<String, Double>> emissionMap) {
		this.emissionMap = emissionMap;
	}
	
	public double getTotalEmission() {
		mergeEmissions();
		return this.totalEmission;
	}
	public void setTotalEmission(double totalEmission) {
		this.totalEmission = totalEmission;
	}
	
	
	
	
	public Map<String, Double> getTotalEmissionMap() {
		return totalEmissionMap;
	}
	public void setTotalEmissionMap(Map<String, Double> totalEmissionMap) {
		this.totalEmissionMap = totalEmissionMap;
	}
	public double getTotalInfluence() {
		mergeInfluence();
		return totalInfluence;
	}
	public void setTotalInfluence(double totalInfluence) {
		this.totalInfluence = totalInfluence;
	}
	public WritableExcel getParamConfigure() {
		return paramConfigure;
	}
	public void setParamConfigure(WritableExcel paramConfigure) {
		this.paramConfigure = paramConfigure;
	}

	private boolean _emissionMerged = false;
	public Map<String, Double> getMergedEmissionMap() {
		mergeEmissions();
		return mergedEmissionMap;
	}
	
	private void mergeEmissions(){
		if(_emissionMerged){
			return;
		}
		
		this.totalEmission = 0;   // 所有排放的求和
		
		this.mergedEmissionMap = new LinkedHashMap<>();
		
		if(this.emissionMap != null){
			
			for(String emissionName : this.emissionMap.keySet()){
				if(emissionName.equals("total")) continue;		// 跳过合并项
				
				Map<String, Double> procMap = emissionMap.get(emissionName);
				double mergedValue = 0;
				for(Double v : procMap.values()){
					mergedValue += v;
				}
				this.mergedEmissionMap.put(emissionName, mergedValue);
				
				// 总排放的计算公式改为CO2+CH4*25+N2O*298
				int weight = Contant.getEmisstionWeight(emissionName);
				this.totalEmission += mergedValue*weight;
			}
		}else if(this.totalEmissionMap != null){	// 如果分阶段的排放未配置，则使用汇总的排放配置
			for(String emissionName : this.totalEmissionMap.keySet()){
				if(emissionName.equals("total")) continue;		// 跳过合并项
				int weight = Contant.getEmisstionWeight(emissionName);
				this.totalEmission += this.totalEmissionMap.get(emissionName) * weight;
			}
			this.totalEmissionMap.put("total", totalEmission);
		}
		
		_emissionMerged = true;
	}
	
	private boolean _influenceMerged = false;
	private void mergeInfluence(){
		if(_influenceMerged) return;
		this.totalInfluence = 0;
		if(procInfluenceMap != null){
			for(String key : procInfluenceMap.keySet()){
				totalInfluence += procInfluenceMap.get(key);
			}
			_influenceMerged = true;
		}
	}
	
	public void setMergedEmissionMap(Map<String, Double> mergedEmissionMap) {
		this.mergedEmissionMap = mergedEmissionMap;
	}
	
	
	
//	/**
//	 * 从各个工序计算综合能耗
//	 * @return
//	 */
//	public void calcTotalConsumption(){
//		this.totalConsumption = 0;
//		Iterator<T_Procedure> iter = this.procedures.iterator();
//		while(iter.hasNext()){
//			this.totalConsumption += iter.next().getTotalConsumption();
//		}
//	}
	
}
