package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class SubtractRequest(
    @JsonProperty("minuend")
    val minuend: Double,

    @JsonProperty("subtrahend")
    val subtrahend: Double,
)
