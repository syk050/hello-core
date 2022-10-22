package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @ 어노테이션이 붙은 클래스들을 찾아 자동으로 스프링 빈으로 등록
        /* @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
         * AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고,
         * 실행되어 버려서 기존 코드들을 남기기 위해 필터로 제외하였다
         */
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        )
)
public class AutoAppConfig {

}
