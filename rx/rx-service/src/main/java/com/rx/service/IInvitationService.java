package com.rx.service;

import java.util.List;

import com.rx.entity.User;

public interface IInvitationService extends IBaseService<User, Long> {
	
	public List<User> selectByLoginid(Long loginId);
}
