/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay.service;

import com.task.starzplay.model.PaymentMethods;
import java.util.List;

/**
 *
 * @author omer
 */
public interface PaymentMethodService {

    public List<PaymentMethods> getAllPaymentMethods();

    public PaymentMethods getPaymentMethodsById(int id);

    public PaymentMethods getPaymentMethodsByName(String paymentName);

    public boolean addNewPaymentMethod(PaymentMethods pm);
    
    public boolean isPaymentMethodExists(Long id);
}
