package cn.com.evolver.soubaibei.controller;

import cn.com.evolver.soubaibei.domain.po.Project;
import cn.com.evolver.soubaibei.domain.vo.PageReq;
import cn.com.evolver.soubaibei.domain.vo.Result;
import cn.com.evolver.soubaibei.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<Page<Project>> getProjects(String searchStr){
        try {
            Result<Page<Project>> result = new Result<>();
            result.setStatus(200);
            result.setMessage("success");
            PageReq pageReq = new PageReq(1, 1, "createdTime", false);
            result.setBody(projectService.findByNameLikeOrCodeLikeOrTokenNameLike(searchStr, pageReq));
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
