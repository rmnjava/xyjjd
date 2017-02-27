package com.redstar.jjd.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.UserInfoDao;
import com.redstar.jjd.model.UserInfo;

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo, Long> implements
        UserInfoDao {

    @Override
    public UserInfo findByUserId(String userId) {
        String hql = "from UserInfo where userId = :userId order by id desc";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        return (UserInfo) query.list().get(0);
    }
}
