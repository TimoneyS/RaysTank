package com.rays.tank.common;

import java.io.InputStream;

public class ResourceLoader {
    public static InputStream getClassPathResource(String resource) {
        return ResourceLoader.class.getClassLoader().getResourceAsStream(resource);
    }
}
