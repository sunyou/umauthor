package com.ai.umauthor.server.model;

import java.util.ArrayList;
import java.util.List;

public class UmStationtypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    protected int limitPageSize = -1;

    public UmStationtypeExample() {
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

    public UmStationtypeExample page(int pageNo, int pageSize) {
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

        public Criteria andStationtypeIdIsNull() {
            addCriterion("STATIONTYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdIsNotNull() {
            addCriterion("STATIONTYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdEqualTo(Long value) {
            addCriterion("STATIONTYPE_ID =", value, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdNotEqualTo(Long value) {
            addCriterion("STATIONTYPE_ID <>", value, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdGreaterThan(Long value) {
            addCriterion("STATIONTYPE_ID >", value, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STATIONTYPE_ID >=", value, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdLessThan(Long value) {
            addCriterion("STATIONTYPE_ID <", value, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdLessThanOrEqualTo(Long value) {
            addCriterion("STATIONTYPE_ID <=", value, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdIn(List<Long> values) {
            addCriterion("STATIONTYPE_ID in", values, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdNotIn(List<Long> values) {
            addCriterion("STATIONTYPE_ID not in", values, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdBetween(Long value1, Long value2) {
            addCriterion("STATIONTYPE_ID between", value1, value2, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andStationtypeIdNotBetween(Long value1, Long value2) {
            addCriterion("STATIONTYPE_ID not between", value1, value2, "stationtypeId");
            return (Criteria) this;
        }

        public Criteria andDomainIdIsNull() {
            addCriterion("DOMAIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andDomainIdIsNotNull() {
            addCriterion("DOMAIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDomainIdEqualTo(Long value) {
            addCriterion("DOMAIN_ID =", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotEqualTo(Long value) {
            addCriterion("DOMAIN_ID <>", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdGreaterThan(Long value) {
            addCriterion("DOMAIN_ID >", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DOMAIN_ID >=", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLessThan(Long value) {
            addCriterion("DOMAIN_ID <", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLessThanOrEqualTo(Long value) {
            addCriterion("DOMAIN_ID <=", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdIn(List<Long> values) {
            addCriterion("DOMAIN_ID in", values, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotIn(List<Long> values) {
            addCriterion("DOMAIN_ID not in", values, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdBetween(Long value1, Long value2) {
            addCriterion("DOMAIN_ID between", value1, value2, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotBetween(Long value1, Long value2) {
            addCriterion("DOMAIN_ID not between", value1, value2, "domainId");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameIsNull() {
            addCriterion("STATIONTYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameIsNotNull() {
            addCriterion("STATIONTYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameEqualTo(String value) {
            addCriterion("STATIONTYPE_NAME =", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameNotEqualTo(String value) {
            addCriterion("STATIONTYPE_NAME <>", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameGreaterThan(String value) {
            addCriterion("STATIONTYPE_NAME >", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATIONTYPE_NAME >=", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameLessThan(String value) {
            addCriterion("STATIONTYPE_NAME <", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameLessThanOrEqualTo(String value) {
            addCriterion("STATIONTYPE_NAME <=", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameLike(String value) {
            addCriterion("STATIONTYPE_NAME like", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameNotLike(String value) {
            addCriterion("STATIONTYPE_NAME not like", value, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameIn(List<String> values) {
            addCriterion("STATIONTYPE_NAME in", values, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameNotIn(List<String> values) {
            addCriterion("STATIONTYPE_NAME not in", values, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameBetween(String value1, String value2) {
            addCriterion("STATIONTYPE_NAME between", value1, value2, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeNameNotBetween(String value1, String value2) {
            addCriterion("STATIONTYPE_NAME not between", value1, value2, "stationtypeName");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeIsNull() {
            addCriterion("STATIONTYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeIsNotNull() {
            addCriterion("STATIONTYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeEqualTo(String value) {
            addCriterion("STATIONTYPE_CODE =", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeNotEqualTo(String value) {
            addCriterion("STATIONTYPE_CODE <>", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeGreaterThan(String value) {
            addCriterion("STATIONTYPE_CODE >", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STATIONTYPE_CODE >=", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeLessThan(String value) {
            addCriterion("STATIONTYPE_CODE <", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeLessThanOrEqualTo(String value) {
            addCriterion("STATIONTYPE_CODE <=", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeLike(String value) {
            addCriterion("STATIONTYPE_CODE like", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeNotLike(String value) {
            addCriterion("STATIONTYPE_CODE not like", value, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeIn(List<String> values) {
            addCriterion("STATIONTYPE_CODE in", values, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeNotIn(List<String> values) {
            addCriterion("STATIONTYPE_CODE not in", values, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeBetween(String value1, String value2) {
            addCriterion("STATIONTYPE_CODE between", value1, value2, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeCodeNotBetween(String value1, String value2) {
            addCriterion("STATIONTYPE_CODE not between", value1, value2, "stationtypeCode");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateIsNull() {
            addCriterion("STATIONTYPE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateIsNotNull() {
            addCriterion("STATIONTYPE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateEqualTo(Short value) {
            addCriterion("STATIONTYPE_STATE =", value, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateNotEqualTo(Short value) {
            addCriterion("STATIONTYPE_STATE <>", value, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateGreaterThan(Short value) {
            addCriterion("STATIONTYPE_STATE >", value, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateGreaterThanOrEqualTo(Short value) {
            addCriterion("STATIONTYPE_STATE >=", value, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateLessThan(Short value) {
            addCriterion("STATIONTYPE_STATE <", value, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateLessThanOrEqualTo(Short value) {
            addCriterion("STATIONTYPE_STATE <=", value, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateIn(List<Short> values) {
            addCriterion("STATIONTYPE_STATE in", values, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateNotIn(List<Short> values) {
            addCriterion("STATIONTYPE_STATE not in", values, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateBetween(Short value1, Short value2) {
            addCriterion("STATIONTYPE_STATE between", value1, value2, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andStationtypeStateNotBetween(Short value1, Short value2) {
            addCriterion("STATIONTYPE_STATE not between", value1, value2, "stationtypeState");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
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