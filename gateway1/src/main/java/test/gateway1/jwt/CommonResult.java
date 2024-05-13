package test.gateway1.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T      data;
    private int dataSize;
 
    public CommonResult(Integer code,String message){
        this(code,message,null,0);
    }
    public CommonResult(Integer code,String message,T data){
        this(code,message,data,0);
    }
 
}