package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SubtractRequest(
    @JsonProperty("minuend", required = true)
    val minuend: Double,

    @JsonProperty("subtrahend", required = true)
    val subtrahend: Double,
)
