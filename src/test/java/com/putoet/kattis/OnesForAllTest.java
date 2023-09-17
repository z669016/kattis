package com.putoet.kattis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnesForAllTest {

    @Test
    void onesComplexity() {
        assertEquals(1L, OnesForAll.onesComplexity(1));
        assertEquals(7L, OnesForAll.onesComplexity(10));
        assertEquals(2L, OnesForAll.onesComplexity(2));
        assertEquals(3L, OnesForAll.onesComplexity(12));
        assertEquals(8L, OnesForAll.onesComplexity(1234));
        assertEquals(8L, OnesForAll.onesComplexity(101));
        assertEquals(8L, OnesForAll.onesComplexity(20));
        assertEquals(8L, OnesForAll.onesComplexity(60));
        assertEquals(10L, OnesForAll.onesComplexity(1000));
        assertEquals(11L, OnesForAll.onesComplexity(99999));
        assertEquals(12L, OnesForAll.onesComplexity(100000));
    }
}