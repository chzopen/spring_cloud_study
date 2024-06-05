package com.chz.myShardingSphere.persistence.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {

    private Long id;
    private Long orderType;
    private Long customerId;
    private Double amount;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", customerId=" + customerId +
                ", amount=" + amount +
                '}';
    }

}