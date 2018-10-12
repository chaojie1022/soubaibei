package cn.com.evolver.soubaibei.dao;

import cn.com.evolver.soubaibei.domain.po.ExchangeRate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@CacheConfig(cacheNames = "exchangeRates")
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,Integer> {

    @Cacheable
    List<ExchangeRate> findAll();
}
