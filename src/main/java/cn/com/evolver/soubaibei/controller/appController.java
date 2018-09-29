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

    /**
     * 通过输入的coins信息模糊查出对应的币种全名,最多返回10行
     * @return
     */
    @RequestMapping(value="/getCoins",method = {RequestMethod.GET})
    @ResponseBody
    public String getCoins(String coins){

        // 获取币种信息 label 是币种名称
        //category 为不同类型做后续扩展 当前可以全部为''
        String data1="[{\"label\":\"anders(eos)"+coins+"\",\"category\":\"\"},{\"label\":\"andreas(btc)\",\"category\":\"\"},{\"label\":\"antal\",\"category\":\"\"},{\"label\":\"anders andersson\",\"category\":\"People\"}]";
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
      return data1;
    }

    /**
     * 通过搜索框输入的token名 查找token信息,需要适配模糊查询,超过10个需要分页
     * @return a1：token图片地址,a2:代币简写 ,a3：代币全名,b1:基于CNY价格
     * b2:同比昨天百分比涨跌幅 b3:日成交额 b4:众筹价格 b5:发行时间
     * c1:官网网址,c2:代币简写,c3:代币简介 c4:流通市值.c5:项目进度,c6:项目类型
     * c7:推特粉丝数和(7d)增加量 c8:电报成员数和(7d)增加量 c9:活跃用户数和(24h)同比
     * d1:token最近走势
     */
    @RequestMapping(value="/getTokenDetail",method = {RequestMethod.GET})
    @ResponseBody
    public String getTokenDetail(String token){
        //当界面返回成功后 直接请求此方法
        //category 为不同类型做后续扩展 当前可以全部为''
        String data1="{\"totalCount\":\"10\",\"token\":[{\"a1\":\"~/../image/102.jpeg\",\"a2\":\"EOS1\",\"a3\":\"eos1\",\"b1\":\"47.51\",\"b2\":\"5.4\",\"b3\":\"53亿\",\"b4\":\"7\",\"b5\":\"2017-07-02\""+
                ",\"c1\":\"https://eos.io/\",\"c2\":\"EOS\",\"c3\":\" IO is software that introduces a blockchain architecture designed to enable vertical....\""+
                ",\"c4\":\"425亿\",\"c5\":\"主网上线\",\"c6\":\"智能合约\",\"c7\":\"5653(+7)\",\"c8\":\"13952/+231\",\"c9\":\"422/2.4%\""+
                ",\"d1\":\"220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227\"}"+
                ",{\"a1\":\"~/../image/102.jpeg\",\"a2\":\"EOS2\",\"a3\":\"eos1\",\"b1\":\"47.51\",\"b2\":\"5.4\",\"b3\":\"53亿\",\"b4\":\"7\",\"b5\":\"2017-07-02\""+
                ",\"c1\":\"https://eos.io/\",\"c2\":\"EOS\",\"c3\":\" IO is software that introduces a blockchain architecture designed to enable vertical....\""+
                ",\"c4\":\"425亿\",\"c5\":\"主网上线\",\"c6\":\"智能合约\",\"c7\":\"5653(+7)\",\"c8\":\"13952/+231\",\"c9\":\"422/2.4%\""+
                ",\"d1\":\"220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227\"}"+
                "]}";
        return data1;
    }
    /**
     * 通过查找条件 查找token信息,需要适配模糊查询,超过10个需要分页
     * 条件不固定需要判断
     * 大类 0：投资机构  下面子类未知需要工参
     * 大类1：类型       子类：0 智能合约 1 资产交易 2 操作系统 3 银行结算 4 社交通讯 5 物联网 6 文件存储 7 其它
     * 大类2：流通市值   子类：0 TOP100 1 100~200 2 200~500 3 500以后  4 自定义 例100~200
     * 大类3：回报率     子类 0 >100 1 10~100 2 5~10 3 1~5  4 0.5~1 5  0.1~0.5 6  <0.1  7 自定义 例100~200
     * 大类4：近期走势   子类 0 <-50% 1 -50%~-30% 2 -30%~-10% 3 -10%~10%  4 10%~30% 5 30%~50% 6 >50%
     * 大类5：国家      子类 0 中国 1 美国 2 日本 3 韩国 4 欧洲 5 其它
     * page：-1,第一次请求 1-~ 分页请求
     * @return a1：token图片地址,a2:代币简写 ,a3：代币全名,b1:基于CNY价格
     * b2:同比昨天百分比涨跌幅 b3:日成交额 b4:众筹价格 b5:发行时间
     * c1:官网网址,c2:代币简写,c3:代币简介 c4:流通市值.c5:项目进度,c6:项目类型
     * c7:推特粉丝数和(7d)增加量 c8:电报成员数和(7d)增加量 c9:活跃用户数和(24h)同比
     * d1:token最近走势
     */
    @RequestMapping(value="/getCoinsDetail",method = {RequestMethod.GET})
    @ResponseBody
    public String getCoinsDetail(String token){
        //当界面返回成功后 直接请求此方法
        //category 为不同类型做后续扩展 当前可以全部为''
        String data1="{\"totalCount\":\"10\",\"token\":[{\"a1\":\"~/../image/102.jpeg\",\"a2\":\"EOS1\",\"a3\":\"eos1\",\"b1\":\"47.51\",\"b2\":\"5.4\",\"b3\":\"53亿\",\"b4\":\"7\",\"b5\":\"2017-07-02\""+
                ",\"c1\":\"https://eos.io/\",\"c2\":\"EOS\",\"c3\":\" IO is software that introduces a blockchain architecture designed to enable vertical....\""+
                ",\"c4\":\"425亿\",\"c5\":\"主网上线\",\"c6\":\"智能合约\",\"c7\":\"5653(+7)\",\"c8\":\"13952/+231\",\"c9\":\"422/2.4%\""+
                ",\"d1\":\"220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227\"}"+
                ",{\"a1\":\"~/../image/102.jpeg\",\"a2\":\"EOS2\",\"a3\":\"eos1\",\"b1\":\"47.51\",\"b2\":\"5.4\",\"b3\":\"53亿\",\"b4\":\"7\",\"b5\":\"2017-07-02\""+
                ",\"c1\":\"https://eos.io/\",\"c2\":\"EOS\",\"c3\":\" IO is software that introduces a blockchain architecture designed to enable vertical....\""+
                ",\"c4\":\"425亿\",\"c5\":\"主网上线\",\"c6\":\"智能合约\",\"c7\":\"5653(+7)\",\"c8\":\"13952/+231\",\"c9\":\"422/2.4%\""+
                ",\"d1\":\"220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227\"}"+
                "]}";
        return data1;
    }
    /**
     * 获取界面顶层btc、eth、eos价格
     * @return
     */
    @RequestMapping(value="/getTokenCNY",method = {RequestMethod.GET})
    @ResponseBody
    public String getTokenCNY(){
        //当界面返回成功后 直接请求此方法
        //返回btc、eth、eos当前基于cny价格和同比前一天涨跌↑ ↓
        String data1="{\"btc\":\"44074.05↑\",\"eth\":\"3154.44↑\",\"eos\":\"58.91↑\"}";
        return data1;
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
        }
     */
}
