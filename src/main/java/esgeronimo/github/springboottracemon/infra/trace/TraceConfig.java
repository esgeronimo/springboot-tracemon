package esgeronimo.github.springboottracemon.infra.trace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(value = {
    ClientPerformanceMonitoringConfig.class,
    DataSourcePerformanceMonitoringConfig.class
})
public class TraceConfig {
    @Bean
    public GenericPerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new GenericPerformanceMonitorInterceptor();
    }
}