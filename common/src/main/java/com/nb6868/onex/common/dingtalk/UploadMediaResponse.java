package com.nb6868.onex.common.dingtalk;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel(value = "上传媒体文件,返回体")
@EqualsAndHashCode(callSuper = false)
public class UploadMediaResponse extends BaseResponse {

    @ApiModelProperty(value = "媒体文件类型")
    private String type;

    @ApiModelProperty(value = "媒体文件上传后获取的唯一标识")
    private String media_id;

    @ApiModelProperty(value = "媒体文件上传时间戳")
    private Long created_at;

}
