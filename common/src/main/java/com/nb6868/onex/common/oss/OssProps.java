package com.nb6868.onex.common.oss;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 存储配置
 *
 * @author Charles zhangchaoxu@gmail.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "onex.oss")
public class OssProps {

    @ApiModelProperty(value = "配置项")
    private Map<String, Config> configs;

    @Data
    public static class Config {

        @ApiModelProperty(value = "类型 aliyun阿里云/huaweicloud华为云/local本地")
        private String type;

        @ApiModelProperty(value = "绑定的域名")
        private String domain;

        @ApiModelProperty("保留文件名")
        private Boolean keepFileName;

        @ApiModelProperty("安全访问")
        private Boolean secure;

        @ApiModelProperty(value = "角色ARN")
        private String roleArn;

        @ApiModelProperty(value = "区域")
        private String region;

        @ApiModelProperty(value = "角色SessionName")
        private String roleSessionName;

        @ApiModelProperty(value = "STS有效秒数")
        private Long stsDurationSeconds;

        @ApiModelProperty(value = "路径前缀")
        private String prefix;

        @ApiModelProperty(value = "EndPoint")
        private String endPoint;

        @ApiModelProperty(value = "AccessKeyId")
        private String accessKeyId;

        @ApiModelProperty(value = "AccessKeySecret")
        private String accessKeySecret;

        @ApiModelProperty(value = "阿里云BucketName")
        private String bucketName;

        @ApiModelProperty(value = "本地上传存储目录")
        private String localPath;

    }

}
