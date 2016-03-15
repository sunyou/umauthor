package com.ai.umauthor.server.model;

public abstract class TableCols {
	public static class UmMenuCols {
		public final String menuId = "MENU_ID";
		public final String domainId = "DOMAIN_ID";
		public final String systemId = "SYSTEM_ID";
		public final String menuName = "MENU_NAME";
		public final String menuUrl = "MENU_URL";
		public final String menuOrder = "MENU_ORDER";
		public final String menuType = "MENU_TYPE";
		public final String menuDesc = "MENU_DESC";
		public final String parentMenuId = "PARENT_MENU_ID";
		public final String createDate = "CREATE_DATE";
		public final String menuState = "MENU_STATE";
		public final String menuImage = "MENU_IMAGE";
		public final String menuCode = "MENU_CODE";
	}
	public static final UmMenuCols UmMenuCols = new UmMenuCols();

	public static class UmStationCols {
		public final String stationId = "STATION_ID";
		public final String domainId = "DOMAIN_ID";
		public final String stationName = "STATION_NAME";
		public final String stationDesc = "STATION_DESC";
		public final String createDate = "CREATE_DATE";
		public final String createOperatorId = "CREATE_OPERATOR_ID";
		public final String updateDate = "UPDATE_DATE";
		public final String updateOperatorId = "UPDATE_OPERATOR_ID";
		public final String stationState = "STATION_STATE";
		public final String groupId = "GROUP_ID";
		public final String orgId = "ORG_ID";
	}
	public static final UmStationCols UmStationCols = new UmStationCols();

	public static class UmDomainCols {
		public final String domainId = "DOMAIN_ID";
		public final String domainName = "DOMAIN_NAME";
		public final String domainState = "DOMAIN_STATE";
		public final String remarks = "REMARKS";
	}
	public static final UmDomainCols UmDomainCols = new UmDomainCols();

	public static class UmOperateLogCols {
		public final String logId = "LOG_ID";
		public final String domainId = "DOMAIN_ID";
		public final String operatorCode = "OPERATOR_CODE";
		public final String operatorDate = "OPERATOR_DATE";
		public final String operatorType = "OPERATOR_TYPE";
		public final String operatorDesc = "OPERATOR_DESC";
	}
	public static final UmOperateLogCols UmOperateLogCols = new UmOperateLogCols();

	public static class UmOrgCols {
		public final String orgId = "ORG_ID";
		public final String domainId = "DOMAIN_ID";
		public final String parentOrgId = "PARENT_ORG_ID";
		public final String orgCode = "ORG_CODE";
		public final String orgName = "ORG_NAME";
		public final String orgState = "ORG_STATE";
		public final String remarks = "REMARKS";
		public final String orgType = "ORG_TYPE";
		public final String orgShort = "ORG_SHORT";
		public final String orgIntroduct = "ORG_INTRODUCT";
		public final String provinceCode = "PROVINCE_CODE";
		public final String cityCode = "CITY_CODE";
		public final String districtCode = "DISTRICT_CODE";
		public final String streetCode = "STREET_CODE";
		public final String villageCode = "VILLAGE_CODE";
		public final String updateOperatorId = "UPDATE_OPERATOR_ID";
		public final String updateTime = "UPDATE_TIME";
		public final String createOperatorId = "CREATE_OPERATOR_ID";
		public final String createTime = "CREATE_TIME";
		public final String webUrl = "WEB_URL";
		public final String email = "EMAIL";
		public final String postcode = "POSTCODE";
		public final String faxNo = "FAX_NO";
		public final String orgAddr = "ORG_ADDR";
		public final String contactTel = "CONTACT_TEL";
		public final String contactName = "CONTACT_NAME";
		public final String lawPersonTel = "LAW_PERSON_TEL";
		public final String lawPersonName = "LAW_PERSON_NAME";
		public final String orgLevel = "ORG_LEVEL";
		public final String adminLevel = "ADMIN_LEVEL";
		public final String businessType = "BUSINESS_TYPE";
		public final String telNo = "TEL_NO";
		public final String orderId = "ORDER_ID";
	}
	public static final UmOrgCols UmOrgCols = new UmOrgCols();

	public static class UmStation2menuCols {
		public final String id = "ID";
		public final String stationId = "STATION_ID";
		public final String menuId = "MENU_ID";
		public final String remarks = "REMARKS";
	}
	public static final UmStation2menuCols UmStation2menuCols = new UmStation2menuCols();

	public static class UmStationGroupCols {
		public final String groupId = "GROUP_ID";
		public final String groupName = "GROUP_NAME";
		public final String createDate = "CREATE_DATE";
		public final String createOperatorId = "CREATE_OPERATOR_ID";
		public final String remarks = "REMARKS";
		public final String domainId = "DOMAIN_ID";
	}
	public static final UmStationGroupCols UmStationGroupCols = new UmStationGroupCols();

	public static class UmOperator2menuCols {
		public final String id = "ID";
		public final String operatorId = "OPERATOR_ID";
		public final String menuId = "MENU_ID";
		public final String remarks = "REMARKS";
	}
	public static final UmOperator2menuCols UmOperator2menuCols = new UmOperator2menuCols();

	public static class UmStation2stationtypeCols {
		public final String relationId = "RELATION_ID";
		public final String stationId = "STATION_ID";
		public final String stationtypeId = "STATIONTYPE_ID";
		public final String remarks = "REMARKS";
	}
	public static final UmStation2stationtypeCols UmStation2stationtypeCols = new UmStation2stationtypeCols();

	public static class UmOperator2stationCols {
		public final String id = "ID";
		public final String operatorId = "OPERATOR_ID";
		public final String orgId = "ORG_ID";
		public final String stationId = "STATION_ID";
		public final String remarks = "REMARKS";
		public final String authorOperatorId = "AUTHOR_OPERATOR_ID";
		public final String authorDate = "AUTHOR_DATE";
		public final String allowReauthor = "ALLOW_REAUTHOR";
	}
	public static final UmOperator2stationCols UmOperator2stationCols = new UmOperator2stationCols();

	public static class UmDictitemKeyCols {
		public final String dictKey = "DICT_KEY";
		public final String itemKey = "ITEM_KEY";
	}
	public static final UmDictitemKeyCols UmDictitemKeyCols = new UmDictitemKeyCols();

	public static class UmOperatorCols {
		public final String operatorId = "OPERATOR_ID";
		public final String domainId = "DOMAIN_ID";
		public final String operatorCode = "OPERATOR_CODE";
		public final String operatorName = "OPERATOR_NAME";
		public final String operatorPsw = "OPERATOR_PSW";
		public final String orgId = "ORG_ID";
		public final String operatorState = "OPERATOR_STATE";
		public final String changePswDate = "CHANGE_PSW_DATE";
		public final String createDate = "CREATE_DATE";
		public final String remarks = "REMARKS";
		public final String certNo = "CERT_NO";
		public final String sexCode = "SEX_CODE";
		public final String birthday = "BIRTHDAY";
		public final String email = "EMAIL";
		public final String telNo = "TEL_NO";
		public final String addressDetail = "ADDRESS_DETAIL";
		public final String positionCode = "POSITION_CODE";
	}
	public static final UmOperatorCols UmOperatorCols = new UmOperatorCols();

	public static class UmStationtypeCols {
		public final String stationtypeId = "STATIONTYPE_ID";
		public final String domainId = "DOMAIN_ID";
		public final String stationtypeName = "STATIONTYPE_NAME";
		public final String stationtypeCode = "STATIONTYPE_CODE";
		public final String stationtypeState = "STATIONTYPE_STATE";
		public final String remarks = "REMARKS";
	}
	public static final UmStationtypeCols UmStationtypeCols = new UmStationtypeCols();

	public static class UmDictitemCols {
		public final String itemValue = "ITEM_VALUE";
		public final String parentItemKey = "PARENT_ITEM_KEY";
		public final String itemOrder = "ITEM_ORDER";
		public final String itemState = "ITEM_STATE";
		public final String itemDesc = "ITEM_DESC";
		public final String itemExtValue1 = "ITEM_EXT_VALUE1";
		public final String itemExtValue2 = "ITEM_EXT_VALUE2";
	}
	public static final UmDictitemCols UmDictitemCols = new UmDictitemCols();

	public static class UmAreasCols {
		public final String areaId = "AREA_ID";
		public final String areaName = "AREA_NAME";
		public final String pyshort = "PYSHORT";
		public final String provice = "PROVICE";
		public final String region = "REGION";
		public final String county = "COUNTY";
		public final String town = "TOWN";
		public final String parentid = "PARENTID";
		public final String areaFullname = "AREA_FULLNAME";
		public final String timestamp = "TIMESTAMP";
		public final String createUser = "CREATE_USER";
		public final String createTime = "CREATE_TIME";
		public final String modifiedUser = "MODIFIED_USER";
		public final String modifiedTime = "MODIFIED_TIME";
		public final String leafFlag = "LEAF_FLAG";
		public final String treelevel = "TREELEVEL";
		public final String latitude = "LATITUDE";
		public final String longitude = "LONGITUDE";
		public final String remark = "REMARK";
	}
	public static final UmAreasCols UmAreasCols = new UmAreasCols();

}
