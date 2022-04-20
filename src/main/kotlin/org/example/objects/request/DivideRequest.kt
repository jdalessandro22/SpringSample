package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DivideRequest(
    @JsonProperty("dividend")
    val dividend: Double,

    @JsonProperty("divisor")
    val divisor: Double,
)