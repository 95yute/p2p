package cn.facebook.service.user;

import cn.facebook.domain.user.UserModel;

public interface IUserService {

	public UserModel findByUsername(String username);
	
	public UserModel findByPhone(String phone);

	public boolean addUser(UserModel user);

	public UserModel login(String username, String pwd);

	public UserModel findById(int userid);
}
