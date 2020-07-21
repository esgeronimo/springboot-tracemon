package esgeronimo.github.springboottracemon.infra.trace;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Aspect
@ConditionalOnProperty(name = "springboottracemon.trace.datasource-monitoring.enabled", havingValue = "true", matchIfMissing = false)
public class DataSourcePerformanceMonitoringConfig {
    @Pointcut("within(esgeronimo.github.springboottracemon.infra.repository.*Repository+)")
    public void doDataSourceTrace() {}

    @Bean
    public Advisor datasourcePerformanceMonitoAdvisor(GenericPerformanceMonitorInterceptor performanceMonitorInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("esgeronimo.github.springboottracemon.infra.trace.DataSourcePerformanceMonitoringConfig.doDataSourceTrace()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor);
    }
}