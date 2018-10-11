package cn.com.evolver.soubaibei.domain.vo;

import lombok.Getter;
import lombok.Setter;

public class Result<T> {

    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_Exception = 500;
    public static final int NOT_FOUND = 404;


    @Setter
    @Getter
    private int status;

    @Setter
    @Getter
    private T body;

    @Setter
    @Getter
    private String message;

}
