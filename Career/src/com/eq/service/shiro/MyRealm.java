package com.eq.service.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.eq.dao.entity.system.Permission;
import com.eq.dao.entity.system.Role;
import com.eq.dao.entity.system.User;
import com.eq.dao.impl.system.PermissionImpl;
import com.eq.dao.impl.system.RoleImpl;
import com.eq.dao.impl.system.UserImpl;

public class MyRealm extends AuthorizingRealm
{
	@Autowired
	private UserImpl userImpl;
	@Autowired
	private RoleImpl roleImpl;
	@Autowired
	private PermissionImpl permissionImpl;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		String username = (String)principals.getPrimaryPrincipal();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		List<Role> roleList = roleImpl.selectList(params);
		List<Permission> permissionList = permissionImpl.selectList(params);
		Set<String> roleSet = new HashSet<String>();
		Set<String> permissionSet = new HashSet<String>();
		for(int i=0;i<roleList.size();i++) {
			roleSet.add(roleList.get(i).getRole());
		}
		for(int i=0;i<permissionList.size();i++) {
			permissionSet.add(permissionList.get(i).getPermission());
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(permissionSet);
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
	{
		String username = (String)token.getPrincipal();
		User user = userImpl.selectOne(username);
		if(user==null) {
			throw new UnknownAccountException();//没找到帐号
		}
		if(user.getLock()==1) {
			throw new LockedAccountException(); //帐号锁定
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getName(), //用户名
                user.getPwd(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()+"1991"),//salt=username+salt
                getName()  //realm name
        );
		return authenticationInfo;
	}

}
