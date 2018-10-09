package cn.com.evolver.soubaibei.controller;

import cn.com.evolver.soubaibei.domain.po.Project;
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

    @RequestMapping("/getCoins")
    public List<Project> getProjects(String searchStr){
        return projectService.findByTokenNameLike(searchStr);
    }
}
