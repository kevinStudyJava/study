package com.sohu.bp.elite.api.service;

import com.sohu.bp.bean.UserInfo;
import com.sohu.bp.elite.enums.BpUserType;

/**
 * create time: 2015年12月21日 下午2:46:14
 * @auther dexingyang
 */
public interface UserInfoService {
	/**
	 * 获取封装后的用户信息，主要是针对公司账号，将用户头像和昵称替换为公司信息
	 * @param bpid
	 * @return
	 */
	public UserInfo getDecorateUserInfoByBpid(Long bpid);
	
	public UserInfo getUserInfoByBpid(Long bpid);
	
	public long getBpidByMobile(String mobile);
	
	public UserInfo getUserInfoByMobile(String mobile);
	
	public boolean hasUser(String mobile);
	
	/**
	 * 生成bpuser
	 * @param mobile
	 * @param nick
	 * @param avatar
	 * @param ip
	 * @param port
	 * @return
	 */
	public long addBpUser(String mobile, String nick, String avatar, String ip, Integer port);
	
	public BpUserType getBpUserType(Long bpid);
}
