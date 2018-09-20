package cn.com.evolver.soubaibei.domain.po;

import lombok.Getter;
import lombok.Setter;



import javax.persistence.*;
import java.util.Date;


@Table(name = "tb_user")
@Entity
public class User {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Getter
    @Column(nullable = false,unique = true,length = 63)
    private String username;

    @Setter
    @Getter
    @Column(nullable = false,length = 63)
    private String password;

    @Setter
    @Getter
    @Column(unique = true,length = 63)
    private String email;

    @Setter
    @Getter
    @Column(unique = true,length = 63)
    private String mobileNum;

    @Setter
    @Getter
    @Column(length = 63)
    private String realName;

    @Setter
    @Getter
    @Column(length = 63)
    private String nickName;

    @Setter
    @Getter
    @Column(length = 63)
    private String identityCardNum;

    @Setter
    @Getter
    @Column(length = 15)
    private String type;

    @Setter
    @Getter
    @Column(length = 15)
    private String status;

    @Setter
    @Getter
    @Column(updatable = false)
    private Date createTime;

    @Setter
    @Getter
    @Column
    private Date lastUpdateTime;

    @Setter
    @Getter
    @Column
    private String remark;

}
