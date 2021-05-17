package cn.edu.bupt.OcrApp.service;


import cn.edu.bupt.OcrApp.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
