package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_articles")
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private String title;

    @Column(length = 2047)
    private String content;

    @Column(length = 2047)
    private String contentTranslation;

    private Integer forwardCount;

    private Integer replyCount;

    private Integer likeCount;

    /**
     * 类型
     * 1.twitter
     */
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
