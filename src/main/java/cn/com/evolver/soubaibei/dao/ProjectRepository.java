package cn.com.evolver.soubaibei.dao;

import cn.com.evolver.soubaibei.domain.po.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {

    @Query(value = "from Project where name like %:searchStr% or code like %:searchStr% or tokenName like %:searchStr%")
    List<Project> findByTokenNameLike(@Param(value = "searchStr") String searchStr, Pageable pageable);

    Page<Project> findByNameLikeOrCodeLikeOrTokenNameLike(String name,String code,String tokenName, Pageable pageable);




}
