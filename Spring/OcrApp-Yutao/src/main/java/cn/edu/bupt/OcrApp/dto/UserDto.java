package cn.edu.bupt.OcrApp.dto;



import cn.edu.bupt.OcrApp.model.SysUser;

import java.util.List;

public class UserDto extends SysUser {

	private static final long serialVersionUID = -184009306207076712L;

	private List<Long> roleIds;

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

}
