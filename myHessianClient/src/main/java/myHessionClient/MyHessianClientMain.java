package myHessionClient;

import myHession.server.UserDto;
import myHession.server.UserServiceFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyHessianClientMain {

    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(MyHessianClientMain.class, args);

        // 調用
        UserServiceFacade userServiceFacade = context.getBean(UserServiceFacade.class);
        UserDto userDto = userServiceFacade.queryUser("11");
        System.out.println(userDto);
    }

}