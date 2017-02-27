package com.redstar.jjd.dao;

import java.util.List;

import com.redstar.jjd.model.UserApply;
import com.redstar.jjd.query.UserApplyQuery;

public interface UserApplyDao extends BaseDao<UserApply, Long> {
    Double getAmountSumByUser(String userId);

    public List<UserApply> getInfos(String start, String end) throws Exception;

    /**
     * 
     * @description 查询无卡用户导出报表（不分页） <br/>
     * 
     * @param userQuery
     * @return List<UserApply>
     * @throws
     */
    public List<UserApply> queryExport(UserApplyQuery userQuery);

    public Object[] getUserLoanById(Long applyId);
}
