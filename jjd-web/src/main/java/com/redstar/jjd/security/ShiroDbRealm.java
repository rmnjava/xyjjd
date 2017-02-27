package com.redstar.jjd.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.redstar.jjd.admin.service.OperatorService;
import com.redstar.jjd.model.Operator;
import com.redstar.jjd.model.Role;
import com.redstar.jjd.utils.ConvertUtils;
import com.redstar.jjd.vo.OperatorView;

/**
 * 自实现用户与权限查询. 演示关系，密码用明文存储，因此使用默认 的SimpleCredentialsMatcher.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    private static final Logger LOGGER = Logger.getLogger(ShiroDbRealm.class);

    @Autowired
    private OperatorService operatorService;

    /**
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
        String loginName = token.getUsername();
        if (loginName == null) {
            throw new AccountException(
                    "Null usernames are not allowed by this realm.");
        }
        // String captcha = token.getCaptcha();
        // String exitCode = (String) SecurityUtils.getSubject().getSession()
        // .getAttribute(CaptchaServlet.KEY_CAPTCHA);
        // if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
        // throw new CaptchaException("验证码错误");
        // }

        Operator operator = operatorService.getOperatorByLoginName(loginName);
        if (null == operator) {
            throw new UnknownAccountException("No account found for user ["
                    + loginName + "]");
        }
        OperatorView view = new OperatorView();
        ConvertUtils.convertObject(operator, view);
        return new SimpleAuthenticationInfo(new ShiroUser(view,
                view.getLoginName(), view.getOperatorId(), view.getName()),
                view.getPassword(), getName());

    }

    public static ShiroUser getCurrentUser() {
        ShiroUser currentUsesr = (ShiroUser) SecurityUtils.getSubject()
                .getPrincipal();
        return currentUsesr;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName())
                .iterator().next();
        Operator operator = operatorService.getOperatorByLoginName(shiroUser
                .getLoginName());
        if (operator != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            for (Role role : operator.getRoleList()) {
                List<com.redstar.jjd.model.Permission> permissions = role
                        .getPermissions();
                List<String> permStrs = new ArrayList<String>();
                for (com.redstar.jjd.model.Permission p : permissions) {
                    permStrs.add(p.getShiroPerm());
                }
                LOGGER.debug("===>:" + StringUtils.join(permStrs, ","));
                info.addStringPermissions(permStrs);
            }
            return info;
        } else {
            return null;
        }
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {

        private static final long serialVersionUID = -1748602382963711884L;
        private OperatorView operator;
        private String loginName;
        private String name;
        private Long id;

        public ShiroUser(OperatorView operator, String loginName, Long id,
                String name) {
            this.operator = operator;
            this.loginName = loginName;
            this.id = id;
            this.name = name;
        }

        public String getLoginName() {
            return loginName;
        }

        /**
         * 本函数输出将作为默认的<shiro:principal/>输出.
         */
        @Override
        public String toString() {
            return loginName;
        }

        public Long getId() {
            return id;
        }

        public OperatorView getOperator() {
            return operator;
        }

        public void setOperator(OperatorView operator) {
            this.operator = operator;
        }

        public String getName() {
            return name;
        }

    }
}
