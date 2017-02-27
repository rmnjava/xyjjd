package com.redstar.jjd.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redstar.jjd.admin.dao.MenuDao;
import com.redstar.jjd.admin.service.MenuService;
import com.redstar.jjd.model.Menu;

/**
 * 
 * Description:
 * 
 * @author <a href="http://www.micmiu.com">Michael</a>
 * @see <a href="http://www.micmiu.com">http://www.micmiu.com</a>
 * @time Create on 2013-5-31 上午11:39:17
 * @version 1.0
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getRootMenuByOrder() {
        return menuDao.getRootMenuByOrder();
    }

    @Override
    public Menu getById(Long menuId) {
        return menuDao.get(menuId);
    }

}
