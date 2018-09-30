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
     * page：-1,第一次请求 1-~ 分页请求
     * @return a1：token图片地址,a2:代币简写 ,a3：代币全名,b1:基于CNY价格
     * b2:同比昨天百分比涨跌幅 b3:日成交额 b4:众筹价格 b5:发行时间
     * c1:官网网址,c2:代币简写,c3:代币简介 c4:流通市值.c5:项目进度,c6:项目类型
     * c7:推特粉丝数和(7d)增加量 c8:电报成员数和(7d)增加量 c9:活跃用户数和(24h)同比
     * d1:token最近走势
     */
    @RequestMapping(value="/getTokenDetail",method = {RequestMethod.GET})
    @ResponseBody
    public String getTokenDetail(String token,Integer page){
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
     * 大类 0：投资机构  下面子类未知需要工参 （此项多选最多5个 下面都是单选每项最多只有一个）
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
    public String getCoinsDetail(String token,Integer page){
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

    /**
     * 获取30天均价对比信息
     *  参数 token全名  例子 btc全称;btc全称;btc全称  1-~个
     *  点击界面 开始对比按钮 出来的echarts图标就是所需要的格式
     * @return 例 表头 a,a1~a30 数据 btc,50000,50001,....
     * 通过union all select '0','8-1','8-2' 拼接出30天日期,第一位直接为'0' 放在数据的首位
     */
    @RequestMapping(value="/getTokenEcharts",method = {RequestMethod.GET})
    @ResponseBody
    public String getTokenEcharts(String token){
        //当界面返回成功后 直接请求此方法
        //表头为 a,a1-a30 其中a1-a30为最近30天
        //第一行数据为固定的0以及最近30天日期 0,'8-1','8-2','8-3'
        // 通过union all select '0','8-1','8-2' 拼接出这个格式 需要放在数据的首位
        //后面的数据就是对应token名和对应第一行数据日期的每日均价
        String data1="{\"a\":\"0\",\"a1\":\"8-1\",\"a2\":\"8-2\",\"a3\":\"8-3\"\n" +
                ",\"a\":\"btc\",\"a1\":\"50000\",\"a2\":\"51000\",\"a3\":\"49000\"\n" +
                ",\"a\":\"eth\",\"a1\":\"2000\",\"a2\":\"1900\",\"a3\":\"21000\"\n" +
                "}";
        return data1;
    }

    /**
     * 获取各个项目基本面对比信息
     *  参数 token全名  例子 btc全称;btc全称;btc全称  1-~个
     *  点击界面 开始对比按钮 出来的表格就是所需要的格式
     * @return 表头 id ,name1,name2,name3, 其中id是固定的对比信息  name1、2、3是当前这个对比信息在这个token下的值
     * id的值为 'telegram用户数','telegram留言数','telegram活跃用户','telegram用户新增数','telegram活跃用户占比','推特用户数','推特点赞数','推特用户新增数','网站搜索排名','众筹单价-USD','众筹单价-BTC','众筹单价-ETH','代币总量','众筹代币占比','众筹硬顶','众筹完成比例','众筹开始时间','众筹完成时间','测试网络/测试版DAPP是否上线','测试网络/测试版DAPP上线时间','主链/正式版DAPP是否上线','主链/正式版DAPP上线时间','code贡献人数','code问题讨论数','code问题解决数','code改动量','code更新频率','fork次数','star次数','watch次数','核心开发人员人数','核心开发人员1参与项目数','核心开发人员1参与项目被关注数','核心开发人员2参与项目数','核心开发人员2参与项目被关注数','核心开发人员3参与项目数','核心开发人员3参与项目被关注数','代码独创性','投资机构数量','顶级投资机构数量','投资名单','战略合作机构数量','独角兽合作机构数量','合作机构名单','coindesk报道次数','PR次数','推特每周发布数'
     *返回的数据格式 例： 假设有三个对比项的情况下
     * telegram用户数,500,300,400
     * telegram留言数,800,200,100
     * telegram活跃用户,1000,200,300
     */
    @RequestMapping(value="/getTokenBasicTabData",method = {RequestMethod.GET})
    @ResponseBody
    public String getTokenBasicTabData(String token){
        //当界面返回成功后 直接请求此方法
        //表头为 id,name1,name2,name3 只有id是固定的 nameX是1-~个
        //id的值为 'telegram用户数','telegram留言数','telegram活跃用户','telegram用户新增数','telegram活跃用户占比','推特用户数','推特点赞数','推特用户新增数','网站搜索排名','众筹单价-USD','众筹单价-BTC','众筹单价-ETH','代币总量','众筹代币占比','众筹硬顶','众筹完成比例','众筹开始时间','众筹完成时间','测试网络/测试版DAPP是否上线','测试网络/测试版DAPP上线时间','主链/正式版DAPP是否上线','主链/正式版DAPP上线时间','code贡献人数','code问题讨论数','code问题解决数','code改动量','code更新频率','fork次数','star次数','watch次数','核心开发人员人数','核心开发人员1参与项目数','核心开发人员1参与项目被关注数','核心开发人员2参与项目数','核心开发人员2参与项目被关注数','核心开发人员3参与项目数','核心开发人员3参与项目被关注数','代码独创性','投资机构数量','顶级投资机构数量','投资名单','战略合作机构数量','独角兽合作机构数量','合作机构名单','coindesk报道次数','PR次数','推特每周发布数'
        //上面id的值全部都需要展现,样例只取了3个
        String data1="{\"id\":\"telegram用户数\",\"name1\":\"8-1\",\"name2\":\"8-2\",\"name3\":\"8-3\"\n" +
                ",\"id\":\"telegram留言数\",\"name1\":\"50000\",\"name2\":\"51000\",\"name3\":\"49000\"\n" +
                ",\"id\":\"telegram活跃用户\",\"name1\":\"2000\",\"name2\":\"1900\",\"name3\":\"21000\"\n" +
                "}";
        return data1;
    }

    /**
     * 获取各个项目市场表现面对比信息
     *  参数 token全名  例子 btc全称;btc全称;btc全称  1-~个
     *  点击界面 开始对比按钮 出来的表格就是所需要的格式
     * @return 表头 id ,name1,name2,name3, 其中id是固定的对比信息  name1、2、3是当前这个对比信息在这个token下的值
     * id的值为 '众筹价-USD','众筹回报率-USD','众筹回报率-BTC','众筹回报率-ETH','换手率','流通率','流通数量','流通市值','流通市值排名','日变化幅度','周变化幅度','月变化幅度','日成交额','token持仓TOP200占比','token持仓TOP100占比','活跃钱包数','交易所数量','交易所清单'
     *返回的数据格式 例： 假设有三个对比项的情况下
     * 众筹价-USD,500,300,400
     * 众筹回报率-USD,20%,120%,400%
     * 众筹回报率-BTC,20%,120%,400%
     */
    @RequestMapping(value="/getTokenMarketTabData",method = {RequestMethod.GET})
    @ResponseBody
    public String getTokenMarketTabData(String token){
        //当界面返回成功后 直接请求此方法
        //表头为 id,name1,name2,name3 只有id是固定的 nameX是1-~个
        //id的值为 '众筹价-USD','众筹回报率-USD','众筹回报率-BTC','众筹回报率-ETH','换手率','流通率','流通数量','流通市值','流通市值排名','日变化幅度','周变化幅度','月变化幅度','日成交额','token持仓TOP200占比','token持仓TOP100占比','活跃钱包数','交易所数量','交易所清单'
        //上面id的值全部都需要展现,样例只取了3个
        String data1="{\"id\":\"众筹价-USD\",\"name1\":\"500\",\"name2\":\"300\",\"name3\":\"400\"\n" +
                ",\"id\":\"众筹回报率-USD\",\"name1\":\"20%\",\"name2\":\"120%\",\"name3\":\"400%\"\n" +
                ",\"id\":\"众筹回报率-BTC\",\"name1\":\"20%\",\"name2\":\"120%\",\"name3\":\"400%\"\n" +
                "}";
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
