package esgeronimo.github.springboottracemon.infra.trace;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

import lombok.Value;

public class GenericPerformanceMonitorInterceptor extends AbstractMonitoringInterceptor {

    private static final long serialVersionUID = 4924058424735671749L;

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {
        String name = createInvocationTraceName(invocation);
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            writeToLog(logger, new LogInfo(name, System.currentTimeMillis() - start).toString());
        }
    }

    @Value
    private static class LogInfo {
        private String method;
        private long duration;
    }
}