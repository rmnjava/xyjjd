/**
 * Project Name:jjd-background
 * File Name:UserDao.java
 * Package Name:com.redstar.jjd.dao
 * Date:2015年12月16日下午5:12:44
 * Copyright (c) 2015, China Link Communications LTD All Rights Reserved.
 *
 */

package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.User;

/**
 * ClassName: UserDao <br/>
 * Date: 2015年12月16日 下午5:12:44 <br/>
 * Description: TODO
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface UserDao extends BaseDao<User, Long> {

    public User findByPhone(String phone);

    public User findUser(User user);

    public List<Object[]> getInfos(String start, String end) throws Exception;

    public User findByPhoneAndChannel(String phone, String enterChannel);
}
