package cn.facebook.service.productAccount.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.facebook.dao.accountLog.IAccountLogDAO;
import cn.facebook.dao.fundingNotMatched.IFundingNotMatchedDAO;
import cn.facebook.dao.productAccount.IProductAccountDAO;
import cn.facebook.dao.userAccount.IUserAccountDAO;
import cn.facebook.domain.ProductAccount;
import cn.facebook.domain.accountLog.AccountLog;
import cn.facebook.domain.productAcount.FundingNotMatchedModel;
import cn.facebook.domain.userAccount.UserAccountModel;
import cn.facebook.service.productAccount.IProductAccountService;
@Service
public class ProductAccountServiceImpl implements IProductAccountService{
	@Autowired
	private IProductAccountDAO productAccountDao;
	@Autowired
	private IAccountLogDAO accountLogDao;
	@Autowired
	private IFundingNotMatchedDAO fundingNotMatchedModelDao;
	@Autowired
	private IUserAccountDAO userAccountDao;
	@Transactional
	@Override
	public void addProductAccount(UserAccountModel userAccountModel, ProductAccount pa, AccountLog accountlog,
			FundingNotMatchedModel fnmm) {
		// 修改UserAccountModel表中数据
				userAccountDao.updateUserAccountById(userAccountModel.getBalance(), userAccountModel.getInverstmentW(), userAccountModel.getInterestTotal(),
						userAccountModel.getRecyclingInterest(), userAccountModel.getInverstmentA(), userAccountModel.getId());
				// 保存ProductAccount
				productAccountDao.save(pa);
				accountlog.setpId(pa.getpId());
				accountLogDao.save(accountlog);
				fnmm.setfInvestRecordId(pa.getpId());
				fundingNotMatchedModelDao.save(fnmm);
	}

}
