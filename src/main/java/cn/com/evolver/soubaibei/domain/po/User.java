package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tb_user")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false,unique = true,length = 63)
    private String username;

    @Column(nullable = false,length = 63)
    private String password;

    @Column(unique = true,length = 63)
    private String email;

    @Column(unique = true,length = 63)
    private String mobileNum;

    @Column(length = 63)
    private String realName;

    @Column(length = 63)
    private String nickName;

    @Column(length = 63)
    private String identityCardNum;

    @Column(length = 15)
    private String type;

    @Column(length = 15)
    private String status;

    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;

    @Column
    private String remark;

}
