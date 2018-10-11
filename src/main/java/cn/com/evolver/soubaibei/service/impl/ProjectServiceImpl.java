package cn.com.evolver.soubaibei.service.impl;

import cn.com.evolver.soubaibei.dao.ProjectRepository;
import cn.com.evolver.soubaibei.domain.po.Project;
import cn.com.evolver.soubaibei.domain.vo.PageReq;
import cn.com.evolver.soubaibei.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> findByTokenNameLike(String searchStr, PageReq pageReq) {
        return projectRepository.findByTokenNameLike(searchStr, pageReq.getPageable());
    }


    @Override
    public Page<Project> findByNameLikeOrCodeLikeOrTokenNameLike(String searchStr, PageReq pageReq) {
        searchStr = "%"+searchStr+"%";
        return projectRepository.findByNameLikeOrCodeLikeOrTokenNameLike(searchStr,searchStr,searchStr, pageReq.getPageable());
    }
}
