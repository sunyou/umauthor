package com.ai.umauthor.web.controller.open.api.operator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.domain.AjaxPageBean;
import com.ai.common.exception.BusinessException;
import com.ai.common.security.ILoginUser;
import com.ai.common.security.PasswordEncoderSerivce;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.service.mrg.interfaces.LoginUserSV;
import com.ai.umauthor.server.service.mrg.interfaces.OperatorSV;
import com.ai.umauthor.web.controller.open.api.BaseAPIController;

/**
 * @author Typhon Chens 2016年2月17日
 */
@Controller("operatorAPI")
@RequestMapping("/api/operator")
public class OperatorController extends BaseAPIController {
	private static final Logger log = LoggerFactory
			.getLogger(OperatorController.class);

	@Autowired
	private PasswordEncoderSerivce passwordEncoder;

	@Autowired
	private LoginUserSV loginUserSV;

	@Autowired
	OperatorSV operatorSV;

	@RequestMapping("/operateLogin")
	@ResponseBody
	public AjaxBean operateLogin(
			@RequestParam(value = "operatorCode") String operatorCode,
			@RequestParam(value = "password") String password) {

		boolean matches;

		AjaxBean result = new AjaxBean();
		if (StringUtils.isEmpty(operatorCode)) {
			result.setCode("-1");
			result.setMessage("操作员帐号不能为空");
			return result;
		}
		if (StringUtils.isEmpty(password)) {
			result.setCode("-2");
			result.setMessage("操作员密码不能为空");
			return result;
		}
		ILoginUser loginUser = loginUserSV
				.queryUserInfoByLoginCode(operatorCode);
		if (loginUser == null) {
			result.setCode("-3");
			result.setMessage("操作员帐号不存在");
			return result;
		}

		matches = passwordEncoder.match(new String(password),
				String.valueOf(loginUser.getPassword()));
		if (matches == false) {
			result.setCode("-4");
			result.setMessage("密码错误");
			return result;
		}

		result.setCode("0");
		result.setMessage(matches + "");
		return result;
	}

	@RequestMapping("/getOperatorById")
	@ResponseBody
	public AjaxBean getOperatorById(
			@RequestParam(value = "operatorId") String operatorId) {
		AjaxBean result = new AjaxBean();
		if (StringUtils.isEmpty(operatorId)) {
			result.setCode("-1");
			result.setMessage("操作员帐号不能为空");
			return result;
		}

		UmOperator operator;
		try {
			operator = operatorSV.selectOperatorById(operatorId);
			if (operator == null) {
				result.setCode("-2");
				result.setMessage("操作员ID不存在");
				return result;
			}
		} catch (BusinessException e) {
			result.setCode("-3");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setCode("0");
		result.setData(operator);
		result.setMessage("成功");
		return result;
	}

	@RequestMapping("/getOperatorByCode")
	@ResponseBody
	public AjaxBean getOperatorByCode(
			@RequestParam(value = "operatorCode") String operatorCode) {
		AjaxBean result = new AjaxBean();
		if (StringUtils.isEmpty(operatorCode)) {
			result.setCode("-1");
			result.setMessage("操作员帐号不能为空");
			return result;
		}

		UmOperator operator;
		try {
			operator = operatorSV.selectOperatorByCode(operatorCode);
			if (operator == null) {
				result.setCode("-2");
				result.setMessage("操作员编码不存在");
				return result;
			}
		} catch (BusinessException e) {
			result.setCode("-3");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setCode("0");
		result.setData(operator);
		result.setMessage("成功");
		return result;
	}

	@RequestMapping("/changeOperatorPassword")
	@ResponseBody
	public AjaxBean changeOperatorPassword(
			@RequestParam(value = "operatorId") String operatorId,
			@RequestParam(value = "oldPassword") String oldPassword,
			@RequestParam(value = "newPassword") String newPassword) {
		AjaxBean result = new AjaxBean();
		if (StringUtils.isEmpty(operatorId)) {
			result.setCode("-1");
			result.setMessage("操作员帐号不能为空");
			return result;
		}
		if (StringUtils.isEmpty(oldPassword)) {
			result.setCode("-2");
			result.setMessage("操作员旧密码不能为空");
			return result;
		}
		if (StringUtils.isEmpty(newPassword)) {
			result.setCode("-3");
			result.setMessage("操作员新密码不能为空");
			return result;
		}

		try {
			operatorSV
					.changePsw(new Long(operatorId), newPassword, oldPassword);
		} catch (BusinessException e) {
			result.setCode("-4");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setCode("0");
		result.setMessage("成功");
		return result;
	}

	/**
	 * 
	 * 功能描述：同步操作员信息
	 * 
	 * @author zhangly
	 *         <p>
	 *         创建日期 ：2016年2月24日 下午4:02:57
	 *         </p>
	 * 
	 * @param method
	 *            ：{add:表示新增,update:表示修改}
	 * @param operator
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@RequestMapping("/syncOperator")
	@ResponseBody
	public AjaxBean syncOperator(String method, UmOperator operator) {

		AjaxBean result = new AjaxBean();
		result.setCode(AjaxBean.SUCCESS_CODE);
		result.setMessage("success");
		try {
			if (null == operator) {
				result.setCode("999");
				result.setMessage("操作员信息不能为空");
				return result;
			}
			if (StringUtils.isEmpty(method) || "add".equals(method)) {
				operator.setAddFrom("2");// 工商联注册
				operator.setDomainId(new Long(1));
				operator.setOperatorType("02");
				operator.setOperatorLevel("5");
				operator = operatorSV.insertOperator(operator);
				result.setData(operator);
			} else {
				operatorSV.modifyOperator(operator);
			}
		 } catch(Exception e) {
			 result.setCode(AjaxBean.ERROR_CODE);
			 result.setMessage(e.getMessage());
		 }
		 return result;  
	}

	@RequestMapping("/changeOperatorPasswordNoOld")
	@ResponseBody
	public AjaxBean changeOperatorPasswordNoOld(
			@RequestParam(value = "operatorId") String operatorId,
			@RequestParam(value = "newPassword") String newPassword) {
		AjaxBean result = new AjaxBean();
		if (StringUtils.isEmpty(operatorId)) {
			result.setCode("-1");
			result.setMessage("操作员帐号不能为空");
			return result;
		}

		if (StringUtils.isEmpty(newPassword)) {
			result.setCode("-3");
			result.setMessage("操作员新密码不能为空");
			return result;
		}

		try {
			operatorSV.changePsw(new Long(operatorId), newPassword);
		} catch (BusinessException e) {
			result.setCode("-4");
			result.setMessage(e.getMessage());
			return result;
		}
		result.setCode("0");
		result.setMessage("成功");
		return result;
	}
}
