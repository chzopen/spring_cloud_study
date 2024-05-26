package myHessionClient;

import myHession.server.UserServiceFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessionClientConfig {

    @Bean("userService")
    public HessianProxyFactoryBean userService() {
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl("http://localhost:8080/userService");
        factoryBean.setServiceInterface(UserServiceFacade.class);
        return factoryBean;
    }

}