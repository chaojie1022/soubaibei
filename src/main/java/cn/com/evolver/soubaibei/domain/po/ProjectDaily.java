package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "tb_project_daily")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ProjectDaily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private Date checkedDate;

    private Float googleRelativeValue;

    private Float projectCompleteRange;

    private Float codeCompleteRange;

    private Integer codeUpdateCount;

    private Integer codeContributorCount;

    private Integer telegramFollowCount;

    private Integer telegramMessageCount;

    private Integer starsCount;

    private Integer forkedCount;

    private Integer watchedCount;

    private Integer twitterCount;

    private Integer twitterForwardCount;

    private Integer twitterReplyCount;

    private String type;

    private String status;

    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;



}
