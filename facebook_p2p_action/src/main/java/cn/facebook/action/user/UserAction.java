package cn.facebook.action.user;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.facebook.action.common.BaseAction;
import cn.facebook.cache.BaseCacheService;
import cn.facebook.domain.user.UserModel;
import cn.facebook.service.user.IUserService;
import cn.facebook.utils.FrontStatusConstants;
import cn.facebook.utils.ImageUtil;
import cn.facebook.utils.Response;

@Controller
@Namespace("/user")
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<UserModel>{

	@Autowired
	private BaseCacheService baseCacheService;
	@Autowired
	private IUserService userService;
	
	private UserModel user = new UserModel();
	
	@Override
	public UserModel getModel() {
		return user;
	}
	// 注册操作
		@Action("signup")
		public void regist() {
			// 1.使用模型驱动完成请求参数封装.
			// 2.有些参数封装不上，例如验证码----必须手动获取

			// 3.完成添加用户操作
			boolean flag = userService.addUser(user);

			// 4.响应数据
			try {
				if (flag) {
					this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
				} else {
					this.getResponse().getWriter()
							.write(Response.build().setStatus(FrontStatusConstants.REGISTER_LOSED).toJSON());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	// 验证验证码是否正确
	@Action("codeValidate")
	public void codeValidate() {
		// 1.得到请求参数
		String signUuid = this.getRequest().getParameter("signUuid");
		String signCode = this.getRequest().getParameter("signCode");
		try {
			if (StringUtils.isBlank(signUuid)) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
			if (StringUtils.isBlank(signCode)) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
			// 2.判断验证码是否正确
			String _signCode = baseCacheService.get(signUuid);
			if (StringUtils.isBlank(_signCode)) {
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				return;
			}
			// 3.响应数据到浏览器
			if (signCode.equalsIgnoreCase(_signCode)) {
				// 正确
				this.getResponse().getWriter().write(Response.build().setStatus("1").toJSON());
			} else {
				// 不正确
				this.getResponse().getWriter()
						.write(Response.build().setStatus(FrontStatusConstants.INPUT_ERROR_OF_VALIDATE_CARD).toJSON());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Action("validatePhone")
	public void validatePhone(){
		String phone = this.getRequest().getParameter("phone");
		UserModel user = userService.findByPhone(phone);
			try {
				if(user == null){
					this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
				} else {
					this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Action("validateUserName")
	public void validateUserName(){
		String username = this.getRequest().getParameter("username");
		UserModel user = userService.findByUsername(username);
			try {
				if(user == null){
					this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).toJSON());
				} else {
					this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.BREAK_DOWN).toJSON());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	//生成验证码
	@Action("validateCode")
	public void validateCode(){
		String tokenUuid = this.getRequest().getParameter("tokenUuid");
		String value = baseCacheService.get(tokenUuid);
		
		if(StringUtils.isNotBlank(value)){
			String str = ImageUtil.getRundomStr();
			
			baseCacheService.set(tokenUuid, str);//key是uuid,value是生成的验证码
			baseCacheService.expire(tokenUuid, 3*60);
			
			try {
				ImageUtil.getImage(str, this.getResponse().getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
	}
	//生成uuid
	@Action("uuid")
	public void uuid(){
		String uuid = UUID.randomUUID().toString();
		
		baseCacheService.set(uuid, uuid);
		baseCacheService.expire(uuid, 60 * 3);
		
		try {
			this.getResponse().getWriter().write(Response.build().setStatus(FrontStatusConstants.SUCCESS).setUuid(uuid).toJSON());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
