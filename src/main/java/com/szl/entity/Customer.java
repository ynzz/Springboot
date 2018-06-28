package com.szl.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName(value = "customer")
public class Customer implements Serializable{

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	@TableId
	private String uuid;//主键
	private String delFlag;//逻辑删除标识
	private String opeTime;//操作时间
	private String oper;//操作人
	private String createTime;//创建时间
	private String customerName;//用户名
	private String email;//邮箱
	private String frozenState;//冻结状态
	private String frozenType;//冻结类型
	private String lastWrongLoginTime;//最后登录错误时间
	private Integer loginErrorTimes;//登录错误次数
	private String mobile;//手机号
	private String password;//登录密码
	private String salt;//密码哈希
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFrozenState() {
		return frozenState;
	}
	public void setFrozenState(String frozenState) {
		this.frozenState = frozenState;
	}
	public String getFrozenType() {
		return frozenType;
	}
	public void setFrozenType(String frozenType) {
		this.frozenType = frozenType;
	}
	public String getLastWrongLoginTime() {
		return lastWrongLoginTime;
	}
	public void setLastWrongLoginTime(String lastWrongLoginTime) {
		this.lastWrongLoginTime = lastWrongLoginTime;
	}
	public Integer getLoginErrorTimes() {
		return loginErrorTimes;
	}
	public void setLoginErrorTimes(Integer loginErrorTimes) {
		this.loginErrorTimes = loginErrorTimes;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "Customer [uuid=" + uuid + ", delFlag=" + delFlag + ", opeTime="
				+ opeTime + ", oper=" + oper + ", createTime=" + createTime
				+ ", customerName=" + customerName + ", email=" + email
				+ ", frozenState=" + frozenState + ", frozenType=" + frozenType
				+ ", lastWrongLoginTime=" + lastWrongLoginTime
				+ ", loginErrorTimes=" + loginErrorTimes + ", mobile=" + mobile
				+ ", password=" + password + ", salt=" + salt + "]";
	}
	
}
