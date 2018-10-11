package cn.com.evolver.soubaibei.service;

import cn.com.evolver.soubaibei.domain.po.Project;
import cn.com.evolver.soubaibei.domain.vo.PageReq;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {

    List<Project> findByTokenNameLike(String searchStr,PageReq pageReq);

    Page<Project> findByNameLikeOrCodeLikeOrTokenNameLike(String searchStr, PageReq pageReq);

}
