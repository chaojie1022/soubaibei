package cn.com.evolver.soubaibei.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public class Page {

    @Setter
    @Getter
    private Integer pageSize = 10;

    @Setter
    @Getter
    private Integer pageNumber = 1;

    @Setter
    @Getter
    private String sortBy;

    @Setter
    @Getter
    private boolean isSortAsc = false;

    @Getter
    private Sort sort = new Sort(Sort.Direction.DESC);

    private Pageable pageable;


    public void initPageable(){
        if(StringUtils.isEmpty(sortBy)){
            this.pageable = PageRequest.of(pageNumber-1,pageSize);
        }
        if(isSortAsc){
            this.sort = new Sort(Sort.Direction.ASC,sortBy);
        }
        this.pageable = PageRequest.of(pageNumber-1,pageSize,this.getSort());
    }

    public Page(){

    }
    public Page(int pageNumber,int pageSize,String sortBy, boolean isSortAsc){
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        if(isSortAsc){
            this.sort = new Sort(Sort.Direction.ASC,sortBy);
        }
        initPageable();
    }

    public Pageable getPageable(){
        if(null == this.pageable){
            initPageable();
        }
        return this.pageable;
    }
}
