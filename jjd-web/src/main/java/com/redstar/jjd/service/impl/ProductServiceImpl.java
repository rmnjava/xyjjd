package com.redstar.jjd.service.impl;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.dao.BankDao;
import com.redstar.jjd.dao.BankProductDao;
import com.redstar.jjd.dao.BankProductReturnMethodDao;
import com.redstar.jjd.dao.StoreProductDao;
import com.redstar.jjd.model.Bank;
import com.redstar.jjd.model.BankProduct;
import com.redstar.jjd.model.BankProductReturnMethod;
import com.redstar.jjd.model.StoreProduct;
import com.redstar.jjd.query.BankProductQuery;
import com.redstar.jjd.service.ProductService;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.BankProductReturnMethodView;
import com.redstar.jjd.vo.BankProductView;
import com.redstar.jjd.vo.ProductView;

@Service("productService")
public class ProductServiceImpl extends AbstractBasicService implements
        ProductService {

    public static final Logger LOGGER = Logger
            .getLogger(ProductServiceImpl.class);

    @Autowired
    private BankProductReturnMethodDao bankProductReturnMethodDao;

    @Autowired
    private BankProductDao bankProductDao;

    @Autowired
    private BankDao bankDao;

    @Autowired
    private StoreProductDao storeProductDao;

    @Override
    public List<BankProduct> query(BankProductQuery query) {
        List<BankProduct> list = bankProductDao.pageQuery(query);
        for (BankProduct product : list) {
            Bank bank = bankDao.get(product.getBankId());
            product.setBankName(bank.getName());
        }
        return list;
    }

    @Override
    public void save(ProductView view) {
        Bank bank = new Bank();
        ConvertUtils.convertObject(view.getBankView(), bank);
        BankProduct product = new BankProduct();
        ConvertUtils.convertObject(view.getBankProductView(), product);
        if (view.getBankView().getId() == null) {
            bank = bankDao.save(bank);
        }
        product.setBankId(bank.getId());
        product.setTypeId(1);
        if (view.getBankProductView().getId() != null) {
            product.setHasBankPay(view.getBankProductView().getHasBankPay());
            bankProductDao.update(product);
        } else {
            product.setHasBankPay(false);
            bankProductDao.save(product);
        }

    }

    @Override
    public int delBatch(String ids) {
        String[] idArr = ids.split(",");
        for (String id : idArr) {
            if (!id.isEmpty()) {
                // 判断该金融机构是否已有商场匹配
                List<StoreProduct> list = storeProductDao.findBy("productId",
                        Long.valueOf(id));
                List<BankProductReturnMethod> returnList = bankProductReturnMethodDao
                        .findBy("productId", Long.valueOf(id));
                if (!list.isEmpty()) {
                    return -1;
                } else if (!returnList.isEmpty()) {
                    return -2;
                } else {
                    BankProduct bankProduct = (BankProduct) bankProductDao
                            .get(Long.valueOf(id));
                    bankProductDao.delete(Long.parseLong(id));
                    bankDao.delete(bankProduct.getBankId());
                }
            }
        }
        return idArr.length;
    }

    @Override
    public BankProduct findBy(Long productId) {
        return bankProductDao.get(productId);
    }

    @Override
    public List<BankProductReturnMethod> getByProductId(Long productId) {
        return bankProductReturnMethodDao.findByProductId(productId);
    }

    @Override
    public BankProductReturnMethod saveRate(BankProductReturnMethodView view) {
        BankProductReturnMethod rate = new BankProductReturnMethod();
        ConvertUtils.convertObject(view, rate);
        if (view.getRateId() != null) {
            rate.setId(view.getRateId());
            return bankProductReturnMethodDao.update(rate);
        } else {
            return bankProductReturnMethodDao.save(rate);
        }
    }

    @Override
    public int delRate(Long id) {
        return bankProductReturnMethodDao.delete(id);
    }

    @Override
    public BankProductReturnMethod getByRateId(Long rateId) {
        return bankProductReturnMethodDao.get(rateId);
    }

    @Override
    public BankProduct getProductByBankId(Long bankId) {
        return bankProductDao.findByBankId(bankId);
    }

    @Override
    public WritableWorkbook createExcelForBank(OutputStream out,
            List<BankProductView> list) {
        String worksheet = "家居贷_金融机构导出表";
        WritableWorkbook workbook = null;
        try {
            workbook = jxl.Workbook.createWorkbook(out);
            WritableSheet sheet = workbook.createSheet(worksheet, 0); // 添加第一个工作表
            // 表头
            String[] headLine = { "银行名称", "贷款最小额度(万元)", "贷款最大额度(万元)", "合作开始时间",
                    "合作结束时间", "产品介绍", "申请条件", "所需材料", "进件资料标准", "授信政策", "还款方式",
                    "期数", "利率", "星易通汇补贴", "商场补贴" };
            for (int i = 0; i < headLine.length; i++) {
                sheet.setColumnView(i, 30);
            }
            // 标题样式
            WritableFont fontTitle = new WritableFont(
                    WritableFont.createFont("宋体"), 15, WritableFont.BOLD);
            WritableCellFormat titleFormat = new WritableCellFormat(fontTitle);
            titleFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            titleFormat
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中
            sheet.mergeCells(0, 0, headLine.length - 1, 0);// (列，行，列，行)第一行合并单元格
            sheet.getSettings().setVerticalFreeze(1);// 设置冻结单元格，下拉时标题固定
            sheet.getSettings().setVerticalFreeze(2);// 设置冻结单元格，下拉时表头固定
            // 添加标题
            String title = "家居贷_金融机构导出表";
            Label labelTitle = new Label(0, 0, title, titleFormat);
            sheet.addCell(labelTitle);
            // 表头样式
            WritableFont fontHeadLine = new WritableFont(
                    WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
            WritableCellFormat headLineFormat = new WritableCellFormat(
                    fontHeadLine);
            headLineFormat.setBorder(Border.TOP, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 上边框
            headLineFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 下边框
            headLineFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 右边框
            headLineFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            headLineFormat
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中
            headLineFormat.setWrap(true);// 是否换行
            headLineFormat.setBackground(jxl.format.Colour.GRAY_25);// 设置背景颜色
            // 单元格内容样式
            WritableFont fontContent = new WritableFont(
                    WritableFont.createFont("宋体"), 10);
            WritableCellFormat contentFormat = new WritableCellFormat(
                    fontContent);
            contentFormat.setBorder(Border.TOP, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 上边框
            contentFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 下边框
            contentFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK); // 右边框
            contentFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格内容水平居中
            contentFormat
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 单元格内容垂直居中
            // 第二行开始添加
            for (int i = 0; i < headLine.length; i++) {
                Label labelHeadLine = new jxl.write.Label(i, 1, headLine[i],
                        headLineFormat);
                sheet.addCell(labelHeadLine);
            }
            jxl.write.Label labelBankName;
            jxl.write.Label labelMinAmount;
            jxl.write.Label labelMaxAmount;
            jxl.write.Label labelStartTime;
            jxl.write.Label labelEndTime;
            jxl.write.Label labelDescription;
            jxl.write.Label labelApplyCondition;
            jxl.write.Label labelRequireMaterial;
            jxl.write.Label labelIncomingStandard;
            jxl.write.Label labelCreditPolicy;
            jxl.write.Label labelPayMethodDesc;
            jxl.write.Label labelPeriod = null;
            jxl.write.Label labelInterest = null;
            jxl.write.Label labelCompanyAllowance = null;
            jxl.write.Label labelStoreAllowance = null;
            int countRow = 2;
            for (BankProductView view : list) {
                int count = 0;
                labelBankName = new Label(count++, countRow,
                        view.getBankName(), contentFormat);
                labelMinAmount = new Label(count++, countRow,
                        String.valueOf(view.getMinAmount()), contentFormat);
                labelMaxAmount = new Label(count++, countRow,
                        String.valueOf(view.getMaxAmount()), contentFormat);
                labelStartTime = new Label(count++, countRow, view.getStart(),
                        contentFormat);
                labelEndTime = new Label(count++, countRow, view.getEnd(),
                        contentFormat);
                labelDescription = new Label(count++, countRow,
                        view.getDescription(), contentFormat);
                labelApplyCondition = new Label(count++, countRow,
                        view.getApplyCondition(), contentFormat);
                labelRequireMaterial = new Label(count++, countRow,
                        view.getRequireMaterial(), contentFormat);
                labelIncomingStandard = new Label(count++, countRow,
                        view.getIncomingStandard(), contentFormat);
                labelCreditPolicy = new Label(count++, countRow,
                        view.getCreditPolicy(), contentFormat);
                labelPayMethodDesc = new Label(count++, countRow,
                        view.getPayMethodDesc(), contentFormat);
                BankProductReturnMethodView methodView = view
                        .getBankProductReturnMethodView();
                if (methodView != null) {
                    labelPeriod = new Label(count++, countRow,
                            String.valueOf(methodView.getPeriod()),
                            contentFormat);
                    labelInterest = new Label(count++, countRow,
                            methodView.getInterestFmt(), contentFormat);
                    labelCompanyAllowance = new Label(count++, countRow,
                            methodView.getCompanyAllowanceFmt(), contentFormat);
                    labelStoreAllowance = new Label(count++, countRow,
                            methodView.getStoreAllowanceFmt(), contentFormat);
                }

                sheet.addCell(labelBankName);
                sheet.addCell(labelMinAmount);
                sheet.addCell(labelMaxAmount);
                sheet.addCell(labelStartTime);
                sheet.addCell(labelEndTime);
                sheet.addCell(labelDescription);
                sheet.addCell(labelApplyCondition);
                sheet.addCell(labelRequireMaterial);
                sheet.addCell(labelIncomingStandard);
                sheet.addCell(labelCreditPolicy);
                sheet.addCell(labelPayMethodDesc);
                sheet.addCell(labelPeriod);
                sheet.addCell(labelInterest);
                sheet.addCell(labelCompanyAllowance);
                sheet.addCell(labelStoreAllowance);
                countRow++;

            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info(e);
        }
        return workbook;
    }

    @Override
    public List<BankProductView> queryBankExport(BankProductQuery productQuery) {
        Map<String, Object> queryParams = new HashMap<String, Object>();
        StringBuilder sql = new StringBuilder();
        sql.append("select b.NAME,p.MIN_AMOUNT,p.MAX_AMOUNT,p.START_TIME,p.END_TIME,p.DESCRIPTION,");
        sql.append("p.APPLY_CONDITION,p.REQUIRE_MATERIAL,p.INCOMINGSTANDARD,p.CREDITPOLICY,p.PAYMETHOD_DESC,");
        sql.append("r.PERIOD,r.INTEREST,r.COMPANYALLOWANCE,r.STOREALLOWANCE ");
        sql.append("from T_BANK_PRODUCT p,T_BANK b , T_BANK_PRODUCT_RETURN_METHOD r ");
        sql.append("where p.BANK_ID = b.ID and p.ID = r.PRODUCT_ID ");
        if (null != productQuery.getQbankId()
                && !new Long(-1L).equals(productQuery.getQbankId())) {
            queryParams.put("bankId", productQuery.getQbankId());
            sql.append(" and b.ID = :bankId ");
        }
        sql.append("order by b.id asc,r.period asc ");
        Query query = sessionFactory.getCurrentSession().createSQLQuery(
                sql.toString());
        for (Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        List<Object[]> objects = query.list();
        List<BankProductView> list = covertToView(objects);
        return list;
    }

    /**
     * 
     * @description object对象转为BankProductView <br/>
     * 
     * @param list
     * @return List<BankProductView>
     * @throws
     */
    private List<BankProductView> covertToView(List<Object[]> list) {
        List<BankProductView> viewList = new ArrayList<BankProductView>();
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        for (Object[] object : list) {
            BankProductView view = new BankProductView();
            if (object[0] != null) {
                view.setBankName(object[0].toString());
            }
            if (object[1] != null) {
                view.setMinAmount(Double.parseDouble(object[1].toString()));
            }
            if (object[2] != null) {
                view.setMaxAmount(Double.parseDouble(object[2].toString()));
            }
            if (object[3] != null) {
                view.setStart(sdf.format(object[3]));
            }
            if (object[4] != null) {
                view.setEnd(sdf.format(object[4]));
            }

            if (object[5] != null) {
                view.setDescription(object[5].toString());
            }
            if (object[6] != null) {
                view.setApplyCondition(object[6].toString());
            }
            if (object[7] != null) {
                view.setRequireMaterial(object[7].toString());
            }

            if (object[8] != null) {
                view.setIncomingStandard(object[8].toString());
            }

            if (object[9] != null) {
                view.setCreditPolicy(object[9].toString());
            }
            if (object[10] != null) {
                view.setPayMethodDesc(object[10].toString());
            }
            // BankProductReturnMethodView
            BankProductReturnMethodView methodView = new BankProductReturnMethodView();
            if (object[11] != null) {
                methodView.setPeriod(Integer.parseInt(object[11].toString()));
            }

            if (object[12] != null) {
                methodView
                        .setInterest(Double.parseDouble(object[12].toString()));
            }

            if (object[13] != null) {
                methodView.setCompanyAllowance(Double.parseDouble(object[13]
                        .toString()));
            }

            if (object[14] != null) {
                methodView.setStoreAllowance(Double.parseDouble(object[14]
                        .toString()));
            }
            view.setBankProductReturnMethodView(methodView);
            viewList.add(view);
        }
        return viewList;

    }
}
