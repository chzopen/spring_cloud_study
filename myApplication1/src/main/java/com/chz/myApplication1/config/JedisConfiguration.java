//package test.config;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//public class JedisConfiguration {
//
//    @Bean
//    public JedisSentinelPool jedisSentinelPool()
//    {
//        Set<String> sentinels = new HashSet<>();
//        sentinels.add("192.168.44.228:26401");
//        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//        poolConfig.setJmxEnabled(false);
//        poolConfig.setMaxIdle(5);
//        poolConfig.setMaxTotal(10);
//        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels, poolConfig); // redis连接池
//        return jedisSentinelPool;
//    }
//
//    @Bean
//    public JedisCluster jedisCluster()
//    {
//        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7000));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7001));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7002));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7003));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7004));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7005));
//
//        GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
//        poolConfig.setJmxEnabled(false);
//        poolConfig.setMaxIdle(5);
//        poolConfig.setMaxTotal(10);
//
//        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, poolConfig);
//        return jedisCluster;
//    }
//
//    public static void main(String[] args) {
//        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7000));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7001));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7002));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7003));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7004));
//        jedisClusterNode.add(new HostAndPort("192.168.44.229", 7005));
//
//        GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
//        poolConfig.setJmxEnabled(false);
//        poolConfig.setMaxIdle(5);
//        poolConfig.setMaxTotal(10);
//
//        JedisCluster jedisCluster = new JedisCluster(jedisClusterNode, poolConfig);
//        String a = jedisCluster.get("a");
//        System.out.println(a);
//
//    }
//
//
//}
