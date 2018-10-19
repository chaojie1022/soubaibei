package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_github")
@EntityListeners(AuditingEntityListener.class)
public class Github {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private Long projectId;

    private String href;

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

    @Column(length = 15)
    private String type;

    @Column(length = 15)
    private String status;

    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;


}
