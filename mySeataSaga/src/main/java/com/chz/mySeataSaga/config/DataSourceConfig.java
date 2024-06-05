package com.chz.mySeataSaga.config;

import io.seata.saga.engine.StateMachineConfig;
import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.engine.config.DbStateMachineConfig;
import io.seata.saga.engine.impl.ProcessCtrlStateMachineEngine;
import io.seata.saga.rm.StateMachineEngineHolder;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "dispose")
    public JdbcConnectionPool dataSource()
    {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:seata_saga");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return JdbcConnectionPool.create(dataSource);
    }

    @Bean
    public ProcessCtrlStateMachineEngine stateMachineEngine(StateMachineConfig dbStateMachineConfig)
    {
        ProcessCtrlStateMachineEngine stateMachineEngine = new ProcessCtrlStateMachineEngine();
        stateMachineEngine.setStateMachineConfig(dbStateMachineConfig);
        return stateMachineEngine;
    }

    @Bean
    public DbStateMachineConfig dbStateMachineConfig(JdbcConnectionPool dataSource, ThreadPoolExecutor threadExecutor)
    {
        DbStateMachineConfig dbStateMachineConfig = new DbStateMachineConfig();
        dbStateMachineConfig.setDataSource(dataSource);
        dbStateMachineConfig.setResources(new String[]{"statelang/*.json"});
        dbStateMachineConfig.setEnableAsync(true);
        dbStateMachineConfig.setThreadPoolExecutor(threadExecutor);
        dbStateMachineConfig.setApplicationId("saga_sample");
        dbStateMachineConfig.setTxServiceGroup("my_test_tx_group");
        return dbStateMachineConfig;
    }

    @Bean
    public ThreadPoolExecutor threadExecutor()
    {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        return threadPoolExecutor;
    }

    @Bean
    public StateMachineEngineHolder stateMachineEngineHolder(ProcessCtrlStateMachineEngine stateMachineEngine)
    {
        StateMachineEngineHolder stateMachineEngineHolder = new StateMachineEngineHolder();
        stateMachineEngineHolder.setStateMachineEngine(stateMachineEngine);
        return stateMachineEngineHolder;
    }

}
