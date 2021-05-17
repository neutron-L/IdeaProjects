package cn.edu.bupt.OcrApp.controller;


import cn.edu.bupt.OcrApp.dao.UserDao;
import cn.edu.bupt.OcrApp.dto.UserDto;
import cn.edu.bupt.OcrApp.model.SysUser;
import cn.edu.bupt.OcrApp.service.UserService;
import cn.edu.bupt.common.utils.CommonResult;
import cn.edu.bupt.common.utils.UserUtil;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "用户")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


    @PostMapping("addUser")
    @ApiOperation(value = "新增用户")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public CommonResult saveUser(@RequestBody UserDto userDto) {
        SysUser u = userService.getUser(userDto.getUsername());
        if (u != null) {
            return new CommonResult().failed("用户名已存在");
        }
        return new CommonResult().success(userService.saveUser(userDto));
    }


    @PutMapping("updateUser")
    @ApiOperation(value = "修改用户")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public CommonResult updateUser(@RequestBody UserDto userDto) {
        return new CommonResult().success(userService.updateUser(userDto));
    }


    @PutMapping("headImgUrl")
    @ApiOperation(value = "修改头像")
    public CommonResult updateHeadImgUrl(@RequestParam String headImgUrl) {
        SysUser user = UserUtil.getLoginUser();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setHeadImgUrl(headImgUrl);
        userService.updateUser(userDto);
        log.debug("{}修改了头像", user.getUsername());
        return new CommonResult().success("头像修改成功");
    }


    @PutMapping("/{username}")
    @ApiOperation(value = "修改密码")
    @PreAuthorize("hasAuthority('sys:user:password')")
    public CommonResult changePassword(@PathVariable String username, String oldPassword, String newPassword) {
        userService.changePassword(username, oldPassword, newPassword);
        return new CommonResult().success("密码修改成功");
    }

    @GetMapping("userList")
    @ApiOperation("根据用户名，昵称，用户状态模糊查询用户 查询条件可为空")
    public CommonResult userList(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) String nickname,
                                 @RequestParam(defaultValue = "1") Integer status,
                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUsers = userDao.listUser(username, nickname, status);
        return new CommonResult().pageSuccess(sysUsers);
    }



//	@GetMapping
//	@ApiOperation(value = "用户列表")
//	@PreAuthorize("hasAuthority('sys:user:query')")
//	public PageTableResponse listUsers(PageTableRequest request) {
//		return new PageTableHandler(new CountHandler() {
//
//			@Override
//			public int count(PageTableRequest request) {
//				return userDao.count(request.getParams());
//			}
//		}, new ListHandler() {
//
//			@Override
//			public List<SysUser> list(PageTableRequest request) {
//				List<SysUser> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
//				return list;
//			}
//		}).handle(request);
//	}

    @ApiOperation(value = "当前登录用户")
    @GetMapping("/current")
    public CommonResult currentUser() {
        return new CommonResult().success(UserUtil.getLoginUser());
    }

    @ApiOperation(value = "根据用户id获取用户")
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:user:query')")
    public CommonResult user(@PathVariable Long id) {
        SysUser user = userDao.getById(id);
        if (user == null) {
            return new CommonResult().failed("用户id不存在");
        }
        return new CommonResult().success(user);
    }

}
