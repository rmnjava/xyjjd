/*
 * @author Michael Sun
 *
 * more see: http://www.micmiu.com
 *
 */
package com.redstar.jjd.admin.service;

import java.util.List;

import com.redstar.jjd.model.Menu;

/**
 * 
 * ClassName: MenuService <br/>
 * Date: 2016年6月23日 下午4:58:31 <br/>
 * Description: 菜单service <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface MenuService {

    /**
     * 获取所有的菜单
     * 
     * @return
     */
    List<Menu> getRootMenuByOrder();

    Menu getById(Long menuId);

}
