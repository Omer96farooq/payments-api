/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay;

import com.task.starzplay.service.PaymentMethodService;
import com.task.starzplay.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author omer
 */
@Component
@Slf4j
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    PaymentMethodService service;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Util.paymentMethodsList = service.getAllPaymentMethods();
        if (Util.paymentMethodsList != null) {
            log.info("PaymentMethods Loaded in JVM");
        } else {
            log.info("Unable to load PaymmentMethods in JVM");
        }
    }

}
