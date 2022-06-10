/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay.service.impl;

import com.task.starzplay.model.PaymentMethods;
import com.task.starzplay.repository.PaymentMethodRepository;
import com.task.starzplay.service.PaymentMethodService;
import com.task.starzplay.util.Util;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author omer
 */
@Service
@Slf4j
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    PaymentMethodRepository repository;

    @Override
    public List<PaymentMethods> getAllPaymentMethods() {
        return repository.findAll();
    }

    @Override
    public PaymentMethods getPaymentMethodsById(int id) {
        if (Util.paymentMethodsList != null && Util.paymentMethodsList.size() > 0) {
            List<PaymentMethods> paymentMethodList = Util.paymentMethodsList.parallelStream()
                    .filter(e -> e.getId() == id)
                    .collect(Collectors.toList());
            if (paymentMethodList != null && paymentMethodList.size() > 0) {
                return paymentMethodList.get(0);
            }
        } else {
            log.warn("getPaymentMethodsById() NO Payment FOUND IN JVM. PLEASE RELOAD JVM");
        }
        return null;
    }

    @Override
    public PaymentMethods getPaymentMethodsByName(String paymentName) {
        if (Util.paymentMethodsList != null && Util.paymentMethodsList.size() > 0) {
            List<PaymentMethods> paymentMethodList = Util.paymentMethodsList.parallelStream()
                    .filter(e -> e.getName().equalsIgnoreCase(paymentName))
                    .collect(Collectors.toList());
            if (paymentMethodList != null && paymentMethodList.size() > 0) {
                return paymentMethodList.get(0);
            }
        } else {
            log.warn("getPaymentMethodsByName() NO Payment FOUND IN JVM. PLEASE RELOAD JVM");
        }
        return null;
    }

    @Override
    public boolean addNewPaymentMethod(PaymentMethods pm) {

        pm = repository.save(pm);
        return pm != null;
    }

    @Override
    public boolean isPaymentMethodExists(Long id) {
        if(repository.existsById(id)){
            return true;
        }
        else return false;
    }

}
