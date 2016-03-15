--! sqlOperators.query
-- 查询操作员列表,后续扩展字段

SELECT T1.OPERATOR_ID,
       T1.DOMAIN_ID,
       T1.OPERATOR_CODE,
       T1.OPERATOR_NAME,
       T1.OPERATOR_PSW,
       T1.ORG_ID,
       (SELECT ORG_NAME FROM UM_ORG O WHERE O.ORG_ID = T1.ORG_ID) AS ORG_NAME,
       T1.OPERATOR_STATE,
       T1.CHANGE_PSW_DATE,
       T1.CREATE_DATE,
       T1.REMARKS,
       T1.CERT_NO,
       T1.SEX_CODE,
       T1.BIRTHDAY,
       T1.EMAIL,
       T1.TEL_NO,
       T1.ADDRESS_DETAIL,
       T1.POSITION_CODE
  FROM UM_OPERATOR T1
   WHERE 1=1

--! sqlOperators.queryRelaStation
-- 查询操作员关联岗位,后续扩展字段
select t1.*,
       (select operator_name from um_operator where operator_id = t1.operator_id) operator_name,
       (select operator_name from um_operator where operator_id = t1.author_operator_id) author_operator_name,
       t2.station_name,
       t3.org_name
  from um_operator2station t1, um_station t2, um_org t3
 where t1.station_id = t2.station_id(+)
   and t1.org_id = t3.org_id(+)
   and t2.station_state = 1
   --and t3.org_state = 1