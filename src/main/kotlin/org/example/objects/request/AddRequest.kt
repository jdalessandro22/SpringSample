package org.example.objects.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AddRequest(
    @JsonProperty("addends")
    val addends: DoubleArray,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AddRequest

        if (!addends.contentEquals(other.addends)) return false

        return true
    }

    override fun hashCode(): Int {
        return addends.contentHashCode()
    }
}
