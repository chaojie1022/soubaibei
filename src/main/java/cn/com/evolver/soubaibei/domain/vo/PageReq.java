package cn.com.evolver.soubaibei.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class PageReq {

    @Setter
    @Getter
    private Integer pageSize = 10;

    @Setter
    @Getter
    private Integer pageNumber = 0;

    @Setter
    @Getter
    private String sortBy;

    @Setter
    @Getter
    private boolean isSortAsc = false;

    @Getter
    private Sort sort;

    private Pageable pageable;


    public void initPageable(){
        if(StringUtils.isEmpty(sortBy)){
            this.pageable = PageRequest.of(pageNumber,pageSize);
            return;
        }
        this.sort = new Sort(Sort.Direction.DESC,sortBy);
        if(this.isSortAsc){
            this.sort = new Sort(Sort.Direction.ASC,sortBy);
        }
        this.pageable = PageRequest.of(pageNumber,pageSize,this.getSort());
    }

    public PageReq(){

    }
    public PageReq(int pageNumber, int pageSize, String sortBy, boolean isSortAsc){
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.isSortAsc = isSortAsc;
        initPageable();
    }

    public Pageable getPageable(){
        if(null == this.pageable){
            initPageable();
        }
        return this.pageable;
    }
}
