package cn.facebook.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.facebook.domain.user.UserModel;

public interface IUserDAO extends JpaRepository<UserModel, Integer>{

	UserModel findByUsername(String username);
	
	UserModel findByPhone(String phone);

	UserModel findByUsernameAndPassword(String username, String pwd);
}
