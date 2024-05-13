package test.application1.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "chz_user")
public class User {
    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String email;
}