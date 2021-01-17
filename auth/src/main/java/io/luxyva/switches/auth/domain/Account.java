package io.luxyva.switches.auth.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_account
 * @author 
 */
@Data
public class Account implements Serializable {
    private Long id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 名称
     */
    private String realName;

    private String idNo;

    private String phone;

    /**
     * 关注我的
     */
    private Long follower;

    /**
     * 我关注的
     */
    private Long followed;

    /**
     * 收藏
     */
    private Long collect;

    /**
     * 性别
     */
    private Boolean gender;

    private String password;

    private String description;

    private Integer location;

    private String birthday;

    private String avatarUrl;

    private Date createDt;

    private Date modifyDt;

    private static final long serialVersionUID = 1L;
}