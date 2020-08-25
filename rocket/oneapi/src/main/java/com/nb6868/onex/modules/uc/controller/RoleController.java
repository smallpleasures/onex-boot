package com.nb6868.onex.modules.uc.controller;

import com.nb6868.onex.booster.exception.ErrorCode;
import com.nb6868.onex.booster.pojo.PageData;
import com.nb6868.onex.booster.pojo.Result;
import com.nb6868.onex.booster.validator.AssertUtils;
import com.nb6868.onex.booster.validator.group.AddGroup;
import com.nb6868.onex.booster.validator.group.DefaultGroup;
import com.nb6868.onex.booster.validator.group.UpdateGroup;
import com.nb6868.onex.common.annotation.LogOperation;
import com.nb6868.onex.modules.uc.dto.RoleDTO;
import com.nb6868.onex.modules.uc.service.RoleDataScopeService;
import com.nb6868.onex.modules.uc.service.RoleMenuService;
import com.nb6868.onex.modules.uc.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author Charles zhangchaoxu@gmail.com
 */
@RestController
@RequestMapping("uc/role")
@Validated
@Api(tags="角色管理")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private RoleDataScopeService roleDataScopeService;

	@GetMapping("page")
	@ApiOperation("分页")
	@RequiresPermissions("uc:role:page")
	public Result<?> page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<RoleDTO> page = roleService.pageDto(params);

		return new Result<>().success(page);
	}

	@GetMapping("list")
	@ApiOperation("列表")
	@RequiresPermissions("uc:role:list")
	public Result<?> list(@ApiIgnore @RequestParam Map<String, Object> params){
		List<RoleDTO> data = roleService.listDto(params);

		return new Result<>().success(data);
	}

	@GetMapping("info")
	@ApiOperation("信息")
	@RequiresPermissions("uc:role:info")
	public Result<?> info(@NotNull(message = "{id.require}") @RequestParam Long id){
		RoleDTO data = roleService.getDtoById(id);
		AssertUtils.isNull(data, ErrorCode.DB_RECORD_NOT_EXISTED);

		// 查询角色对应的菜单
		List<Long> menuIdList = roleMenuService.getMenuIdListByRoleId(id);
		data.setMenuIdList(menuIdList);

		// 查询角色对应的数据权限
		List<Long> deptIdList = roleDataScopeService.getDeptIdListByUserId(id);
		data.setDeptIdList(deptIdList);

		return new Result<>().success(data);
	}

	@PostMapping("save")
	@ApiOperation("保存")
	@LogOperation("保存")
	@RequiresPermissions("uc:role:save")
	public Result<?> save(@Validated(value = {DefaultGroup.class, AddGroup.class}) @RequestBody RoleDTO dto){
		roleService.saveDto(dto);

		return new Result<>().success(dto);
	}

	@PutMapping("update")
	@ApiOperation("修改")
	@LogOperation("修改")
	@RequiresPermissions("uc:role:update")
	public Result<?> update(@Validated(value = {DefaultGroup.class, UpdateGroup.class}) @RequestBody RoleDTO dto){
		roleService.updateDto(dto);

		return new Result<>().success(dto);
	}

	@DeleteMapping("delete")
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("uc:role:delete")
	public Result<?> delete(@NotNull(message = "{id.require}") @RequestParam Long id) {
		roleService.logicDeleteById(id);

		return new Result<>();
	}

	@DeleteMapping("deleteBatch")
	@ApiOperation("批量删除")
	@LogOperation("批量删除")
	@RequiresPermissions("uc:role:deleteBatch")
	public Result<?> deleteBatch(@NotEmpty(message = "{ids.require}") @RequestBody List<Long> ids) {
		roleService.logicDeleteByIds(ids);

		return new Result<>();
	}
}
