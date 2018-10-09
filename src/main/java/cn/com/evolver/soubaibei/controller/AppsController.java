package cn.com.evolver.soubaibei.controller;

import cn.com.evolver.soubaibei.domain.po.Project;
import cn.com.evolver.soubaibei.domain.vo.Result;
import cn.com.evolver.soubaibei.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "app")
@Slf4j
public class AppsController {

    @Autowired
    ProjectService projectService;

    @RequestMapping("/")
    public String index(){
        return "app/index";
    }

    @RequestMapping("/getProjects")
    public Result<List<Project>> getProjects(String searchStr){
        Result<List<Project>> result = new Result<>();
        result.setStatus(200);
        result.setMessage("success");
        result.setT(projectService.findByTokenNameLike(searchStr));
        return result;
    }




}