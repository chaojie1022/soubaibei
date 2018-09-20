package cn.com.evolver.soubaibei.dao;

import cn.com.evolver.soubaibei.domain.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 通过类型查找用户列表
     * @param type
     * @return
     */
    List<User> findByType(String type);

    /**
     * 根据状态查找用户列表
     * @param status
     * @return
     */
    List<User> findByStatus(String status);





}
