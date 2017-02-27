package com.redstar.jjd.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.redstar.jjd.dao.BaseDao;
import com.redstar.jjd.model.Menu;

/**
 * 
 * ClassName: MenuDao <br/>
 * Date: 2016年6月23日 下午5:06:37 <br/>
 * Description: 菜单dao <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
@Repository("menuDao")
public interface MenuDao extends BaseDao<Menu, Long> {
    public List<Menu> getRootMenuByOrder();
}
