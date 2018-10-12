package cn.com.evolver.soubaibei.controller;

import cn.com.evolver.soubaibei.domain.po.ExchangeRate;
import cn.com.evolver.soubaibei.domain.po.Project;
import cn.com.evolver.soubaibei.domain.vo.PageReq;
import cn.com.evolver.soubaibei.domain.vo.Result;
import cn.com.evolver.soubaibei.exception.SouBaiBeiException;
import cn.com.evolver.soubaibei.service.ExchangeRateService;
import cn.com.evolver.soubaibei.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "app")
@Slf4j
public class AppsController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ExchangeRateService exchangeRateService;

    @RequestMapping("/")
    public String index(){
        return "app/index";
    }

    @RequestMapping("/getProjects")
    public Result<Page<Project>> getProjects(String searchStr){
        try {
            Result<Page<Project>> result = new Result<>();
            result.setStatus(200);
            result.setMessage("success");
            PageReq pageReq = new PageReq(0, 10, "createdTime", false);
            result.setBody(projectService.findByNameLikeOrCodeLikeOrTokenNameLike(searchStr, pageReq));
            return result;
        }catch (Exception e){
            return SouBaiBeiException.catchProcess(e);
        }
    }

    @RequestMapping("/getExchangeRate")
    public Result<List<ExchangeRate>> getExchangeRate(){
        try {
            Result<List<ExchangeRate>> result = new Result<>();
            result.setStatus(200);
            result.setMessage("success");
            result.setBody(exchangeRateService.findAll());
            return result;
        }catch (Exception e){
            return SouBaiBeiException.catchProcess(e);
        }
    }

}
