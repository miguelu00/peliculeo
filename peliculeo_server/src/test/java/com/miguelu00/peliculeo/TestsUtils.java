package com.miguelu00.peliculeo;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class TestsUtils {
    // This class will be extended by other test classes to inherit the test configuration
}

