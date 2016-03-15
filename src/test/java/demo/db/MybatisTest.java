package demo.db;

import com.ai.common.dynasql.NamedParamQuery;
import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlHelper;
import com.ai.common.dynasql.SqlRepo;
import com.ai.umauthor.server.mapper.UmDictitemMapper;
import com.ai.umauthor.server.mapper.UmOrgMapper;
import com.ai.umauthor.server.model.*;
import com.google.common.collect.ImmutableMap;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * MybatisTest
 *
 * @author mysh
 * @since 2016/1/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Transactional
@Ignore
public class MybatisTest {
	@Autowired
	UmDictitemMapper mapper;

	@Test
	public void crud() {
		UmDictitem rec = new UmDictitem();
		rec.setDictKey("mysh");
		rec.setItemDesc("desc");
		rec.setItemValue("value");
		rec.setItemKey("key");
		assertEquals(1, mapper.insert(rec));

		UmDictitemExample exp = new UmDictitemExample().page(1, 10);
		exp.createCriteria().andDictKeyEqualTo("mysh");
		List<UmDictitem> items = mapper.selectByExample(exp);
		assertEquals(1, items.size());
	}

	@Autowired
	NamedParamQuery jdbcTemplate;

	@Test
	public void dynamicSqlRetTable() throws Exception {
		TableCols.UmOrgCols t = TableCols.UmOrgCols;
		List<UmOrg> r = SqlHelper
						.create(jdbcTemplate, "select * from um_org where 1=1 ")
						.eq(t.orgId, 324)
						.orderBy(t.createTime)
						.page(1, 10) // 分页
						.fetch(UmOrg.class);
		System.out.println(r);
	}

	@Test
	public void dynamicSqlRetMap() throws Exception {
		List<Map<String, Object>> r = SqlHelper
						.create(jdbcTemplate, "select * from um_org  ")
						.page(1, 10)
						.onResult(SqlHelper.dateValue2Str) // 将时间值转成字符串
						.fetch();
		System.out.println(r);
	}

	@Test
	@Ignore
	public void dynamicSqlCache() throws Exception {
		TableCols.UmOrgCols t = TableCols.UmOrgCols;
		while (true) {
			List<Map<String, Object>> r = SqlHelper
							.create(jdbcTemplate, "SELECT * FROM UM_ORG where 1=1")
							.eq(t.orgId, 324)
							.cacheLevel(SqlHelper.CacheLevel.M5) // 结果缓存5分钟
							.fetch();
			System.out.println(r);

			Thread.sleep(5000);
		}
	}

	public static class MultiTable extends UmOrg implements Serializable {
		private static final long serialVersionUID = 6800726618985569194L;
		UmOperator op;
	}

	@Test
	public void dynamicSqlMultiTable() throws Exception {
		List<MultiTable> r = SqlHelper
						.create(jdbcTemplate, "SELECT o.*,op.operator_code FROM UM_ORG o ,UM_OPERATOR op WHERE o.ORG_ID=op.ORG_ID")
						.page(1, 10)
						.fetch(MultiTable.class); // 多表聚合类
		System.out.println(r);
	}

	@Test
	public void dynamicSqlCond() throws Exception {
		TableCols.UmOrgCols t = TableCols.UmOrgCols;
		SqlCondition cond = new SqlCondition()
						.eq(t.orgId, 324);

		List<Map<String, Object>> r = SqlHelper
						.create(jdbcTemplate, "select * from um_org where 1=1")
						.appendCond(cond) // 外部条件
						.page(1, 10)
						.fetch();
		System.out.println(r);
	}

	@Autowired
	SqlRepo sqlRepo;

	@Test
	public void sqlRepoFetchConf() throws Exception {
		TableCols.UmOperatorCols t = TableCols.UmOperatorCols;
		SqlCondition cond = new SqlCondition()
						.isNotNull(t.operatorName)
						.page(1, 10);

		List<Map<String, Object>> r = sqlRepo.fetchByConfig(
						"sqlRepoDemo.query", ImmutableMap.of("id", 1), cond);
		System.out.println(r);

		List<UmOperator> r2 = sqlRepo.fetchByConfig(
						"sqlRepoDemo.query2", ImmutableMap.of("id", 1), cond, UmOperator.class);
		System.out.println(r2);
	}

	@Test
	public void sqlRepoFetchSql() throws Exception {
		TableCols.UmOperatorCols t = TableCols.UmOperatorCols;
		SqlCondition cond = new SqlCondition()
						.isNotNull(t.operatorName)
						.page(1, 10);

		List<Map<String, Object>> r = sqlRepo.fetchBySql(
						"select * from UM_OPERATOR where OPERATOR_ID=:id", ImmutableMap.of("id", 1), cond);
		System.out.println(r);

		List<UmOperator> r2 = sqlRepo.fetchBySql(
						"select * from UM_OPERATOR where OPERATOR_ID=:id", ImmutableMap.of("id", 1),
						cond, UmOperator.class);
		System.out.println(r2);
	}

}
