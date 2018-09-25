package cn.com.evolver.soubaibei.domain.po;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_category_project_rel")
public class CategoryProjectRel {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private Long categoryId;

    @Setter
    @Getter
    @Column(nullable = false)
    private Long projectId;

    @Setter
    @Getter
    @Column(length = 15)
    private String status;

    @Setter
    @Getter
    private String remark;

    @Setter
    @Getter
    @CreatedDate
    private Date createdTime;

    @Setter
    @Getter
    @LastModifiedDate
    private Date lastModifiedTime;

}
