package com.jy.metro.enums;

/**
 * ResultCodeEnum
 *
 * @author shisan
 * @create 2017-07-19 下午2:02
 **/
public enum ResultCodeEnum {
    /**
     * 失败
     */
    FAIL(1, "fail"),

    /**
     * 成功
     */
    SUCCESS(0, "success"),

    /**
     * 已被踢出
     */
    KICKED_OUT(2, " kicked_out"),

    /**
     * 暂无权限
     */
    NO_PERMISSION(3, "no_permission"),

    CRM_NOT_LOGIN(1992, "crm_not_login");

    private Integer value;

    private String desc;

    ResultCodeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
