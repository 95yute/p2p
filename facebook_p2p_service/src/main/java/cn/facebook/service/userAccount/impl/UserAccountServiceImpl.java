package cn.facebook.service.userAccount.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.facebook.dao.userAccount.IUserAccountDAO;
import cn.facebook.domain.userAccount.UserAccountModel;
import cn.facebook.service.userAccount.IUserAccountService;

@Service
public class UserAccountServiceImpl implements IUserAccountService{

	@Autowired
	private IUserAccountDAO userAccountDao;
	@Override
	public UserAccountModel findByUserId(int id) {
		return userAccountDao.findByUserId(id);
	}

}
