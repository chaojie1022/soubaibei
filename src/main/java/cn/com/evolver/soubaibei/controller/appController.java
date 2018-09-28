package cn.com.evolver.soubaibei.controller;

/*import cn.com.evolver.soubaibei.domain.po.User;
import cn.com.evolver.soubaibei.domain.vo.Result;
import cn.com.evolver.soubaibei.service.UserService;*/

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Slf4j
@Controller
public class appController {

    /**
     * 注解@Resource的作用相当于@Autowired，
     * 只不过@Autowired按byType自动注入，
     * 而@Resource默认按 byName自动注入
     */



    @RequestMapping("/")
    public String index(){
        return "/app/index";
    }
    @RequestMapping(value="/getCoins",method = {RequestMethod.GET})
    @ResponseBody
    public String getCoins(){
        //当界面返回成功后 直接请求此方法 获取全部的币种信息 label 是币种名称
        //category 为不同类型做后续扩展 当前可以全部为''
        String data = "["+
        "{ label: 'anders', category: '' },"+
        "{ label: 'andreas', category:'' },"+
        "{ label: 'antal', category: '' },"+
        "{ label: 'annhhx10', category: 'Products' },"+
        "{ label: 'annk K12', category: 'Products' },"+
        "{ label: 'annttop C13', category: 'Products' },"+
        "{ label: 'anders andersson', category: 'People' },"+
        "{ label: 'andreas andersson', category: 'People' },"+
        "{ label: 'andreas johnson', category: 'People' }"+
    "]";
      return data;
    }

    /*@RequestMapping("/restList")
    @ResponseBody
    public Result<List<User>> list(User user){
        List<User> users = userService.findAll(user);
        Result<List<User>> result = new Result<>();
        result.setStatus(Result.STATUS_SUCCESS);
        result.setMessage(String.valueOf(users==null?0:users.size())+"users");
        result.setT(users);
        return result;
    }*/
}
