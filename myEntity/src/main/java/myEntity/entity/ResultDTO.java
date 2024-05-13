package myEntity.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDTO<T> {

    private T data;
    private String code;
    private String msg;

    private String traceId;
    private String ptraceId;

    public ResultDTO(T data) {
        this.data = data;
    }

}
