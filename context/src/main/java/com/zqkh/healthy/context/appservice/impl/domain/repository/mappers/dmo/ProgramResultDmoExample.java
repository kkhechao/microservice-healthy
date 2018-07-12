package com.zqkh.healthy.context.appservice.impl.domain.repository.mappers.dmo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramResultDmoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramResultDmoExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdIsNull() {
            addCriterion("familymember_id is null");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdIsNotNull() {
            addCriterion("familymember_id is not null");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdEqualTo(String value) {
            addCriterion("familymember_id =", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdNotEqualTo(String value) {
            addCriterion("familymember_id <>", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdGreaterThan(String value) {
            addCriterion("familymember_id >", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("familymember_id >=", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdLessThan(String value) {
            addCriterion("familymember_id <", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdLessThanOrEqualTo(String value) {
            addCriterion("familymember_id <=", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdLike(String value) {
            addCriterion("familymember_id like", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdNotLike(String value) {
            addCriterion("familymember_id not like", value, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdIn(List<String> values) {
            addCriterion("familymember_id in", values, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdNotIn(List<String> values) {
            addCriterion("familymember_id not in", values, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdBetween(String value1, String value2) {
            addCriterion("familymember_id between", value1, value2, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andFamilymemberIdNotBetween(String value1, String value2) {
            addCriterion("familymember_id not between", value1, value2, "familymemberId");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNull() {
            addCriterion("program_id is null");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNotNull() {
            addCriterion("program_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgramIdEqualTo(String value) {
            addCriterion("program_id =", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotEqualTo(String value) {
            addCriterion("program_id <>", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThan(String value) {
            addCriterion("program_id >", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThanOrEqualTo(String value) {
            addCriterion("program_id >=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThan(String value) {
            addCriterion("program_id <", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThanOrEqualTo(String value) {
            addCriterion("program_id <=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLike(String value) {
            addCriterion("program_id like", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotLike(String value) {
            addCriterion("program_id not like", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdIn(List<String> values) {
            addCriterion("program_id in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotIn(List<String> values) {
            addCriterion("program_id not in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdBetween(String value1, String value2) {
            addCriterion("program_id between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotBetween(String value1, String value2) {
            addCriterion("program_id not between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andInsistDayIsNull() {
            addCriterion("insist_day is null");
            return (Criteria) this;
        }

        public Criteria andInsistDayIsNotNull() {
            addCriterion("insist_day is not null");
            return (Criteria) this;
        }

        public Criteria andInsistDayEqualTo(Integer value) {
            addCriterion("insist_day =", value, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayNotEqualTo(Integer value) {
            addCriterion("insist_day <>", value, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayGreaterThan(Integer value) {
            addCriterion("insist_day >", value, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("insist_day >=", value, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayLessThan(Integer value) {
            addCriterion("insist_day <", value, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayLessThanOrEqualTo(Integer value) {
            addCriterion("insist_day <=", value, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayIn(List<Integer> values) {
            addCriterion("insist_day in", values, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayNotIn(List<Integer> values) {
            addCriterion("insist_day not in", values, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayBetween(Integer value1, Integer value2) {
            addCriterion("insist_day between", value1, value2, "insistDay");
            return (Criteria) this;
        }

        public Criteria andInsistDayNotBetween(Integer value1, Integer value2) {
            addCriterion("insist_day not between", value1, value2, "insistDay");
            return (Criteria) this;
        }

        public Criteria andWanderNumIsNull() {
            addCriterion("wander_num is null");
            return (Criteria) this;
        }

        public Criteria andWanderNumIsNotNull() {
            addCriterion("wander_num is not null");
            return (Criteria) this;
        }

        public Criteria andWanderNumEqualTo(Integer value) {
            addCriterion("wander_num =", value, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumNotEqualTo(Integer value) {
            addCriterion("wander_num <>", value, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumGreaterThan(Integer value) {
            addCriterion("wander_num >", value, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("wander_num >=", value, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumLessThan(Integer value) {
            addCriterion("wander_num <", value, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumLessThanOrEqualTo(Integer value) {
            addCriterion("wander_num <=", value, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumIn(List<Integer> values) {
            addCriterion("wander_num in", values, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumNotIn(List<Integer> values) {
            addCriterion("wander_num not in", values, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumBetween(Integer value1, Integer value2) {
            addCriterion("wander_num between", value1, value2, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andWanderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("wander_num not between", value1, value2, "wanderNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumIsNull() {
            addCriterion("give_up_num is null");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumIsNotNull() {
            addCriterion("give_up_num is not null");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumEqualTo(Integer value) {
            addCriterion("give_up_num =", value, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumNotEqualTo(Integer value) {
            addCriterion("give_up_num <>", value, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumGreaterThan(Integer value) {
            addCriterion("give_up_num >", value, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("give_up_num >=", value, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumLessThan(Integer value) {
            addCriterion("give_up_num <", value, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumLessThanOrEqualTo(Integer value) {
            addCriterion("give_up_num <=", value, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumIn(List<Integer> values) {
            addCriterion("give_up_num in", values, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumNotIn(List<Integer> values) {
            addCriterion("give_up_num not in", values, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumBetween(Integer value1, Integer value2) {
            addCriterion("give_up_num between", value1, value2, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andGiveUpNumNotBetween(Integer value1, Integer value2) {
            addCriterion("give_up_num not between", value1, value2, "giveUpNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumIsNull() {
            addCriterion("finish_num is null");
            return (Criteria) this;
        }

        public Criteria andFinishNumIsNotNull() {
            addCriterion("finish_num is not null");
            return (Criteria) this;
        }

        public Criteria andFinishNumEqualTo(Integer value) {
            addCriterion("finish_num =", value, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumNotEqualTo(Integer value) {
            addCriterion("finish_num <>", value, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumGreaterThan(Integer value) {
            addCriterion("finish_num >", value, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("finish_num >=", value, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumLessThan(Integer value) {
            addCriterion("finish_num <", value, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumLessThanOrEqualTo(Integer value) {
            addCriterion("finish_num <=", value, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumIn(List<Integer> values) {
            addCriterion("finish_num in", values, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumNotIn(List<Integer> values) {
            addCriterion("finish_num not in", values, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumBetween(Integer value1, Integer value2) {
            addCriterion("finish_num between", value1, value2, "finishNum");
            return (Criteria) this;
        }

        public Criteria andFinishNumNotBetween(Integer value1, Integer value2) {
            addCriterion("finish_num not between", value1, value2, "finishNum");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTranscendIsNull() {
            addCriterion("transcend is null");
            return (Criteria) this;
        }

        public Criteria andTranscendIsNotNull() {
            addCriterion("transcend is not null");
            return (Criteria) this;
        }

        public Criteria andTranscendEqualTo(Integer value) {
            addCriterion("transcend =", value, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendNotEqualTo(Integer value) {
            addCriterion("transcend <>", value, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendGreaterThan(Integer value) {
            addCriterion("transcend >", value, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendGreaterThanOrEqualTo(Integer value) {
            addCriterion("transcend >=", value, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendLessThan(Integer value) {
            addCriterion("transcend <", value, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendLessThanOrEqualTo(Integer value) {
            addCriterion("transcend <=", value, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendIn(List<Integer> values) {
            addCriterion("transcend in", values, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendNotIn(List<Integer> values) {
            addCriterion("transcend not in", values, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendBetween(Integer value1, Integer value2) {
            addCriterion("transcend between", value1, value2, "transcend");
            return (Criteria) this;
        }

        public Criteria andTranscendNotBetween(Integer value1, Integer value2) {
            addCriterion("transcend not between", value1, value2, "transcend");
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