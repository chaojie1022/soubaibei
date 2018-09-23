package cn.com.evolver.soubaibei.domain.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_project_category")
public class ProjectCategory {

    @Setter
    @Getter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Setter
    @Getter
    private Integer parentId;

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private Integer level;

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private String status;

    @Setter
    @Getter
    @CreatedDate
    private Date createdTime;

    @Setter
    @Getter
    @LastModifiedDate
    private Date lastModifiedTime;

    @Setter
    @Getter
    private String remark;
}
