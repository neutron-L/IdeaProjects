package cn.edu.bupt.OcrApp.service.serviceImpl;


import cn.edu.bupt.OcrApp.model.SysUser;
import cn.edu.bupt.OcrApp.dao.UserDao;
import cn.edu.bupt.OcrApp.dto.UserDto;
import cn.edu.bupt.OcrApp.service.UserService;
import cn.edu.bupt.common.enums.ExceptionEnum;
import cn.edu.bupt.common.exception.CabbageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public SysUser saveUser(UserDto userDto) {
		SysUser user = userDto;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(SysUser.Status.VALID);
		userDao.save(user);
		saveUserRoles(user.getId(), userDto.getRoleIds());

		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	private void saveUserRoles(Long userId, List<Long> roleIds) {
		if (roleIds != null) {
			userDao.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userDao.saveUserRoles(userId, roleIds);
			}
		}
	}

	@Override
	public SysUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public void changePassword(String username, String oldPassword, String newPassword) {
		SysUser u = userDao.getUser(username);
		if (u == null) {
			throw new CabbageException(ExceptionEnum.USER_NOT_FOUND);
		}

		if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
			throw new CabbageException(ExceptionEnum.OLD_PASSWORD_ERROR);
		}

		userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));

		log.debug("修改{}的密码", username);
	}

	@Override
	@Transactional
	public UserDto updateUser(UserDto userDto) {
		SysUser user = userDao.getById(userDto.getId());
		if(user==null){
			throw new CabbageException(ExceptionEnum.USER_NOT_FOUND);
		}
		if(!user.getUsername().equals(userDto.getUsername())) {
			SysUser u = userService.getUser(userDto.getUsername());
			if (u != null) {
				throw new CabbageException(ExceptionEnum.USERNAME_EXIST);
			}
		}
		int update = userDao.update(userDto);
		if(update==0){
			throw new CabbageException(ExceptionEnum.DATA_UPDATE_FAIL);
		}
		saveUserRoles(userDto.getId(), userDto.getRoleIds());

		return userDto;
	}

}
