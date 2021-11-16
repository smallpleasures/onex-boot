package com.nb6868.onex.common.dingtalk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "钉钉基础,返回体")
public class BaseResponse implements Serializable {

    @ApiModelProperty(value = "返回码")
    private int errcode;

    @ApiModelProperty(value = "返回描述")
    private String errmsg;

    public BaseResponse(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    /**
     * 是否执行成功
     */
    public boolean isSuccess() {
        return errcode == 0;
    }

    public BaseResponse error(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        return this;
    }
}
