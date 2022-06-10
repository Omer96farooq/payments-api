/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay.model.response;

import lombok.Getter;

/**
 * @author Sagher Mehmood
 */
@Getter
public enum ErrorCode {

    // use 00 - 10 for general message
    SUCCESS("00"),
    FAILED("01"),
    INVALID_PARAMS("02");

    private String respCode;

    private String value;

    private ErrorCode(String value) {
        this.value = value;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
}
