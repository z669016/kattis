package com.putoet.kattis;

import java.io.InputStream;

public class IS {
    public static InputStream in(String resourceName) {
        return IS.class.getResourceAsStream(resourceName);
    }
}
