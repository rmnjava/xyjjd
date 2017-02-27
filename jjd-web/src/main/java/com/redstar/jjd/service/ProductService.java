package com.redstar.jjd.service;

import java.io.OutputStream;
import java.util.List;

import jxl.write.WritableWorkbook;

import com.redstar.jjd.model.BankProduct;
import com.redstar.jjd.model.BankProductReturnMethod;
import com.redstar.jjd.query.BankProductQuery;
import com.redstar.jjd.vo.BankProductReturnMethodView;
import com.redstar.jjd.vo.BankProductView;
import com.redstar.jjd.vo.ProductView;

public interface ProductService {

    public List<BankProduct> query(BankProductQuery query);

    /**
     * 
     * @description 金融机构导出 <br/>
     * 
     * @param out
     * @param list
     * @return WritableWorkbook
     * @throws
     */
    public WritableWorkbook createExcelForBank(OutputStream out,
            List<BankProductView> list);

    /**
     * 
     * @description 查询金融机构导出清单（帅选之后的内容） <br/>
     * 
     * @param query
     * @return List<BankProductView>
     * @throws
     */
    public List<BankProductView> queryBankExport(BankProductQuery query);

    public void save(ProductView view);

    public int delBatch(String ids);

    public BankProduct findBy(Long productId);

    public List<BankProductReturnMethod> getByProductId(Long productId);

    public BankProductReturnMethod saveRate(BankProductReturnMethodView view);

    public int delRate(Long id);

    public BankProductReturnMethod getByRateId(Long rateId);

    public BankProduct getProductByBankId(Long bankId);

}
