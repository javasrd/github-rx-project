package com.rx.service.impl.back;

import org.springframework.stereotype.Service;

import com.rx.dao.PermissionMapper;
import com.rx.entity.Permission;
import com.rx.service.back.IPermissionService;
import com.rx.service.impl.AbstractBaseService;

@Service("permissionServiceBean")
public class PermissionServiceImpl extends AbstractBaseService<Permission, Long> implements IPermissionService {

	private PermissionMapper permisstionMapper;

	/**
	 * @param permisstionMapper the permisstionMapper to set
	 * set方式注入
	 */
	public void setPermissionMapper(PermissionMapper permisstionMapper) {
		this.permisstionMapper = permisstionMapper;
		this.setMapper(permisstionMapper);
	}

}
