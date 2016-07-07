package cn.facebook.dao.accountLog;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.facebook.domain.accountLog.AccountLog;


public interface IAccountLogDAO extends JpaRepository<AccountLog, Integer> {

	

}
