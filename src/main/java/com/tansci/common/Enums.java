package com.tansci.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @path：com.tansci.common.Enums.java
 * @className：Enums.java
 * @description：常用枚举
 * @author：tanyp
 * @dateTime：2022/03/29 16:14
 * @editNote：
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Enums {

    /**
     * 认证相关
     */
    AUTH_NO_TOKEN(401, "auth_type", "用户凭证已过期，请重新登录！"),
    AUTH_NO_ACCESS(403, "auth_type", "无访问权限，请核实!"),
    AUTH_NONEXISTENT(404, "auth_type", "请求路径不存在"),

    /**
     * 用户性别
     */
    USER_GENDER_MALE(0, "user_gender", "男"),
    USER_GENDER_GIRL(1, "user_gender", "女"),

    /**
     * 用户类型
     */
    USER_TYPE_0(0, "user_type", "超级管理员"),
    USER_TYPE_1(1, "user_type", "管理员"),
    USER_TYPE_2(2, "user_type", "普通用户"),

    /**
     * 付款码类型
     */
    PAY_CODE_WX(1, "pay_code_type", "微信"),
    PAY_CODE_ALL(2, "pay_code_type", "支付宝"),

    /**
     * 商品状态
     */
    GOODS_STATUS_0(0, "goods_status", "未上架"),
    GOODS_STATUS_1(1, "goods_status", "已上架"),
    GOODS_STATUS_2(2, "goods_status", "已下架"),

    /**
     * 订单状态
     */
    ORDER_STATUS_0(0, "order_status", "未支付"),
    ORDER_STATUS_1(1, "order_status", "已支付"),
    ORDER_STATUS_2(2, "order_status", "失败"),
    ORDER_STATUS_3(3, "order_status", "退款"),

    ;

    private Integer key;
    private String group;
    private String value;

    /**
     * @methodName：getVlaueByGroup
     * @description：根据key和group获取value
     * @author：tanyp
     * @dateTime：2022/03/29 16:14
     * @Params： [key, group]
     * @Return： java.lang.String
     * @editNote：
     */
    public static String getVlaueByGroup(Integer key, String group) {
        for (Enums item : Enums.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}