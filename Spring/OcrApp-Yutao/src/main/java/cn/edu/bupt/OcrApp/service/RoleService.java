package cn.edu.bupt.OcrApp.service;


import cn.edu.bupt.OcrApp.dto.RoleDto;

public interface RoleService {

	RoleDto saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
