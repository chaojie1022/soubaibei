package cn.com.evolver.soubaibei.service;

import cn.com.evolver.soubaibei.domain.po.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {

    /**
     * 获取用户列表
     * @return
     */
    List<User> getUserList();

    /**
     * 通过id查找到用户
     * @param id
     * @return
     */
    User findUserById(long id);

    /**
     * 新增一个用户
     * @param user
     */
    void save(User user);

    /**
     * 编辑用户
     * @param user
     */
    void edit(User user);

    /**
     * 根据用户名删除用户
     * @param name
     */
    void deleteByUsername(String name);

    /**
     * 根据条件查询
     * @return
     */
    List<User> findAll(Example<User> userExample);

    List<User> findAll(Specification<User> userSpecification);

    /**
     * 根据实体类固定条件查询
     * @param user
     * @return
     */
    List<User> findAll(User user);

    /**
     * 根据Id删除用户
     * @param id
     */
    void deleteById(Long id);


}
