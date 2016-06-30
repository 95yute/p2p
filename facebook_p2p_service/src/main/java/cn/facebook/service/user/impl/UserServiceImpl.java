package cn.facebook.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.facebook.dao.user.IUserDAO;
import cn.facebook.dao.userAccount.IUserAccountDAO;
import cn.facebook.domain.user.UserModel;
import cn.facebook.domain.userAccount.UserAccountModel;
import cn.facebook.service.user.IUserService;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDao;
	
	@Autowired
	private IUserAccountDAO userAccountDao;
	
	@Override
	public UserModel findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	@Override
	public UserModel findByPhone(String phone) {
		return userDao.findByPhone(phone);
	}
	@Override
	public boolean addUser(UserModel user) {
		UserModel userModel = userDao.save(user);
		if (userModel == null) {
			return false;
		}
		UserAccountModel uam = new UserAccountModel();
		uam.setUserId(user.getId());
		UserAccountModel returvalue2 = userAccountDao.save(uam);
		if (returvalue2 == null) {
			return false;
		}
		return true;
	}

}
