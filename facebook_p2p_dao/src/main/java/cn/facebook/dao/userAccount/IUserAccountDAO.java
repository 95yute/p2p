package cn.facebook.dao.userAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.facebook.domain.userAccount.UserAccountModel;

public interface IUserAccountDAO extends JpaRepository<UserAccountModel, Integer> {

	UserAccountModel findByUserId(int id);
}
