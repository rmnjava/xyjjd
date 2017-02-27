package com.redstar.jjd.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.UserApplyDao;
import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.query.UserApplyQuery;
import com.redstar.jjd.utils.UtilDatetime;

/**
 * Created by Pengfei on 2015/12/24.
 */
@Repository("userApplyDao")
public class UserApplyDaoImpl extends BaseDaoImpl<UserApply, Long> implements
        UserApplyDao {

    @Override
    public Double getAmountSumByUser(String userId) {
        String hql = "select sum(amount) from UserApply where userId = :userId and applyStatus = 1";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return (Double) query.list().get(0);
    }

    @Override
    public List<UserApply> getInfos(String start, String end) throws Exception {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> queryparam = new HashMap<String, Object>();
        sql.append(" from UserApply a where a.orderNo is null ");
        if (null != start && !"".equals(start)) {
            Timestamp startTime = new Timestamp(UtilDatetime.getStartTimeByDay(
                    UtilDatetime.parseStringToShortDate(start)).getTime());
            queryparam.put("start", startTime);
            sql.append(" and (a.applyDate >= :start)");
        }
        if (null != end && !"".equals(end)) {
            Timestamp endTime = new Timestamp(UtilDatetime.getEndTimeByDay(
                    UtilDatetime.parseStringToShortDate(end)).getTime());
            queryparam.put("end", endTime);
            sql.append(" and (a.applyDate <= :end)");
        }
        sql.append(" order by a.applyDate desc");
        Query query = sessionFactory.getCurrentSession().createQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryparam.entrySet()) {
            query.setParameter(entry.getKey(), queryparam.get(entry.getKey()));
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserApply> queryExport(UserApplyQuery userQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select t from UserApply t where (1=1) and t.orderNo is null and t.dataSource is null ");
        if (null != userQuery.getqStartTime()
                && !"".equals(userQuery.getqStartTime())) {
            queryParams.put("qStartTime", userQuery.getStartTime());
            sql.append(" and (t.applyDate >= :qStartTime) ");
        }
        if (null != userQuery.getqEndTime()
                && !"".equals(userQuery.getqEndTime())) {
            queryParams.put("qEndTime", userQuery.getEndTime());
            sql.append(" and (t.applyDate <= :qEndTime)");
        }
        if (null != userQuery.getStoreId()) {
            queryParams.put("storeId", userQuery.getStoreId());
            sql.append(" and t.storeId = :storeId ");
        }
        sql.append(" order by t.applyDate desc");
        Query query = sessionFactory.getCurrentSession().createQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return (List<UserApply>) query.list();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object[] getUserLoanById(Long applyId) {
        StringBuilder sql = new StringBuilder();
        sql.append("select apply.ID,li.IS_LOAN,li.OPERATOR_TIME as liOperatorTime,li.OPERATOR_ID as liOperatorId,");
        sql.append(" li.NOTE as liNote,it.IS_INTO,it.OPERATOR_TIME as itOperatorTime,it.OPERATOR_ID as itOperatorId,");
        sql.append(" it.NOTE as itNote,le.IS_LOAN_SUCCESS,le.OPERATOR_TIME as leOperatorTime,le.OPERATOR_ID as leOperatorId,");
        sql.append(" le.NOTE as leNote,li.CUSTOMER_TYPE,it.INTO_DATE,le.ARRIVAL_DATE from T_USER_APPLY apply ");
        sql.append(" left join T_LOAN_INTENTION li on apply.ID=li.APPLY_ID ");
        sql.append(" left join T_LOAN_INTO it on apply.ID=it.APPLY_ID ");
        sql.append(" left join T_LOAN_EXAMINE le on apply.ID=le.APPLY_ID ");
        sql.append(" where apply.ID=?");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        query.setLong(0, applyId);
        List list = query.list();
        if (!list.isEmpty()) {
            return (Object[]) query.list().get(0);
        }
        return null;
    }
}
