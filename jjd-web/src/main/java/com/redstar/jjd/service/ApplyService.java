package com.redstar.jjd.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.query.UserApplyQuery;
import com.redstar.jjd.vo.UserApplyView;

/**
 * Created by Pengfei on 2015/12/25.
 */
public interface ApplyService {

    Double getSuccessAmount(String userId);

    public List<UserApplyView> query(UserApplyQuery query);

    /**
     * 
     * @description 查询无卡申请客户信息导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<UserApplyView>
     * @throws
     */
    public List<UserApplyView> queryExport(UserApplyQuery query);

    /**
     * 
     * @description 无卡申请客户导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForNoCard(OutputStream out,
            List<UserApplyView> list);
}
