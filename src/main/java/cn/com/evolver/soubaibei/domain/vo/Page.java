package cn.com.evolver.soubaibei.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Page {

    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private boolean isSortAsc = false;



}
