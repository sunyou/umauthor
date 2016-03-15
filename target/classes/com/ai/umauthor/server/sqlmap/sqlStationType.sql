--! sqlStationType.query
-- 查询操作员列表,后续扩展字段

SELECT P.*, D.DOMAIN_NAME
  FROM UM_STATIONTYPE P, UM_DOMAIN D
 WHERE P.DOMAIN_ID = D.DOMAIN_ID