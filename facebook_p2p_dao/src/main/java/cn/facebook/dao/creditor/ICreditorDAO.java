package cn.facebook.dao.creditor;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.facebook.domain.creditor.CreditorModel;

public interface ICreditorDAO extends JpaRepository<CreditorModel, Integer> {

}
