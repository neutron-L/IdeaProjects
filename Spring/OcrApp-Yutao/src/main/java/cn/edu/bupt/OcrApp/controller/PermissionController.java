package cn.edu.bupt.OcrApp.controller;

import cn.edu.bupt.OcrApp.dao.PermissionDao;
import cn.edu.bupt.OcrApp.dto.LoginUser;
import cn.edu.bupt.OcrApp.model.Permission;
import cn.edu.bupt.OcrApp.service.PermissionService;
import cn.edu.bupt.common.utils.CommonResult;
import cn.edu.bupt.common.utils.UserUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Api(tags = "权限")
@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private PermissionService permissionService;

	@ApiOperation(value = "当前登录用户拥有的权限(权限树形式)")
	@GetMapping("/current")
	public CommonResult permissionsCurrent() {
		LoginUser loginUser = UserUtil.getLoginUser();
		List<Permission> list = loginUser.getPermissions();
		final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

//		setChild(permissions);
//
//		return permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
		// 2018.06.09 支持多级菜单
        List<Permission> firstLevel = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        firstLevel.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });

        return new CommonResult().success(firstLevel);
	}

	/**
	 * 设置子元素
	 * 2018.06.09
	 *
	 * @param p
	 * @param permissions
	 */
	private void setChild(Permission p, List<Permission> permissions) {
		List<Permission> child = permissions.parallelStream().filter(a -> a.getParentId().equals(p.getId())).collect(Collectors.toList());
		p.setChild(child);
		if (!CollectionUtils.isEmpty(child)) {
			child.parallelStream().forEach(c -> {
				//递归设置子元素，多级菜单支持
				setChild(c, permissions);
			});
		}
	}

//	private void setChild(List<Permission> permissions) {
//		permissions.parallelStream().forEach(per -> {
//			List<Permission> child = permissions.stream().filter(p -> p.getParentId().equals(per.getId()))
//					.collect(Collectors.toList());
//			per.setChild(child);
//		});
//	}

	/**
	 * 菜单列表
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param list
	 */
	private void setPermissionsList(Long pId, List<Permission> permissionsAll, List<Permission> list) {
		for (Permission per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				list.add(per);
				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					setPermissionsList(per.getId(), permissionsAll, list);
				}
			}
		}
	}

	@GetMapping("getPermissionsList")
	@ApiOperation(value = "权限列表")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public CommonResult permissionsList() {
		List<Permission> permissionsAll = permissionDao.listAll();

		List<Permission> list = Lists.newArrayList();
		setPermissionsList(0L, permissionsAll, list);

		return new CommonResult().success(list);
	}

	@GetMapping("/all")
	@ApiOperation(value = "权限树")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public CommonResult permissionsAll() {
		List<Permission> permissionsAll = permissionDao.listAll();
		JSONArray array = new JSONArray();
		setPermissionsTree(0L, permissionsAll, array);

		return new CommonResult().success(array);
	}

	@GetMapping("/parents")
	@ApiOperation(value = "一级菜单")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public CommonResult parentMenu() {
		List<Permission> parents = permissionDao.listParents();

		return new CommonResult().success(parents);
	}

	/**
	 * 菜单树
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param array
	 */
	private void setPermissionsTree(Long pId, List<Permission> permissionsAll, JSONArray array) {
		for (Permission per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				String string = JSONObject.toJSONString(per);
				JSONObject parent = (JSONObject) JSONObject.parse(string);
				array.add(parent);

				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					JSONArray child = new JSONArray();
					parent.put("child", child);
					setPermissionsTree(per.getId(), permissionsAll, child);
				}
			}
		}
	}

	@GetMapping("listByRoleId")
	@ApiOperation(value = "根据角色id获取权限")
	@PreAuthorize("hasAnyAuthority('sys:menu:query','sys:role:query')")
	public CommonResult listByRoleId(@RequestParam Long roleId) {
		return new CommonResult().success(permissionDao.listByRoleId(roleId));
	}


	@PostMapping("add")
	@ApiOperation(value = "新增权限")
	@PreAuthorize("hasAuthority('sys:menu:add')")
	public CommonResult add(@RequestBody Permission permission) {
		permissionDao.save(permission);
		return new CommonResult().success(permission);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据权限id获取权限")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public CommonResult get(@PathVariable Long id) {
		Permission permission = permissionDao.getById(id);
		if(permission==null){
			return new CommonResult().failed("权限id不存在");
		}
		return new CommonResult().success(permission);
	}


	@PutMapping("update")
	@ApiOperation(value = "修改权限")
	@PreAuthorize("hasAuthority('sys:menu:add')")
	public CommonResult update(@RequestBody Permission permission) {
		permissionService.update(permission);
		return new CommonResult().success(permission);
	}

	/**
	 * 校验权限
	 * 
	 * @return
	 */
	@GetMapping("/owns")
	@ApiOperation(value = "校验当前用户的权限")
	public CommonResult ownsPermission() {
		List<Permission> permissions = UserUtil.getLoginUser().getPermissions();
		if (CollectionUtils.isEmpty(permissions)) {
			return new CommonResult().success(Collections.emptySet());
		}
		Set<String> collect = permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
				.map(Permission::getPermission).collect(Collectors.toSet());
		return new CommonResult().success(collect);
	}


	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除权限")
	@PreAuthorize("hasAuthority('sys:menu:del')")
	public CommonResult delete(@PathVariable Long id) {
		Permission permission = permissionDao.getById(id);
		if(permission==null){
			return new CommonResult().failed("权限id不存在");
		}
		permissionService.delete(id);
		return new CommonResult().success("删除成功");
	}
}
