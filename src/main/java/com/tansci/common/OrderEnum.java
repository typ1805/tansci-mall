package com.tansci.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @path：com.tansci.common.OrderEnum.java
 * @className：OrderEnum.java
 * @description：订单枚举
 * @author：tanyp
 * @dateTime：2022/03/29 16:14
 * @editNote：
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OrderEnum {

    /**
     * 订单状态
     */
    ORDER_STATUS_UNPAID(0, "order_status", "待支付"),
    ORDER_STATUS_PAYMENT(1, "order_status", "已支付"),
    ORDER_STATUS_WAITING(2, "order_status", "配货完成"),
    ORDER_STATUS_ISSUED(3, "order_status", "出库成功"),
    ORDER_STATUS_TRADED(4, "order_status", "交易成功"),
    ORDER_STATUS_USER_CLOSE(5, "order_status", "商家关闭"),
    ORDER_STATUS_OVERTIME_CLOSE(6, "order_status", "超时关闭"),
    ORDER_STATUS_REFUND(7, "order_status", "订单退款"),

    /**
     * 商品订单状态
     */
    GOODS_ORDER_STATUS_UNPAID(0, "goods_order_status", "未支付"),
    GOODS_ORDER_STATUS_TRADED(1, "goods_order_status", "支付成功"),
    GOODS_ORDER_STATUS_FAIL(2, "goods_order_status", "支付失败"),
    GOODS_ORDER_STATUS_REFUND(3, "goods_order_status", "已退款"),

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
        for (OrderEnum item : OrderEnum.values()) {
            if (Objects.equals(key, item.key) && Objects.equals(group, item.group)) {
                return item.getValue();
            }
        }
        return null;
    }

}
