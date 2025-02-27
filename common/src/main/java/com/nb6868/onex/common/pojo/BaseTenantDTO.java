package com.nb6868.onex.common.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础租户DTO
 *
 * @author Charles zhangchaoxu@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseTenantDTO extends BaseDTO {

    @ApiModelProperty(value = "租户编码")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String tenantCode;

}
