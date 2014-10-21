package com.eq.service.shiro;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.eq.dao.entity.system.User;

public class PasswordHelper
{
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private final int hashIterations = 2;
	public void encryptPassword(User user)
	{
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPwd(), ByteSource.Util.bytes(user.getCredentialsSalt()+"1991"), hashIterations).toHex();
		user.setPwd(newPassword);
	}
}
