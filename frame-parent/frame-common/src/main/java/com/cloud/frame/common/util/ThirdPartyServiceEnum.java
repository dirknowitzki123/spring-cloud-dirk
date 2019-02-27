package com.cloud.frame.common.util;

public enum ThirdPartyServiceEnum {
	ELE_SALE_GETPRODNO("电销接口服务-获取电销产品","ELE_SALE"),
	ELE_SALE_TELEINTFSVR("电销接口服务-电销确认能否办单","ELE_SALE"),
	ELE_SALE_TELECUSTCHK("电销接口服务-校验客户是否为电销客户","ELE_SALE"),
	ELE_SALE_WHITELIST("电销接口服务-校验客户是否为在电销白名单中","ELE_SALE"),
	JIUFU_UPDATESTATUS("玖富接口服务--工单确认","JIUFU"),
	JIUFU_PRELOANADD("玖富接口服务--玖富贷前数据","JIUFU"),
	JIUFU_CREATE_CUSTNO("玖富接口服务--创建客户号", "JIUFU"),
	JIUFU_OPEN_ACCOUNT("玖富接口服务--个人开户", "JIUFU"),
	JIUFU_OPEN_ACCOUNT_PRODS("玖富接口服务--开户产品", "JIUFU"),
	CONTRACT_GENERATE("玖富接口服务--生成合同","CONTRACT"),
	CONTRACT_SIGNQUERYURL("合同接口服务--上上签签约地址","CONTRACT"),
	CONTRACT_SIGNQUERYINFO("合同接口服务--查询合同信息","CONTRACT"),
	CONTRACT_QUERY_SIGNED_LIST("合同接口服务--查询已签约合同列表","CONTRACT"),
	BIGATA_AUTOAPPRO("大数据接口服务--自动审批(任信用)","BIGATA"),
	BIGATA_APPLYMOBILE("大数据接口服务--申请电话","BIGATA"),
	BIGATA_FINALLY("大数据接口服务--提交进件时，大数据最后一次校验","BIGATA"),
	BIGATA_LOGINMOBILE("大数据接口服务--用于进行电话运营商、电商（京东、淘宝）的账号登录，进行后续数据的抓取采集","BIGATA"),
	BIGATA_APPLYFACE("大数据接口服务--人脸识别","BIGATA"),
	BIGATA_MOBILE("大数据接口服务--登录手机运营商获取通话详单","BIGATA"),
	BIGATA_SECOND_FINALLY("大数据接口服务--二次提交进件时，大数据最后一次校验","BIGATA"),
	BIGATA_SUBMITDATAAPPLY("大数据接口服务--数据采集","BIGATA"),
	BIGATA_SUBMITLOGIN("大数据接口服务--大数据获取通话详单提交","BIGATA"),
	BIGATA_ACCOUNT_ONLINE_CHECK("大数据接口服务--线上注册验证","BIGATA"),
	BIGATA_UPLOAD_CALLLIST("大数据接口服务--上传客户通话详单","BIGATA"),
	BIGATA_UPLOAD_CONTACTLIST("大数据接口服务--上传客户通讯录","BIGATA"),
	BIGATA_SAVE_SIMILARITY("大数据接口服务--人脸相似度保存", "BIGATA"),
	BIGATA_CHECK_AUTH("大数据接口服务--电商授权验证", "BIGATA"),
	BIGATA_AUTH_URL("大数据接口服务--电商授权链接获取", "BIGATA"),
	JIUFU_CONFIRMPORDER("玖富接口服务--玖富工单确认","JIUFU"),
	JIUFU_DESTROYACCT("玖富接口服务--玖富销户","JIUFU"),
	ELE_QUERY_CUST("电销接口服务-电销分配客户查询","ELE_SALE"),
	ELE_QUERY_CREDIT("电销接口服务-查询客户在电销系统的额度","ELE_SALE"),
	ELE_REGIST("电销接口服务-客户在电销系统注册","ELE_SALE"),
	ELE_FEEDBANK("电销接口服务-电客户问题反馈","ELE_SALE"),
	BIGATA_CHECK_BANKCARD("大数据接口服务-银行卡实名验证","BIGATA"),
	BIGATA_AUTOAPPRO_OTHER("大数据接口服务--自动审批(非任信用)","BIGATA"),
	SEND_MSG("短信服务","MSG"),
	ELE_SALE_CHANNEL_SAVE("电销接口服务-客户渠道信息保存","ELE_SALE"),
	JIUFU_ORIDERCONFIRM("玖富接口服务--玖富工单确认","JIUFU"),
	RONG360_APPLYCHECK("rong360可申请用户判定","BY_DM"),
	RONG360_ORDERINFO("rong360基本信息中订单信息推送","BY_DM"),
	RONG360_ZMAUTHURL("rong360获取芝麻信用授权地址","BY_DM"),
	RONG360_DM_EXTRAINFO("rong360补充信息推送大数据","BY_DM"),
	RONG360_DM_DATA_BANKCARDTYPE("rong360银行卡效验","BY_DM"),
	OTHER_CHANNEL_DM_DATA_ADMITJUDGE("第三方调用银行卡四要素验证","BY_DM"),
	OTHER_CHANNEL_ZMAUTHURL("第三方调用获取芝麻信用授权地址","BY_DM"),
	BIGATA_CHECK_INFOANDRISK("大数据接口服务--同盾用户信息及其风险项检验", "BIGATA"),
	BIGATA_GET_VEHICLE_LOAN_INFO("大数据接口服务--车主贷信息采集", "BIGATA"),
	BIGATA_GET_ZMSCORE_URL("大数据接口服务--获取芝麻分链接", "BIGATA"),
	//////////////////////卡牛
	CardNiu_DM_DATA_ADMITJUDGE("卡牛可申请用户判定","BY_DM"),
	CardNiu_DM_DATA_PUSH("卡牛用户推送至大数据","BY_DM"),
	DM_DATA_BANKCARD_VERIFY("银行卡四要素验证","BY_DM"),
	CARDNIU_RXY_FEEDBACK("卡牛任信用业务数据回馈","CARDNIU"),

	RXY_51CREDIT_NEWUSER_CALLBACK("我爱卡任信用新用户回调通知", "51CREDIT"),
	
	ZHAOYI_SAVE_USERQUOTA("大数据接口服务--招易卡保存用户额度","BIGATA"),
	ZHAOYI_QUERY_USERQUOTA("大数据接口服务--招易卡查询用户额度","BIGATA"),
	ZHAOYI_SAVE_PURCHASERECORD("大数据接口服务--招易卡用户消费记录","BIGATA"),
	ELE_RECV_UNSUBMIT_CUSTINFO("电销接口服务-把用户信息推给电销(注册未进件)","ELE_SALE"),
	ELE_RECV_UNSIGN_LOANINFO("电销接口服务-把用户贷款信息推给电销(审批通过未签约)","ELE_SALE"),
	BIGATA_RECOMMEND_LOAN_PRODUCT("大数据续贷产品查询","BIGATA"),
	
	//////////////////还款绑卡
	REPAYMENT_BINDCARD_SENDMSG("调用第三方代扣短信发送","BANK_WITHHOLD"),//中金、宝付
	REPAYMENT_BINDCARD_WITHHOLD("调用第三方代扣绑卡","BANK_WITHHOLD"),
	OTHER_BANK_WITHHOLD("调用第三方代扣扣款","BANK_WITHHOLD"),
	REPAYMENT_BANK_CANCEL_PAY("验密支付取消","BANK_CANCEL_PAY"),
	QUERY_BANK_STUTS("绑卡状态查询","BANK_WITHHOLD"),
	QUERY_ISANTHCODE_STUTS("查询代扣是否需要输入验证码接口","BANK_WITHHOLD"),
	
	QUERY_REPAYDTLS("客户待还款明细查询","QUERY_REPAYDTLS"),
	QUERY_HISTORY_REPAY("查询指定贷款编号的历史还款记录","QUERY_HISTORY_REPAY"),
	
	
	BIGDATA_LOAN_RECALL_UPDATA("召回用户更新","LOAN_RECALL_UPDATA"),
	
	///////////////保险
	QUERY_PROD_INSULIST("获取保险产品列表","INSU_PROD"),
	INSU_RECHECK("保险核保","INSU_RECHECK"),
	INSU_UNDERWRITE("保险承保","INSU_UNDERWRITE"),
	INSU_DETAIL("保险详情接口","INSU_DETAIL"),
	//faceId
	FACEID_GET_TOKEN("调用第三方FaceId获取Token","FACEID"),
	FACEID_CHECK_CERT("调用第三方FaceId校验身份证","FACEID"),
	FACEID_CHECK_BANKCARD("调用第三方FaceId校验银行卡","FACEID"),

	QUERY_CORPORATE_BANKCARD("获取对公银行卡号","CORP_BANKCARD"),
	CORPORATE_INVOKING_POST_LOAN("调用贷后录入对公信息","CORP_BANKCARD"),
	QUERY_CHECK_BILL("调用贷后查询录入信息","CORP_BANKCARD"),

	FACEID_GET_RESULT("调用第三方FaceId获取人脸识别活体结果反查结果","FACEID"),
	
	JIUFU_CONTRACT_QUERY_SIGNED_LIST("玖福接口服务--查询玖福已签约合同列表","JIUFU"),
	ELE_SALE_QUERYPRODINFO("电销接口服务-查询电销产品信息","ELE_SALE"),
	
	INVOK_TELE_SYNC("调用电销同步数据","TELE_SYNC"),
	//玖福协议支付
	JIUFU_CUST_SIGN_APPLY("玖富接口服务--用户签约申请", "JIUFU"),
	JIUFU_CUST_SIGN_CONFIRM("玖富接口服务--用户签约确认", "JIUFU"),
	JIUFU_GENERATE_CUST_NO("玖富接口服务--创建客户编号", "JIUFU"),
	JIUFU_QUERY_SIGN_STATUS("玖富接口服务--查询签约状态", "JIUFU"),
	
	JIUFU_ISNEED_RESET_PWD("玖富接口服务--校验是否需要重置密码", "JIUFU"),
	JIUFU_RESET_PASSWORD("玖富接口服务--重置密码", "JIUFU"),
	JIUFU_CHANGE_AUTHORIZATION("玖富接口服务--授权变更", "JIUFU"),
	JIUFU_LOAN_AGREEMENT("玖富接口服务--借款端借款查询", "JIUFU"),
	JIUFU_LOAN_ORDER_INFO("玖富接口服务--工单查询", "JIUFU"),

	ZHONGAN_GET_VERCODE("众安接口服务--众安获取短信验证码","ZHONGAN"),
	ZHONGAN_BIND_CARD("众安接口服务--众安绑卡","ZHONGAN"),
	ZHONGAN_CHECK_POLICY("众安接口服务--众安核保","ZHONGAN"),
	
	ACTIV_REG_RECORD("邀请好友活动记录","ACTI"),

	CHENGXIN_GET_INSUPROD_LIST("橙心医疗接口服务--获取保险产品列表","CHENGXIN"),
	CHENGXIN_GET_INSUPROD_DETAIL_URL("橙心医疗接口服务--获取保险产品详情url","CHENGXIN"),

	AFTERLOAN_EXTENSION("调用贷后展期代扣接口", "AFTERLOAN"),
	AFTERLOAN_EXTENSION_PERMISSION("调用贷后展期代扣权限查询接口", "AFTERLOAN"),
	
	WKX_TOKEN_APPLY("外快侠TOKEN获取申请","WKX"),
	WKX_TICKET_APPLY("外快侠TICKET获取申请","WKX"),

	MZQ_QUERY_LOTTERY_INFO("调用聚合数据查询彩票开奖信息接口","JUHE"),
	MZQ_QUERY_LOTTERY_HISTORY_INFO("调用聚合数据查询彩票历史开奖信息接口","JUHE"),
	JVHE_JOKEBOOK("调用聚合API笑话集服务接口","JVHEJOKEBOOK"),

	SUO_MI_API("调用网址缩短API接口","SUO"),

	FUND_GETPRODUCTLIST("调用资金渠道服务接口","PRODUCTLIST");
	private  String desc;
	private  String belong;

	private ThirdPartyServiceEnum(String desc,String belong){
		this.setDesc(desc);
		this.setBelong(belong);
	}
	public String getBelong() {
		return belong;
	}
	private void setBelong(String belong) {
		this.belong = belong;
	}
	public String getDesc() {
		return desc;
	}
	private void setDesc(String desc) {
		this.desc = desc;
	}
}
