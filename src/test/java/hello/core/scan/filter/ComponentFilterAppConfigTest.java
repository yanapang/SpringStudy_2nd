package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull(); //MyIncludeComponent를 붙여 등록되야함.

//        ac.getBean("beanB", BeanB.class);
        assertThrows( //MyExcludeComponent를 붙여 등록안되어있어야함.
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
        
    }

    @Configuration
    @ComponentScan( //내가 생성한 어노테이을 스캔해주는 기능.
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
            //type=FilterType.ANNOTATION은 기본값이므로 생략가능.

            //FilterType 옵션.
            /*
            * 1: ANNOTATION :기본값, 어노테이션 인식해 동작
            * 2: ASSIGNABLE_TYPE :지정한 타입과 자식 타입을 인식해 동작
            * 3: ASPECTJ: AspectJ 패턴사용
            * 4: REGEX: 정규표현식
            * 5: CUSTOM : TypeFilter이라는 인터페이스 구현해 처리
            * */
    )
    static class ComponentFilterAppConfig {

    }
}
