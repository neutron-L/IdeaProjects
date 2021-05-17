package cn.edu.bupt.OcrApp.service;


import cn.edu.bupt.OcrApp.model.SysUser;
import cn.edu.bupt.OcrApp.dto.UserDto;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	UserDto updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
