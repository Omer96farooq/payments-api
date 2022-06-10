/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay.repository;

import com.task.starzplay.model.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author omer
 */
public interface PaymentMethodRepository extends JpaRepository<PaymentMethods, Long> {
    
}
