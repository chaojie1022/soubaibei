package cn.com.evolver.soubaibei.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

   /* @RequestMapping("/")
    public String index(){
        return "index";
    }*/

    @RequestMapping("/e")
    public String exceptionTest()throws Exception{
        throw new Exception("普通异常");
    }
}