package com.nb6868.onex.msg.controller;

import com.nb6868.onex.common.annotation.LogOperation;
import com.nb6868.onex.common.exception.ErrorCode;
import com.nb6868.onex.common.pojo.PageData;
import com.nb6868.onex.common.pojo.Result;
import com.nb6868.onex.common.validator.AssertUtils;
import com.nb6868.onex.common.validator.group.AddGroup;
import com.nb6868.onex.common.validator.group.DefaultGroup;
import com.nb6868.onex.common.validator.group.UpdateGroup;
import com.nb6868.onex.msg.dto.MailTplDTO;
import com.nb6868.onex.msg.service.MailTplService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 消息模板
 *
 * @author Charles zhangchaoxu@gmail.com
 */
@RestController
@RequestMapping("/msg/mailTpl")
@Validated
@Api(tags = "消息模板")
public class MailTplController {

    @Autowired
    MailTplService mailTplService;

    @GetMapping("list")
    @ApiOperation("列表")
    @RequiresPermissions("msg:mailTpl:info")
    public Result<?> list(@ApiIgnore @RequestParam Map<String, Object> params) {
        List<?> list = mailTplService.listDto(params);
        return new Result<>().success(list);
    }

    @GetMapping("page")
    @ApiOperation("分页")
    @RequiresPermissions("msg:mailTpl:info")
    public Result<?> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<?> page = mailTplService.pageDto(params);

        return new Result<>().success(page);
    }

    @GetMapping("info")
    @ApiOperation("信息")
    @RequiresPermissions("msg:mailTpl:info")
    public Result<?> info(@NotNull(message = "{id.require}") @RequestParam Long id) {
        MailTplDTO data = mailTplService.getDtoById(id);
        AssertUtils.isNull(data, ErrorCode.DB_RECORD_NOT_EXISTED);

        return new Result<>().success(data);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("msg:mailTpl:update")
    public Result<?> save(@Validated(value = {DefaultGroup.class, AddGroup.class}) @RequestBody MailTplDTO dto) {
        mailTplService.saveDto(dto);

        return new Result<>().success(dto);
    }

    @PutMapping("update")
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("msg:mailTpl:update")
    public Result<?> update(@Validated(value = {DefaultGroup.class, UpdateGroup.class}) @RequestBody MailTplDTO dto) {
        mailTplService.updateDto(dto);

        return new Result<>().success(dto);
    }

    @DeleteMapping("delete")
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("msg:mailTpl:delete")
    public Result<?> delete(@NotBlank(message = "{id.require}") @RequestParam String id) {
        mailTplService.logicDeleteById(id);

        return new Result<>();
    }

}
