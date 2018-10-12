package cn.com.evolver.soubaibei.exception;

import cn.com.evolver.soubaibei.domain.vo.Result;

public class SouBaiBeiException extends Exception {

    public SouBaiBeiException(String message){
        super(message);
    }

    public static Result catchProcess(Exception e ){
        Result result = new Result();
        result.setMessage(e.getMessage());
        result.setStatus(500);
        e.printStackTrace();
        return result;
    }


}
