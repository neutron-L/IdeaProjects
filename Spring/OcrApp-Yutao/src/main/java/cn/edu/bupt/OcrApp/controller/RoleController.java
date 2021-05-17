package cn.edu.bupt.OcrApp.controller;

import cn.edu.bupt.OcrApp.dao.UserDao;
import cn.edu.bupt.OcrApp.model.Role;
import cn.edu.bupt.OcrApp.model.SysUser;
import cn.edu.bupt.OcrApp.dao.RoleDao;
import cn.edu.bupt.OcrApp.dto.RoleDto;
import cn.edu.bupt.OcrApp.service.RoleService;
import cn.edu.bupt.common.utils.CommonResult;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "角色")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;


    @PostMapping
    @ApiOperation(value = "新增角色")
    @PreAuthorize("hasAuthority('sys:role:add')")
    public CommonResult saveRole(@RequestBody RoleDto roleDto) {
        return new CommonResult().success(roleService.saveRole(roleDto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取角色")
    @PreAuthorize("hasAuthority('sys:role:query')")
    public CommonResult get(@PathVariable Long id) {
        Role role = roleDao.getById(id);
        if (role == null) {
            return new CommonResult().failed("id不存在");
        }
        return new CommonResult().success(role);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有角色")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public CommonResult roles() {
        return new CommonResult().success(roleDao.list(Maps.newHashMap(), null, null));
    }

    @GetMapping(params = "userId")
    @ApiOperation(value = "根据用户id获取拥有的角色")
    @PreAuthorize("hasAnyAuthority('sys:user:query','sys:role:query')")
    public CommonResult roles(Long userId) {
        SysUser user = userDao.getById(userId);
        if(user==null){
            return new CommonResult().failed("用户id不存在");
        }
        List<Role> roles = roleDao.listByUserId(userId);
        if(CollectionUtils.isEmpty(roles)){
            return new CommonResult().failed("该用户还未添加角色");
        }
        return new CommonResult().success(roles);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    @PreAuthorize("hasAuthority('sys:role:del')")
    public void delete(@PathVariable Long id) {
        roleService.deleteRole(id);

    }
}
