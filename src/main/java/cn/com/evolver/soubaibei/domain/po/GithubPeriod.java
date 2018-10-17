package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_github_period")
public class GithubPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long githubId;

    private String name;

    private String code;

    private Long href;

    private Integer contributorsCount;

    private Integer issuesOpenCount;

    private Integer issuesClosedCount;

    private Integer issuesSolvedCount;

    private Integer pullRequestOpenCount;

    private Integer pullRequestClosedCount;

    private Integer pullRequestSolvedCount;

    private Integer starsCount;

    private Integer openedCount;

    private Integer forkedCount;

    private Integer watchedCount;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;
}
