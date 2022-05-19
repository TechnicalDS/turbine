package com.zee.turbine.model.adyen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Saba Imteyaz
 * @Date 27/03/2022
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationItem {

    @JsonProperty("notificationRequestItem")
    private NotificationRequestItem notificationRequestItem;
}
