/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay.controller;

import com.task.starzplay.model.PaymentMethods;
import com.task.starzplay.model.response.ErrorCode;
import com.task.starzplay.model.response.Response;
import com.task.starzplay.service.PaymentMethodService;
import com.task.starzplay.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author omer
 */
@Slf4j
@RestController
@RequestMapping("api/v1.0/configuration/payment-methods")
public class PaymentMethodsController {

    @Autowired
    PaymentMethodService service;

//    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping
    public Response getAllPaymentMethods() {

        if (Util.paymentMethodsList != null && Util.paymentMethodsList.size() > 0) {
            return new Response(ErrorCode.SUCCESS.getValue(), "SUCCESS", Util.paymentMethodsList);
        } else {
            Util.paymentMethodsList = service.getAllPaymentMethods();
            if (Util.paymentMethodsList != null && Util.paymentMethodsList.size() > 0) {
                return new Response(ErrorCode.SUCCESS.getValue(), "SUCCESS", Util.paymentMethodsList);
            } else {
                return new Response(ErrorCode.FAILED.getValue(), "Unable to fetch Payment Methods");
            }
        }
    }

    @GetMapping(value="/{name}")
    public Response getPaymentMethodsByName(@PathVariable String name) {
        PaymentMethods method = service.getPaymentMethodsByName(name);

        if (method != null) {
            return new Response(ErrorCode.SUCCESS.getValue(), "Success", method);
        } else {
            return new Response(ErrorCode.FAILED.getValue(), "No Payment method found agaisnt with name " + name);
        }

    }

     @GetMapping(value="/{id}")
    public Response getPaymentMethodsById(@PathVariable int id) {
        PaymentMethods method = service.getPaymentMethodsById(id);

        if (method != null) {
            return new Response(ErrorCode.SUCCESS.getValue(), "Success", method);
        } else {
            return new Response(ErrorCode.FAILED.getValue(), "No Payment method found agaisnt with id " + id);
        }

    }

    @PostMapping
    public Response addNewPaymentMethod(@RequestBody PaymentMethods paymentMethod) {

        boolean isSuccess = service.addNewPaymentMethod(paymentMethod);

        if (isSuccess) {
            Util.paymentMethodsList = service.getAllPaymentMethods();
            return new Response(ErrorCode.SUCCESS.getValue(), "Payment Method Added and loaded in JVM");
        } else {
            return new Response(ErrorCode.FAILED.getValue(), "Unable to add PaymentMethod. Please Try Again");
        }
    }

    @PutMapping
    public Response updatePaymentMethod(@RequestBody PaymentMethods paymentMethod) {
        boolean isExists = service.isPaymentMethodExists(paymentMethod.getId());
        if (isExists) {
            boolean isSuccess = service.addNewPaymentMethod(paymentMethod);
            if (isSuccess) {
                return new Response(ErrorCode.SUCCESS.getValue(), "Payment Method Added and loaded in JVM");
            } else {
                return new Response(ErrorCode.FAILED.getValue(), "Unable to add PaymentMethod. Please Try Again");
            }
        } else {
            return new Response(ErrorCode.FAILED.getValue(), "No payment method against this record. Please insert Payment Method.");
        }
    }

}
