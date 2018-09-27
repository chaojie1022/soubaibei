package cn.com.evolver.soubaibei.domain.po;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_project")
public class Project {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目编码
     */
    @Column(length = 63)
    private String code;

    /**
     * 项目名称
     */
    @Column(length = 63)
    private String name;

    /**
     * 可以是项目标题,slogan,或者中文名
     */
    private String title;

    /**
     * 代币名称
     */
    @Column(length = 63)
    private String tokenName;

    /**
     * 代币类型
     */
    @Column(length = 15)
    private String tokenType;

    /**
     * 描述
     */
    private String description;

    /**
     * ico募集美元资金
     */
    private Long icoDollar;

    /**
     * ico日期
     */
    @Temporal(TemporalType.DATE)
    private Date icoDate;

    /**
     * icoToken数量
     */
    private Long icoTokensCount;

    /**
     * ico完成度
     */
    private Float icoCompletePercent;

    /**
     * ico占比
     */
    private Float icoTokensPercent;

    /**
     * 流通比例
     */
    private Float availableTokenPercent;

    /**
     * 总Token数量
     */
    private Long totalTokensCount;


    /**
     * telegram账号
     */
    @Column(length = 63)
    private String telegramAccount;

    /**
     * telegram关注人数
     */
    private Integer telegramCount;

    /**
     * 推特账号
     */
    @Column(length = 63)
    private String twitterAccount;

    /**
     * 推特关注人数
     */
    private Integer twitterFollowCount;

    /**
     * 脸书账号
     */
    @Column(length = 63)
    private String facebookAccount;

    /**
     * 脸书关注人数
     */
    private Integer facebookFollowCount;

    /**
     * reddit账号
     */
    @Column(length = 63)
    private String redditAccount;

    /**
     * reddit关注人数
     */
    private Integer redditFollowCount;

    /**
     * github账号
     */
    @Column(length = 63)
    private String githubAccount;

    /**
     * github Watch 数量
     */
    private Integer githubWatch;

    /**
     * github Star 数量
     */
    private Integer githubStar;

    /**
     * github fork 数量
     */
    private Integer githubFork;

    /**
     * 项目类型
     */
    @Column(length = 15)
    private String type;

    /**
     * 项目状态
     */
    @Column(length = 15)
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;

    /**
     * 最后修改时间
     */
    @LastModifiedDate
    private Date lastModifiedTime;

}
