package org.example.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.objects.request.AddRequest
import org.example.objects.request.DivideRequest
import org.example.objects.request.MultiplyRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class ControllerErrorsSpec extends Specification {
    @Autowired
    private MockMvc mvc

    @Autowired
    private ObjectMapper objectMapper

    def "add :: 400 when not enough addends"() {
        when:
        AddRequest rq = new AddRequest(4.7)

        then:
        mvc.perform(
                post("/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(rq))
        ).andExpect(status().isBadRequest())
    }

    def "multiply :: 400 when not enough factors"() {
        when:
        MultiplyRequest rq = new MultiplyRequest(4.7)

        then:
        mvc.perform(
                post("/multiply")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(rq))
        ).andExpect(status().isBadRequest())
    }

    def "divide :: 400 when divide by 0 attempted"() {
        when:
        DivideRequest rq = new DivideRequest(4.7, 0)

        then:
        mvc.perform(
                post("/divide")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(rq))
        ).andExpect(status().isBadRequest())
    }

    def "400 when wrong request format"() {
        when:
        AddRequest rq = new AddRequest(3.2, 4,7, 5.8)

        then:
        mvc.perform(
                post("/subtract")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(rq))
        ).andExpect(status().isBadRequest())
    }

    def "#verb request receives 405"() {
        when:
        AddRequest rq = new AddRequest(3.2, 4.7, 5.8)

        then:
        mvc.perform(
                call("/add")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(rq))
        ).andExpect(status().isMethodNotAllowed())

        where:
        verb     | call
        "GET"    | { path -> get(path) }
        "PUT"    | { path -> put(path) }
        "PATCH"  | { path -> patch(path) }
        "DELETE" | { path -> delete(path) }
    }

    def "request with Accept: #ctype header receives 406"() {
        when:
        AddRequest rq = new AddRequest(3.2, 4.7, 5.8)

        then:
        mvc.perform(
                post("/add")
                .contentType("application/json")
                .accept(ctype)
                .content(objectMapper.writeValueAsString(rq))
        ).andExpect(status().isNotAcceptable())

        where:
        ctype << ["application/pdf", "text/plain", "text/html", "image/png"]
    }

    def "request with urlencoded body receives 415"() {
        when:
        String rq = "dividend=50&divisor=5"

        then:
        mvc.perform(
                post("/divide")
                .contentType("application/x-www-form-urlencoded")
                .content(rq)
        ).andExpect(status().isUnsupportedMediaType())
    }
}
