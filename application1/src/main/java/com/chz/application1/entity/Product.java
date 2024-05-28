package com.chz.application1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor()
@AllArgsConstructor
@Getter
@Setter
public class Product {

    private String sku;
    private String name;
    private Double amount;


}
