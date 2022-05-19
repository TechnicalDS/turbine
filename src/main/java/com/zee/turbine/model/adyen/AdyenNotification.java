package com.zee.turbine.model.adyen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Saba Imteyaz
 * @Date 27/03/2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdyenNotification {

    @JsonProperty("live")
    private Boolean live;

    @JsonProperty("notificationItems")
    private List<NotificationItem> notificationItems;

}
