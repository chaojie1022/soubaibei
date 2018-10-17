package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_category_project_rel")
@EntityListeners(AuditingEntityListener.class)
public class CategoryProjectRel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long projectId;

    @Column(length = 15)
    private String status;

    private String remark;

    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;

}
