package com.redstar.jjd.dao;

import com.redstar.jjd.model.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo, Long> {
    public UserInfo findByUserId(String userId);
}
