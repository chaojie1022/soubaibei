package cn.com.evolver.soubaibei.listener;

import cn.com.evolver.soubaibei.service.ExchangeRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


@Slf4j
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("...ApplicationReadyEvent...");
        ExchangeRateService exchangeRateService = applicationReadyEvent.getApplicationContext().getBean(ExchangeRateService.class);

    }
}
