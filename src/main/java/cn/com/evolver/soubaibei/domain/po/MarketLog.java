package cn.com.evolver.soubaibei.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_market_log")
@Entity
public class MarketLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long projectId;

    private String name;

    private String code;

    private String tokenName;

    private Long marketCap;

    private Float price;

    private Long circulatingSupply;

    private Date currentTime;

    private Long volume;

    private Float changeForHour;

    private Float changeForDay;

    private Float changeForWeek;

    private String type;

    private String status;

    @CreatedDate
    @Column(updatable = false)
    private Date createdTime;



}
