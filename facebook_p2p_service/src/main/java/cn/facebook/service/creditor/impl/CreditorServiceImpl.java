package cn.facebook.service.creditor.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.facebook.dao.creditor.ICreditorDAO;
import cn.facebook.domain.creditor.CreditorModel;
import cn.facebook.service.creditor.ICreditorService;

@Service
@Transactional
public class CreditorServiceImpl implements ICreditorService {

	@Autowired
	private ICreditorDAO creditorDao;
	@Override
	public void save(CreditorModel cm) {
		creditorDao.save(cm);
	}
	@Override
	public void addMultiple(List<CreditorModel> cms) {
		for (CreditorModel c:cms){
			creditorDao.save(c);
		}
		
	}

}
