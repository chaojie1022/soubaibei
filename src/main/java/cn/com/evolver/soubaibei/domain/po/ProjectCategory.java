package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_project_category")
public class ProjectCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer parentId;

    @Column(length = 63)
    private String code;

    @Column(length = 63)
    private String name;

    private String description;

    private Integer level;

    @Column(length = 15)
    private String type;

    @Column(length = 15)
    private String status;

    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;

    private String remark;
}
