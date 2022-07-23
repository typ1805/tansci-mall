package com.tansci.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @path：com.tansci.common.PayEnum.java
 * @className：PayEnum.java
 * @description：支付枚举
 * @author：tanyp
 * @dateTime：2022/03/29 16:14
 * @editNote：
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PayEnum {

    /**
     * 支付状态
     */
    PAY_STATUS_UNPAID(0, "pay_status", "未支付"),
    PAY_STATUS_SUCCESS(1, "pay_status", "支付成功"),
    PAY_STATUS_FAIL(2, "pay_status", "支付失败"),

    /**
     * 支付类型
     */
    PAY_TYPE_ALI(1, "pay_type", "支付宝"),
    PAY_TYPE_WECHAT(2, "pay_type", "微信"),
    PAY_TYPE_UNION(3, "pay_type", "银联"),

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
        for (PayEnum item : PayEnum.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}
