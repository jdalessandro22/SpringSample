package org.example

import spock.lang.Specification

class SanitySpec extends Specification {
    def smokeTest() {
        expect:
        1 == 1
    }
}
