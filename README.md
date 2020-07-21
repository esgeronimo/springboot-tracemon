# springboot-tracemon
Usage of AbstractMonitoringInterceptor and AOP for determining duration of repository and client calls

## Explanation
The `org.springframework.aop.interceptor.AbstractMonitoringInterceptor` class is an implementation of `org.springframework.aop.Advise` that can be used to intercept method calls for monitoring purposes.

We created an implementation of the interceptor, `GenericPerformanceMonitorInterceptor`, that is used in creating instances of `org.springframework.aop.Advisor` associated with the following:
* Database queries - involves repository classes which by convention should be any file under `esgeronimo.github.springboottracemon.infra.repository` with suffix `Repository`. See `esgeronimo.github.springboottracemon.infra.trace.ClientPerformanceMonitoringConfig`
* API calls - involves client classes which by convention should be any files under OR inherits files under `esgeronimo.github.springboottracemon.client`. See ` `esgeronimo.github.springboottracemon.infra.trace.

## Usage
Run the application:
```
$ ./gradlew clean :bootRun
```
Endpoint to produce the logs:
```
POST http://localhost:8080/accounts
```
The endpoint would just produce a `Status 200` response and the output would be shown on the application logs.

Sample log output:
```
2020-07-21 15:28:59.729 TRACE 2312 --- [nio-8080-exec-2] i.t.GenericPerformanceMonitorInterceptor : GenericPerformanceMonitorInterceptor.LogInfo(method=esgeronimo.github.springboottracemon.client.AccountEngineClient.save, duration=2155)
2020-07-21 15:28:59.790 TRACE 2312 --- [nio-8080-exec-2] i.t.GenericPerformanceMonitorInterceptor : GenericPerformanceMonitorInterceptor.LogInfo(method=org.springframework.data.repository.CrudRepository.save, duration=61)
2020-07-21 15:29:00.639 TRACE 2312 --- [nio-8080-exec-2] i.t.GenericPerformanceMonitorInterceptor : GenericPerformanceMonitorInterceptor.LogInfo(method=esgeronimo.github.springboottracemon.infra.push.PushNotifClient.push, duration=849)
```
Information is wrapped within a `GenericPerformanceMonitorInterceptor.LogInfo` record with the following content:
- `method` - provides the class and method triggered
- `duration` - time it take to finish the method from call to returning value (in milliseconds)

Note that the duration does not exactly provide the actual request-to-response time took for the external call. There is a little bit of "padding" due to the additional abstract logic that the framework does before triggering the external call (i.e. Hibernate, RestTemplate) but this should essentially give a close estimate on the duration of the call.

## Reference:
- https://www.baeldung.com/spring-performance-logging
- https://docs.spring.io/spring/docs/5.0.0.M4_to_5.0.0.M5/Spring%20Framework%205.0.0.M5/org/springframework/aop/interceptor/AbstractMonitoringInterceptor.html
