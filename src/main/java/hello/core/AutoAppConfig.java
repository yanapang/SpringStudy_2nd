package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", //패키지 탐색 시작위치 지정. (속도 향상 가능). 여러개 지정가능. {"hello.core", "hello.service"}
        //basePackageClasses = AutoAppConfig.class, //패키지 탐색 지정 클래스부터 시작.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}