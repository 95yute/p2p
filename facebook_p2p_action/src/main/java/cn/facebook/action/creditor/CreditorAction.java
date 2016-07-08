package cn.facebook.action.creditor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.facebook.action.common.BaseAction;
import cn.facebook.domain.creditor.CreditorModel;
import cn.facebook.service.creditor.ICreditorService;
import cn.facebook.util.constant.ClaimsType;
import cn.facebook.utils.FrontStatusConstants;
import cn.facebook.utils.RandomNumberUtil;
import cn.facebook.utils.Response;

@Controller
@Namespace("/creditor")
@Scope("prototype")
public class CreditorAction extends BaseAction implements ModelDriven<CreditorModel>{

	private CreditorModel cm = new CreditorModel();
	
	@Override
	public CreditorModel getModel() {
		return cm;
	}
	
	@Autowired
	private ICreditorService creditorService;
	
	@Action("addCreditor")
	public void addCreditor(){
//		contractNo : undefined, // 借款Id（合同编号）
		try {
			if (StringUtils.isEmpty(cm.getContractNo())) {
					this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CONTRACT_NO).toJSON());
					return;
			} 
//			debtorsName : undefined, // 债务人
			if (StringUtils.isEmpty(cm.getDebtorsName())) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_DEBTOR).toJSON());
				return;
			}
//			debtorsId : undefined, // 身份证号
			if (StringUtils.isEmpty(cm.getDebtorsId())) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_ID_CARD).toJSON());
				return;
			}
//			loanPurpose : undefined, // 借款用途
			if (StringUtils.isEmpty(cm.getLoanPurpose())) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_PURPOSE).toJSON());
				return;
			}
//			loanType : undefined, // 借款类型（标的类型）
			if (StringUtils.isEmpty(cm.getLoanType())) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_BORROWING_TYPE).toJSON());
				return;
			}
//			loanPeriod : undefined, // 原始期限（月）源
			if (StringUtils.isEmpty(cm.getLoanPeriod() + "" )) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_ORIGINAL_DEADLINE).toJSON());
				return;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//			loanStartDate : undefined, // 原始借款开始日期
			if (StringUtils.isEmpty(sdf.format(cm.getLoanStartDate()))) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_ORIGINAL_BORROWING_BEGIN_DATE).toJSON());
				return;
			}
//			loanEndDate : undefined, // 原始借款到期日期
			if (StringUtils.isEmpty(sdf.format(cm.getLoanEndDate()))) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_ORIGINAL_BORROWING_MATURITY_DATE).toJSON());
				return;
			}
//			repaymentStyle : 11601, // 还款方式 radius
			if (StringUtils.isEmpty(cm.getRepaymentStyle() + "" )) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_REPAYMENT_TYPE).toJSON());
				return;
			}
			if (StringUtils.isEmpty(cm.getRepaymenDate())) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_REPAYMENT_DATE).toJSON());
				return;
			}
//			repaymenMoney : undefined, // 期供金额（元）
			if (StringUtils.isEmpty(cm.getRepaymenMoney() + "")) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_REPAYMENT_AMOUNT).toJSON());
				return;
			}
//			debtMoney : undefined, // 债权金额（元）
			if (StringUtils.isEmpty(cm.getDebtMoney() + "" )) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CLAIM_AMOUNT).toJSON());
				return;
			}
//			debtMonthRate : undefined, // 债权年化利率（%// ）
			if (StringUtils.isEmpty(cm.getDebtMoney() + "" )) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CLAIM_YEAR_RATE).toJSON());
				return;
			}
//			debtTransferredMoney : undefined, // 债权转入金额
			if (StringUtils.isEmpty(cm.getDebtTransferredMoney() + "")) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CLAIM_ROLL_IN_AMOUNT).toJSON());
				return;
			}
//			debtTransferredPeriod : undefined, // 债权可用期（月）
			if (StringUtils.isEmpty(cm.getDebtTransferredPeriod() + "")) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CALIM_ROLL_IN_DATE).toJSON());
				return;
			}
//			debtTransferredDate : undefined, // 债权转入日期
			if (StringUtils.isEmpty(sdf.format(cm.getDebtTransferredDate()))) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CALIM_ROLL_IN_DEADLINE).toJSON());
				return;
			}
//			debtRansferOutDate : undefined, // 债权转出日期
			if (StringUtils.isEmpty(sdf.format(cm.getDebtRansferOutDate()))) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_CALIM_ROLL_OUT_DATE).toJSON());
				return;
			}
//			creditor : undefined
			if (StringUtils.isEmpty(cm.getCreditor())) {
				this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.NULL_OF_DEBTEE).toJSON());
				return;
			}
			
			cm.setDebtNo("ZQNO" + RandomNumberUtil.randomNumber(new Date()));
			cm.setBorrowerId(1);
			cm.setDebtStatus(1);
			cm.setDebtStatus(ClaimsType.UNCHECKDE);
			cm.setMatchedMoney(0.0);
			cm.setMatchedStatus(ClaimsType.UNMATCH);
			cm.setDebtType(FrontStatusConstants.NULL_SELECT_OUTACCOUNT);
			cm.setAvailablePeriod(cm.getDebtTransferredPeriod());
			cm.setAvailableMoney(cm.getDebtTransferredMoney());
			
			creditorService.save(cm);
			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
			return;
		} catch (IOException e) {
				e.printStackTrace();
				try {
					this.getResponse().getWriter().write(Response.build().setStatus("0").toJSON());
					return;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

	}
}
