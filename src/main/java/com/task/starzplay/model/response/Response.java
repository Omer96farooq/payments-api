/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.task.starzplay.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author omer
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String respCode;
    private String msg;
    private boolean isSuccess;
    public Object respData;

    public Response(String respCode, String msg) {
        this.respCode = respCode;
        this.msg = msg;
        this.isSuccess = respCode.equalsIgnoreCase(ErrorCode.SUCCESS.getValue());
        this.respData = null;
    }

    public Response(String respCode, String msg, Object respData) {
        this.respCode = respCode;
        this.msg = msg;
        this.isSuccess = respCode.equalsIgnoreCase(ErrorCode.SUCCESS.getValue());
        this.respData = respData;
    }

    @Override
    public String toString() {
        if (respData != null && respData instanceof List) {
            return "Response [" + "isSuccess=" + isSuccess + ", message=" + msg + ", responseCode=" + respCode
                    + ", responseData ListSize=" + ((List) respData).size() + "]";

        } else {
            return "Response [" + "isSuccess=" + isSuccess + ", message=" + msg + ", responseCode=" + respCode + "]";
        }
    }
}
