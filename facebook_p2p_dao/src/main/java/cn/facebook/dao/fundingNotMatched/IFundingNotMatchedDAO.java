package cn.facebook.dao.fundingNotMatched;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.facebook.domain.productAcount.FundingNotMatchedModel;


public interface IFundingNotMatchedDAO extends JpaRepository<FundingNotMatchedModel, Integer> {

	

}
