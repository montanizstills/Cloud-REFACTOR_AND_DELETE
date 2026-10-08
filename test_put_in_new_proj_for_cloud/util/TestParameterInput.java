package com.vertexinc.cloud.test.util;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER})
@ExtendWith({TestParameterInputResolver.class})
public @interface TestParameterInput {
    String[] strings();
}
