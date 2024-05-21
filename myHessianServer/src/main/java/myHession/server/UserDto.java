package myHession.server;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {

    private String id;
    private String name;

    //getter setter toString

}
