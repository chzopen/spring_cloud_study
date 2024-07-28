package com.chz.myShardingSphere.persistence.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersProduct {

    private Long id;
    private Long customerId;
    private Long orderId;
    private Long orderType;
    private Long productId;

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderId=" + orderId +
                ", orderType=" + orderType +
                ", productId=" + productId +
                '}';
    }

}