package myHessionClient.controller;

import myHession.server.UserDto;
import myHession.server.UserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class TestController {

    @Autowired
    @Qualifier("userService")
    private HessianProxyFactoryBean userService;

    @GetMapping("/1")
    public UserDto test(){
        UserServiceFacade object = (UserServiceFacade)userService.getObject();
        UserDto userDto = object.queryUser("1L");
        return userDto;
    }

}
