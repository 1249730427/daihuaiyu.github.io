package com.daihuaiyu.videoapplet.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息Vo
 *
 * @author daihuaiyu
 * @create: 2021-03-31 10:48
 **/
@Data
public class UserVo {
    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 用户登陆后生成的令牌
     */
    private String token;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",name = "username",example = "123",required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "用户密码",name = "password",example = "123",required = true)
    @JsonIgnore
    private String password;

    /**
     * 我的头像，如果没有默认给一张
     */
    @ApiModelProperty(hidden = true)
    private String faceImage;

    /**
     * 昵称
     */

    @ApiModelProperty(hidden = true)
    private String nickname;

    /**
     * 我的粉丝数量
     */
    @ApiModelProperty(hidden = true)
    private Integer fansCounts;

    /**
     * 我关注的人总数
     */
    @ApiModelProperty(hidden = true)
    private Integer followCounts;

    /**
     * 我接受到的赞美/收藏 的数量
     */
    @ApiModelProperty(hidden = true)
    private Integer receiveLikeCounts;

}
