package com.redstar.jjd.admin.service;

import java.util.List;
import java.util.Map;

import com.redstar.jjd.model.Permission;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.query.RoleQuery;
import com.redstar.jjd.vo.RoleView;

/**
 * 
 * ClassName: RoleService <br/>
 * Date: 2016年6月23日 下午5:00:50 <br/>
 * Description: 角色service <br/>
 * 
 * @author huangrui
 * @version
 * @see
 */
public interface RoleService {

    public List<Role> query(RoleQuery roleQuery);

    public Role findRoleByRoleId(Long roleId);

    public void updateRole(Role role, List<Permission> permList);

    public Map<String, Object> delBatch(String ids);

    public void delete(Long id);

    public Role getRoleByName(String name);

    public List<RoleView> findAll();
}
