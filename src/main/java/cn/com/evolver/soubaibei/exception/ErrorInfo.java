package cn.com.evolver.soubaibei.exception;

import lombok.Getter;
import lombok.Setter;

public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = -100;

    @Setter
    @Getter
    private Integer code;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private String url;

    @Setter
    @Getter
    private T data;
}
