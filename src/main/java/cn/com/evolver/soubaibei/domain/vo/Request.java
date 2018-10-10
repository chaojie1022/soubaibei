package cn.com.evolver.soubaibei.domain.vo;

import lombok.Data;

@Data
public class Request<T> {
    private Integer id;
    private String code;
    private T body;


}
