--! sqlOrg.query
-- 查询操作员列表,后续扩展字段

SELECT T1.ORG_ID,
       T1.DOMAIN_ID,
       T1.PARENT_ORG_ID,
       T1.ORG_CODE,
       T1.ORG_NAME,
       T1.ORG_STATE,
       T1.REMARKS,
       T1.ORG_TYPE,
       T1.ORG_SHORT,
       T1.ORG_INTRODUCT,
       T1.PROVINCE_CODE,
       T1.CITY_CODE,
       T1.DISTRICT_CODE,
       T1.STREET_CODE,
       T1.VILLAGE_CODE,
       T1.UPDATE_OPERATOR_ID,
       T1.UPDATE_TIME,
       T1.CREATE_OPERATOR_ID,
       T1.CREATE_TIME,
       T1.WEB_URL,
       T1.EMAIL,
       T1.POSTCODE,
       T1.FAX_NO,
       T1.ORG_ADDR,
       T1.CONTACT_TEL,
       T1.CONTACT_NAME,
       T1.LAW_PERSON_TEL,
       T1.LAW_PERSON_NAME,
       T1.ORG_LEVEL,
       T1.ADMIN_LEVEL,
       T1.BUSINESS_TYPE,
       T1.TEL_NO,
       T1.ORDER_ID
  FROM UM_ORG T1
	WHERE 1=1


