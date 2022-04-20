package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DivideRequest(
    @JsonProperty("dividend", required = true)
    val dividend: Double,

    @JsonProperty("divisor", required = true)
    val divisor: Double,
)