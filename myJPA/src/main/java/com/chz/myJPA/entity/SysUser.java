package com.chz.myJPA.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sys_user") // 表名称
public class SysUser {

    // ID
    @Id
    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库自增
    private Long id;

    // 账户
    @Column
    private String account;

    // 是否启用
    @Column
    private Boolean enabled;

    // 创建时间
    @Column
    private LocalDateTime createAt;

    // 省略 get/set/toString 方法
}