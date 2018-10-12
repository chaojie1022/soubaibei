package cn.com.evolver.soubaibei.service.impl;

import cn.com.evolver.soubaibei.dao.ExchangeRateRepository;
import cn.com.evolver.soubaibei.domain.po.ExchangeRate;
import cn.com.evolver.soubaibei.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> findAll() {
        return exchangeRateRepository.findAll();
    }

}
