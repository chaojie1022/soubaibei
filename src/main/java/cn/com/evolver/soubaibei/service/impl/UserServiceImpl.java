package cn.com.evolver.soubaibei.service.impl;

import cn.com.evolver.soubaibei.dao.UserRepository;
import cn.com.evolver.soubaibei.domain.po.User;
import cn.com.evolver.soubaibei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void save(User user) {
        user.setCreateTime(new Date());
        user.setLastUpdateTime(new Date());
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
        user.setLastUpdateTime(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username);
        this.deleteById(user.getId());
    }

    @Override
    public List<User> findAll(Example<User> userExample) {
        userExample = new Example<User>() {
            @Override
            public User getProbe() {
                return null;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return null;
            }
        };
        return null;
    }

    @Override
    public List<User> findAll(Specification<User> userSpecification) {
        return null;
    }

    @Override
    public List<User> findAll(User user) {
        if(null==user){
            return userRepository.findAll();
        }
        Example<User> userExample = Example.of(user);
        List<User> users = userRepository.findAll(userExample);
        return users;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
