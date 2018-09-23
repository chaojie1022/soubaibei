package cn.com.evolver.soubaibei.domain.po;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_project")
public class Project {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目编码
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String code;

    /**
     * 项目名称
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String name;

    /**
     * 可以是项目标题,slogan,或者中文名
     */
    @Setter
    @Getter
    private String title;

    /**
     * 代币名称
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String tokenName;

    /**
     * 代币类型
     */
    @Setter
    @Getter
    @Column(length = 15)
    private String tokenType;

    /**
     * 描述
     */
    @Setter
    @Getter
    private String description;

    /**
     * ico募集美元资金
     */
    @Setter
    @Getter
    private Long icoDollar;

    /**
     * ico日期
     */
    @Setter
    @Getter
    @Temporal(TemporalType.DATE)
    private Date icoDate;

    /**
     * icoToken数量
     */
    @Setter
    @Getter
    private Long icoTokensCount;

    /**
     * ico完成度
     */
    @Setter
    @Getter
    private Float icoCompletePercent;

    /**
     * ico占比
     */
    @Setter
    @Getter
    private Float icoTokensPercent;

    /**
     * 流通比例
     */
    @Setter
    @Getter
    private Float availableTokenPercent;

    /**
     * 总Token数量
     */
    @Setter
    @Getter
    private Long totalTokensCount;


    /**
     * telegram账号
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String telegramAccount;

    /**
     * telegram关注人数
     */
    @Setter
    @Getter
    private Integer telegramCount;

    /**
     * 推特账号
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String twitterAccount;

    /**
     * 推特关注人数
     */
    @Setter
    @Getter
    private Integer twitterFollowCount;

    /**
     * 脸书账号
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String facebookAccount;

    /**
     * 脸书关注人数
     */
    @Setter
    @Getter
    private Integer facebookFollowCount;

    /**
     * reddit账号
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String redditAccount;

    /**
     * reddit关注人数
     */
    @Setter
    @Getter
    private Integer redditFollowCount;

    /**
     * github账号
     */
    @Setter
    @Getter
    @Column(length = 63)
    private String githubAccount;

    /**
     * github Watch 数量
     */
    @Setter
    @Getter
    private Integer githubWatch;

    /**
     * github Star 数量
     */
    @Setter
    @Getter
    private Integer githubStar;

    /**
     * github fork 数量
     */
    @Setter
    @Getter
    private Integer githubFork;

    /**
     * 项目类型
     */
    @Setter
    @Getter
    @Column(length = 15)
    private String type;

    /**
     * 项目状态
     */
    @Setter
    @Getter
    @Column(length = 15)
    private String status;

    /**
     * 备注
     */
    @Setter
    @Getter
    private String remark;

    /**
     * 创建时间
     */
    @Setter
    @Getter
    @CreatedDate
    private Date createdTime;

    /**
     * 最后修改时间
     */
    @Setter
    @Getter
    @LastModifiedDate
    private Date lastModifiedTime;

}
