package cn.com.evolver.soubaibei.service;

import cn.com.evolver.soubaibei.domain.po.Project;
import cn.com.evolver.soubaibei.domain.vo.Page;

import java.util.List;

public interface ProjectService {

    List<Project> findByTokenNameLike(String searchStr,Page page);


}
