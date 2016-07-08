package cn.facebook.service.creditor;

import java.util.List;

import cn.facebook.domain.creditor.CreditorModel;

public interface ICreditorService {

	void save(CreditorModel cm);

	void addMultiple(List<CreditorModel> cms);

}
