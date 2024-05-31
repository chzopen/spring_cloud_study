package com.chz.myApplication1.persistence.po;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Orders {

    private Integer id;
    private Integer orderType;
    private Integer customerId;
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