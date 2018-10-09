package cn.com.evolver.soubaibei.domain.vo;

import lombok.Getter;
import org.springframework.data.domain.Sort;


@Getter
public class Page {

    private Integer pageSize;

    private Integer pageNumber;

    private Sort sort;

    public Page(int pageSize,int pageNumber,String sortBy, boolean isSortAsc){
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        if(isSortAsc){
            this.sort = new Sort(Sort.Direction.DESC,sortBy);
            return;
        }
        this.sort = new Sort(Sort.Direction.ASC,sortBy);

    }
}
