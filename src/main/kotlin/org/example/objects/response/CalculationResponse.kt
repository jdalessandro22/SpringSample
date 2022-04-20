package org.example.objects.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class CalculationResponse private constructor(
    @JsonProperty("success")
    val isSuccess: Boolean,

    @JsonProperty("result")
    val result: Double?,

    @JsonProperty("error")
    val errorMessage: String?
) {
    companion object {
        fun asSuccess(result: Double) = CalculationResponse(true, result, null)

        fun asError(message: String) = CalculationResponse(false, null, message)
    }
}