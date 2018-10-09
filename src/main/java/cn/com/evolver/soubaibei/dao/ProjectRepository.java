package cn.com.evolver.soubaibei.dao;

import cn.com.evolver.soubaibei.domain.po.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query(value = "from Project where tokenName like %:searchStr%")
    List<Project> findByTokenNameLike(@Param(value = "searchStr") String searchStr);

}
