package com.zee.turbine.dto;

import com.zee.turbine.response.ZeePaymentAPIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Saba Imteyaz
 * @Date 14/03/22
 */

@Validated
public interface TurbineApi {

    @Operation(summary = "Initiate payment", description = "", security = {
            @SecurityRequirement(name = "turbine_api_key")    }, tags={ "Initiate Payment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InitiatePaymentResponse.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request"),

            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @RequestMapping(value = "/payment/initiate",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<ZeePaymentAPIResponse<InitiatePaymentResponse>> initiate(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody InitiatePaymentRequest initiatePaymentRequest);


    @Operation(summary = "process payment", description = "", security = {
            @SecurityRequirement(name = "turbine_api_key")    }, tags={ "Process Payment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CallbackProcessResponse.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request"),

            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @RequestMapping(value = "/payment/process/callback",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<ZeePaymentAPIResponse<CallbackProcessResponse>> processCallback(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CallbackProcessRequest processPaymentRequest);


    @Operation(summary = "process payment", description = "", security = {
            @SecurityRequirement(name = "turbine_api_key")    }, tags={ "Process Payment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CallbackProcessResponse.class))),

            @ApiResponse(responseCode = "400", description = "Bad Request"),

            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @RequestMapping(value = "/payment/process/s2s",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<ZeePaymentAPIResponse<S2sProcessResponse>> processS2s(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody S2sProcessRequest s2sProcessRequest);

}


