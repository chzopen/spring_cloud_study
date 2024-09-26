package com.chz.myJPA.controller;

import com.chz.myJPA.entity.SysUser;
import com.chz.myJPA.repository.SysUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("/myJPA")
public class TestMyJPAController {

    private static final Logger logger = LoggerFactory.getLogger(TestMyJPAController.class);

    @Autowired
    SysUserRepository sysUserRepository;

    @GetMapping("/test")
    public SysUser test()
    {
        // 创建实体类
        SysUser sysUser = new SysUser();
        sysUser.setId(ThreadLocalRandom.current().nextLong());
        sysUser.setAccount("springdoc");
        sysUser.setEnabled(Boolean.TRUE);
        sysUser.setCreateAt(LocalDateTime.now());

        // 保存实体
        this.sysUserRepository.saveAndFlush(sysUser);

        // 根据自增ID检索实体
        Optional<SysUser> user = this.sysUserRepository.findById(sysUser.getId());

        logger.info("user={}", user.get());
        return user.get();
    }

}