package cn.com.evolver.soubaibei.domain.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Price {

    private Float usdPrice;
    private String currency;
    private Float rate;
    private Float underlyingPrice;


    public Price(Float usdPrice,String currency,Float rate){
        this.usdPrice = usdPrice;
        this.currency = currency;
        this.rate = rate;
        this.underlyingPrice = usdPrice;
    }

    public float getUnderlyingPrice(float usdPrice,float rate){
        return 0;
    }
}
