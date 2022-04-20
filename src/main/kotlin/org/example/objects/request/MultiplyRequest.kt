package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MultiplyRequest(
    @JsonProperty("factors", required = true)
    val factors: DoubleArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MultiplyRequest

        if (!factors.contentEquals(other.factors)) return false

        return true
    }

    override fun hashCode(): Int {
        return factors.contentHashCode()
    }
}
