package myHession.server;

import org.springframework.stereotype.Service;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {

    public UserDto queryUser(String userId) {
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        userDto.setName("N-"+userId);
        return userDto;
    }

}