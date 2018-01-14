package com.rx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.dao.UserMapper;
import com.rx.entity.User;
import com.rx.service.IInvitationService;

import tk.mybatis.mapper.entity.Example;

@Service
public class InvitationServiceImpl extends AbstractBaseService<User, Long> implements IInvitationService {

	//private InvitationMapper invitationMapper;

	/**
	 * @param invitationMapper the invitationMapper to set
	 * set方式注入
	 */
	public void setInvitationMapper(UserMapper agentMapper) {		
		this.setMapper(agentMapper);
	}

	/**
	 * 根据登录用户查询邀请函列表 
	 * (non-Javadoc)
	 * @see com.rx.service.IInvitationService#selectByLoginid(java.lang.Long)
	 */
	@Override
	public List<User> selectByLoginid(Long loginId) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("loginId", loginId);
		return mapper.selectByExample(example);
	}

}
