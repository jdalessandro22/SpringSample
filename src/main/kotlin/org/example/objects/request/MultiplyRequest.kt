package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MultiplyRequest(
    @JsonProperty("factors")
    val factors: DoubleArray,
)
