package com.zee.turbine.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Saba Imteyaz
 * @Date 28/03/2022
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZeePaymentAPIResponse<T> {

    private Boolean success;

    private int code;

    private String message;

    private T data;

    /**
     *
     * @param code
     * @param message
     * @return ZeePaymentAPIResponse<T>
     * To build an error response
     * Should be called using an empty object of T type
     */
    public ZeePaymentAPIResponse<T> buildErrorResponse(int code, String message){
        this.setSuccess(false);
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    /**
     *
     * @param code
     * @param message
     * @param data
     * @return ZeePaymentAPIResponse<T>
     * To build a success response
     * Should be called using an empty object of T type
     */
    public ZeePaymentAPIResponse<T> buildSuccessResponse(int code, String message, T data){
        this.setSuccess(true);
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
        return this;
    }

}
