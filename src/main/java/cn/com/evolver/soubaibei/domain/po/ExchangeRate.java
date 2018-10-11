package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_exchange_rate")
@EntityListeners(AuditingEntityListener.class)
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 63)
    private String code;

    @Column(length = 63)
    private String name;

    private Float rate;

    @Column(length = 63)
    private String underlying;

    @Column(length = 15)
    private String type;

    @Column(length = 15)
    private String status;

    @Column(updatable = false)
    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date lastModifiedTime;

}
