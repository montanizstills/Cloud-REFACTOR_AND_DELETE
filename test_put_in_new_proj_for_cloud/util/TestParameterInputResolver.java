package com.vertexinc.cloud.test.util;

import io.github.nezonium.util.WebBrowser;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Arrays;
import java.util.List;

public class TestParameterInputResolver implements ParameterResolver {

    String[] stringParameterInputs;
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        //This gets called for each parameter. Can we find a way to filter for just the annotated parameter @TestParameterInputResolver
        return
                parameterContext.getParameter().getType().equals(String.class) || parameterContext.getParameter().getType().equals(Arrays.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        parameterContext.isAnnotated(TestParameterInput.class);
        stringParameterInputs = parameterContext.getParameter().getAnnotation(TestParameterInput.class).strings();
        return Arrays.asList(stringParameterInputs);
    }
}
