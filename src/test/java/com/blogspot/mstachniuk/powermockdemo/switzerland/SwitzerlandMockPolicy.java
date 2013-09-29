package com.blogspot.mstachniuk.powermockdemo.switzerland;

import org.powermock.core.spi.*;
import org.powermock.mockpolicies.*;
import org.powermock.reflect.*;

import java.lang.reflect.*;

public class SwitzerlandMockPolicy implements PowerMockPolicy {

    @Override
    public void applyClassLoadingPolicy(MockPolicyClassLoadingSettings settings) {
        settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(
                "com.blogspot.mstachniuk.powermockdemo.switzerland.SwitzerlandTaxSource");
    }

    @Override
    public void applyInterceptionPolicy(MockPolicyInterceptionSettings settings) {
        Method method = Whitebox.getMethod(SwitzerlandTaxSource.class, "getTax");
        settings.proxyMethod(method, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return 0.2;
            }
        });
    }
}
