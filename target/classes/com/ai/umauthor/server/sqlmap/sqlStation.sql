--! sqlStation.query
-- 查询岗位列表,后续扩展字段

select t.*,st.group_name from UM_STATION t,UM_STATION_GROUP st 
where t.group_id = st.group_id(+)