package myHession.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class HessionServerConfig {

    @Autowired
    private UserServiceFacade userServiceFacade;

    @Bean("/userService")
    public HessianServiceExporter userService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userServiceFacade);
        exporter.setServiceInterface(UserServiceFacade.class);
        return exporter;
    }

}