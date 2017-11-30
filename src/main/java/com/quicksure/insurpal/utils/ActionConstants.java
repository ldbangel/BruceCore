package com.quicksure.insurpal.utils;

public enum ActionConstants {
	
	//计算保费(提交车辆信息首次报价请求)  	渠道-->核心
	PREMIUM_COUNT_BY_INSURPAL_ONE("PremiumCountByInsurpalNo1", "template_PremiumCount.xml", "PACKET/BODY", ""),
	//计算保费 (提交车辆信息首次报价失败再次报价请求) 	渠道-->核心
    PREMIUM_COUNT_BY_INSURPAL_TWO("PremiumCountByInsurpalNo2", "template_PremiumCount.xml", "PACKET/BODY", ""),
    //计算保费(雷达报价确认再次请求华安报价)  	渠道-->核心
    PREMIUM_COUNT_BY_INSURPAL_THREE("PremiumCountByInsurpalNo3", "template_PremiumCount.xml", "PACKET/BODY", ""),
	
	//计算保费(提交车辆信息首次报价请求)		华安-->核心
	PREMIUM_COUNT_BY_SINOSAFE_ONE("PremiumCountBySinosafeNo1", "", "PACKET/BODY", "PACKET/HEAD"),
	//计算保费(提交车辆信息首次报价失败再次报价请求)		华安-->核心
	PREMIUM_COUNT_BY_SINOSAFE_TWO("PremiumCountBySinosafeNo2", "", "PACKET/BODY", "PACKET/HEAD"),
    //计算保费(雷达报价确认再次请求华安报价)		华安-->核心
	PREMIUM_COUNT_BY_SINOSAFE_THREE("PremiumCountBySinosafeNo3", "", "PACKET/BODY", "PACKET/HEAD"),
	//计算保费              雷达-->核心
	PREMIUM_COUNT_FROM_RADAR_TO_INSURPAL("PremiumCountToInsurpal", "", "PACKET/BODY", ""),
	//计算保费              核心-->雷达
    PREMIUM_COUNT_FROM_INSURPAL_TO_RADAR("PremiumCountToRadar", "template_Radarinfor.xml", "", ""),
	
	//提交核保		渠道-->核心
	SUBMIT_UNDERWRITING_BY_INSURPAL("SubmitUnderwritingByInsurpal","template_SubmitUnderwriting.xml","PACKET/BODY", "PACKET/HEAD"),
	//提交核保  	核心-->华安
	SUBMIT_UNDERWRITING_BY_SINOSAFE("SubmitUnderwritingBySinosafe", "", "PACKET/BODY", "PACKET/HEAD"),
	
	//立即支付        渠道 -->核心
	PAYMENT_APPLICATION_BY_INSURPAL("PaymentApplicationByInsurpal","template_PaymentApplication.xml","PACKET/BODY","PACKET/HEAD"),
	//立即支付        核心-->华安
	PAYMENT_APPLICATION_BY_SINOSAFE("","","",""),
	
	//保单配送        渠道 -->核心
	APPLICATION_DELIVERY_BY_INSURPAL("ApplicationDeliveyByInsurpal","template_ModelDeliveryInforToJY.xml","PACKET/BODY","Packet/HEAD"),
	//保单配送         核心-->华安
	APPLICATION_DELIVERY_BY_SINOSAFE("","","",""),
	
	//续保		渠道-->核心
    RENEWAL_INFORQUERY_BY_INSURPAL("RenewalInforQueryByInsurpal","template_RenewalinforQuery.xml","PACKET/BODY","PACKET/HEAD"),
    //续保 		核心-->华安
    RENEWAL_INFORQUERY_BY_SINOSAFE("RenewalInforQueryBySinosafe","","PACKET/BODY","PACKET/HEAD"),
	
	//车辆查询      	渠道-->核心
	MODEL_SEARCH_BY_INSURPAL("ModelSearchByInsurpal", "template_ModelSearchFromSinosafe.xml", "PACKET/BODY", "PACKET/HEAD"), 
	//车辆查询        华安-->核心
	MODEL_SEARCH_BY_SINOSAFE("ModelSearchBySinosafe", "", "PACKET/BODY", "PACKET/HEAD"),
	
	//撤单               渠道-->核心
	ORDER_CANCEL_BY_INSURPAL("OrderCancelByInsurpal","template_ModelPolicyCancelFromSinosafe.xml","PACKET/BODY","PACKET/HEAD"),
	//撤单               华安-->核心
	ORDER_CANCEL_BY_SINOSAFE("OrderCancelBySinosafe","","",""),
	
	
	
    //非车======================================================================================================================================
	//非车提核   	渠道-->核心
	JIAYI_REQEUST_BY_INSURPAL("JiaYiRequestByInsurpal","template_NoncarjiayixianUnderwriting.xml","PACKET",""),
	JUNAN_REQUEST_BY_INSURPAL("JunAnRequestByInsurpal","template_NoncarJunanbaoUnderwriting.xml","PACKET",""),
	YILU_REQUEST_BY_INSURPAL("YiLuRequestByInsurpal","template_NoncarYilupinganUnderwriting.xml","PACKET",""),
	
	//非车提交核保  	华安-->核心
	JIAYI_REQUEST_BY_SINOSAFE("JiaYiRequestBySinosafe", "template_NoncarjiayixianUnderwriting.xml", "PACKET/BODY", "PACKET/HEAD"),
	JUNAN_REQUEST_BY_SINOSAFE("JunAnRequestBySinosafe", "template_NoncarJunanbaoUnderwriting.xml", "PACKET/BODY", "PACKET/HEAD"),
	YILU_REQUEST_BY_SINOSAFE("YiLuRequestBySinosafe", "template_NoncarYilupinganUnderwriting.xml", "PACKET/BODY", "PACKET/HEAD"),
	//非车处理响应报文
	FC_PROCESS_DATA_BY_RESPONSEXML("FcprocessResponsexml", "", "PACKET", "");
	
	public String value;
	//用来组装报文的xml模板名称(要加.xml)
	public String templatePath;
	//解析主要内容的节点
	public String analysisPath;
	//解析头部信息的节点(目前就是华安返回的需要解析)
	public String headPath;
	
	ActionConstants(String value,String templatePath,String analysisPath,String headPath){
		this.value = value;
		this.templatePath = templatePath;
		this.analysisPath = analysisPath;
		this.headPath = headPath;
	}

	public String getValue() {
		return value;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public String getAnalysisPath() {
		return analysisPath;
	}

	public String getHeadPath() {
		return headPath;
	}
}
