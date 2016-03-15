package com.ai.umauthor.server.model;

import java.util.ArrayList;
import java.util.List;

public class UmDictitemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    protected int limitPageSize = -1;

    public UmDictitemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    public void setLimitPageSize(int limitPageSize) {
        this.limitPageSize=limitPageSize;
    }

    public int getLimitPageSize() {
        return limitPageSize;
    }

    public UmDictitemExample page(int pageNo, int pageSize) {
        this.limitStart= (pageNo-1) * pageSize;
        this.limitEnd = pageNo * pageSize;
        this.limitPageSize = pageSize;
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDictKeyIsNull() {
            addCriterion("DICT_KEY is null");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNotNull() {
            addCriterion("DICT_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andDictKeyEqualTo(String value) {
            addCriterion("DICT_KEY =", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotEqualTo(String value) {
            addCriterion("DICT_KEY <>", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThan(String value) {
            addCriterion("DICT_KEY >", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_KEY >=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThan(String value) {
            addCriterion("DICT_KEY <", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThanOrEqualTo(String value) {
            addCriterion("DICT_KEY <=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLike(String value) {
            addCriterion("DICT_KEY like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotLike(String value) {
            addCriterion("DICT_KEY not like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyIn(List<String> values) {
            addCriterion("DICT_KEY in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotIn(List<String> values) {
            addCriterion("DICT_KEY not in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyBetween(String value1, String value2) {
            addCriterion("DICT_KEY between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotBetween(String value1, String value2) {
            addCriterion("DICT_KEY not between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyIsNull() {
            addCriterion("ITEM_KEY is null");
            return (Criteria) this;
        }

        public Criteria andItemKeyIsNotNull() {
            addCriterion("ITEM_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andItemKeyEqualTo(String value) {
            addCriterion("ITEM_KEY =", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyNotEqualTo(String value) {
            addCriterion("ITEM_KEY <>", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyGreaterThan(String value) {
            addCriterion("ITEM_KEY >", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_KEY >=", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyLessThan(String value) {
            addCriterion("ITEM_KEY <", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyLessThanOrEqualTo(String value) {
            addCriterion("ITEM_KEY <=", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyLike(String value) {
            addCriterion("ITEM_KEY like", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyNotLike(String value) {
            addCriterion("ITEM_KEY not like", value, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyIn(List<String> values) {
            addCriterion("ITEM_KEY in", values, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyNotIn(List<String> values) {
            addCriterion("ITEM_KEY not in", values, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyBetween(String value1, String value2) {
            addCriterion("ITEM_KEY between", value1, value2, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemKeyNotBetween(String value1, String value2) {
            addCriterion("ITEM_KEY not between", value1, value2, "itemKey");
            return (Criteria) this;
        }

        public Criteria andItemValueIsNull() {
            addCriterion("ITEM_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andItemValueIsNotNull() {
            addCriterion("ITEM_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andItemValueEqualTo(String value) {
            addCriterion("ITEM_VALUE =", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotEqualTo(String value) {
            addCriterion("ITEM_VALUE <>", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueGreaterThan(String value) {
            addCriterion("ITEM_VALUE >", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_VALUE >=", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueLessThan(String value) {
            addCriterion("ITEM_VALUE <", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueLessThanOrEqualTo(String value) {
            addCriterion("ITEM_VALUE <=", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueLike(String value) {
            addCriterion("ITEM_VALUE like", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotLike(String value) {
            addCriterion("ITEM_VALUE not like", value, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueIn(List<String> values) {
            addCriterion("ITEM_VALUE in", values, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotIn(List<String> values) {
            addCriterion("ITEM_VALUE not in", values, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueBetween(String value1, String value2) {
            addCriterion("ITEM_VALUE between", value1, value2, "itemValue");
            return (Criteria) this;
        }

        public Criteria andItemValueNotBetween(String value1, String value2) {
            addCriterion("ITEM_VALUE not between", value1, value2, "itemValue");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyIsNull() {
            addCriterion("PARENT_ITEM_KEY is null");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyIsNotNull() {
            addCriterion("PARENT_ITEM_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyEqualTo(String value) {
            addCriterion("PARENT_ITEM_KEY =", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyNotEqualTo(String value) {
            addCriterion("PARENT_ITEM_KEY <>", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyGreaterThan(String value) {
            addCriterion("PARENT_ITEM_KEY >", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ITEM_KEY >=", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyLessThan(String value) {
            addCriterion("PARENT_ITEM_KEY <", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ITEM_KEY <=", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyLike(String value) {
            addCriterion("PARENT_ITEM_KEY like", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyNotLike(String value) {
            addCriterion("PARENT_ITEM_KEY not like", value, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyIn(List<String> values) {
            addCriterion("PARENT_ITEM_KEY in", values, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyNotIn(List<String> values) {
            addCriterion("PARENT_ITEM_KEY not in", values, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyBetween(String value1, String value2) {
            addCriterion("PARENT_ITEM_KEY between", value1, value2, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andParentItemKeyNotBetween(String value1, String value2) {
            addCriterion("PARENT_ITEM_KEY not between", value1, value2, "parentItemKey");
            return (Criteria) this;
        }

        public Criteria andItemOrderIsNull() {
            addCriterion("ITEM_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andItemOrderIsNotNull() {
            addCriterion("ITEM_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andItemOrderEqualTo(Integer value) {
            addCriterion("ITEM_ORDER =", value, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderNotEqualTo(Integer value) {
            addCriterion("ITEM_ORDER <>", value, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderGreaterThan(Integer value) {
            addCriterion("ITEM_ORDER >", value, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("ITEM_ORDER >=", value, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderLessThan(Integer value) {
            addCriterion("ITEM_ORDER <", value, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderLessThanOrEqualTo(Integer value) {
            addCriterion("ITEM_ORDER <=", value, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderIn(List<Integer> values) {
            addCriterion("ITEM_ORDER in", values, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderNotIn(List<Integer> values) {
            addCriterion("ITEM_ORDER not in", values, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_ORDER between", value1, value2, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("ITEM_ORDER not between", value1, value2, "itemOrder");
            return (Criteria) this;
        }

        public Criteria andItemStateIsNull() {
            addCriterion("ITEM_STATE is null");
            return (Criteria) this;
        }

        public Criteria andItemStateIsNotNull() {
            addCriterion("ITEM_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andItemStateEqualTo(String value) {
            addCriterion("ITEM_STATE =", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotEqualTo(String value) {
            addCriterion("ITEM_STATE <>", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateGreaterThan(String value) {
            addCriterion("ITEM_STATE >", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_STATE >=", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateLessThan(String value) {
            addCriterion("ITEM_STATE <", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateLessThanOrEqualTo(String value) {
            addCriterion("ITEM_STATE <=", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateLike(String value) {
            addCriterion("ITEM_STATE like", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotLike(String value) {
            addCriterion("ITEM_STATE not like", value, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateIn(List<String> values) {
            addCriterion("ITEM_STATE in", values, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotIn(List<String> values) {
            addCriterion("ITEM_STATE not in", values, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateBetween(String value1, String value2) {
            addCriterion("ITEM_STATE between", value1, value2, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemStateNotBetween(String value1, String value2) {
            addCriterion("ITEM_STATE not between", value1, value2, "itemState");
            return (Criteria) this;
        }

        public Criteria andItemDescIsNull() {
            addCriterion("ITEM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andItemDescIsNotNull() {
            addCriterion("ITEM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andItemDescEqualTo(String value) {
            addCriterion("ITEM_DESC =", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotEqualTo(String value) {
            addCriterion("ITEM_DESC <>", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescGreaterThan(String value) {
            addCriterion("ITEM_DESC >", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_DESC >=", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLessThan(String value) {
            addCriterion("ITEM_DESC <", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLessThanOrEqualTo(String value) {
            addCriterion("ITEM_DESC <=", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescLike(String value) {
            addCriterion("ITEM_DESC like", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotLike(String value) {
            addCriterion("ITEM_DESC not like", value, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescIn(List<String> values) {
            addCriterion("ITEM_DESC in", values, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotIn(List<String> values) {
            addCriterion("ITEM_DESC not in", values, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescBetween(String value1, String value2) {
            addCriterion("ITEM_DESC between", value1, value2, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemDescNotBetween(String value1, String value2) {
            addCriterion("ITEM_DESC not between", value1, value2, "itemDesc");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1IsNull() {
            addCriterion("ITEM_EXT_VALUE1 is null");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1IsNotNull() {
            addCriterion("ITEM_EXT_VALUE1 is not null");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1EqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE1 =", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1NotEqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE1 <>", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1GreaterThan(String value) {
            addCriterion("ITEM_EXT_VALUE1 >", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1GreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE1 >=", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1LessThan(String value) {
            addCriterion("ITEM_EXT_VALUE1 <", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1LessThanOrEqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE1 <=", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1Like(String value) {
            addCriterion("ITEM_EXT_VALUE1 like", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1NotLike(String value) {
            addCriterion("ITEM_EXT_VALUE1 not like", value, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1In(List<String> values) {
            addCriterion("ITEM_EXT_VALUE1 in", values, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1NotIn(List<String> values) {
            addCriterion("ITEM_EXT_VALUE1 not in", values, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1Between(String value1, String value2) {
            addCriterion("ITEM_EXT_VALUE1 between", value1, value2, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue1NotBetween(String value1, String value2) {
            addCriterion("ITEM_EXT_VALUE1 not between", value1, value2, "itemExtValue1");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2IsNull() {
            addCriterion("ITEM_EXT_VALUE2 is null");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2IsNotNull() {
            addCriterion("ITEM_EXT_VALUE2 is not null");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2EqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE2 =", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2NotEqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE2 <>", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2GreaterThan(String value) {
            addCriterion("ITEM_EXT_VALUE2 >", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2GreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE2 >=", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2LessThan(String value) {
            addCriterion("ITEM_EXT_VALUE2 <", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2LessThanOrEqualTo(String value) {
            addCriterion("ITEM_EXT_VALUE2 <=", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2Like(String value) {
            addCriterion("ITEM_EXT_VALUE2 like", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2NotLike(String value) {
            addCriterion("ITEM_EXT_VALUE2 not like", value, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2In(List<String> values) {
            addCriterion("ITEM_EXT_VALUE2 in", values, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2NotIn(List<String> values) {
            addCriterion("ITEM_EXT_VALUE2 not in", values, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2Between(String value1, String value2) {
            addCriterion("ITEM_EXT_VALUE2 between", value1, value2, "itemExtValue2");
            return (Criteria) this;
        }

        public Criteria andItemExtValue2NotBetween(String value1, String value2) {
            addCriterion("ITEM_EXT_VALUE2 not between", value1, value2, "itemExtValue2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}