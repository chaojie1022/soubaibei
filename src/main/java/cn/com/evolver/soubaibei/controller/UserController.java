package cn.com.evolver.soubaibei.controller;

import cn.com.evolver.soubaibei.domain.po.User;
import cn.com.evolver.soubaibei.domain.vo.Result;
import cn.com.evolver.soubaibei.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Slf4j
@Controller
@RequestMapping(value="/users")
public class UserController {

    /**
     * 注解@Resource的作用相当于@Autowired，
     * 只不过@Autowired按byType自动注入，
     * 而@Resource默认按 byName自动注入
     */
    @Autowired
    UserService userService;


    @RequestMapping("/")
    public String index(){
        return "redirect:/users/list";
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<User> users = userService.getUserList();
        model.addAttribute("users",users);
        return "users/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "users/add";
    }

    @RequestMapping("/add")
    public String add(User user){
        userService.save(user);
        return "redirect:/users/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id){
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "users/edit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        userService.edit(user);
        return "redirect:/users/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        userService.deleteById(id);
        return "redirect:/users/list";
    }


    @RequestMapping("/restList")
    @ResponseBody
    public Result<List<User>> list(User user){
        List<User> users = userService.findAll(user);
        Result<List<User>> result = new Result<>();
        result.setStatus(Result.STATUS_SUCCESS);
        result.setMessage(String.valueOf(users==null?0:users.size())+"users");
        result.setT(users);
        return result;
    }

    @RequestMapping("/toRest")
    public String toRest(){
        return "/users/rest";
    }

}
