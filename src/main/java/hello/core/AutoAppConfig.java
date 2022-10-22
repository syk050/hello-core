package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @ 어노테이션이 붙은 클래스들을 찾아 자동으로 스프링 빈으로 등록
        /* @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
         * AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고,
         * 실행되어 버려서 기존 코드들을 남기기 위해 필터로 제외하였다       */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
        /* ComponentScan PDF: 6- 14p
        basePackages = "hello.core", // 탐색할 패키지의 시작 위치 지정
        basePackages = {"hello.core", "hello.service"} 가능
        basePackageClasses = AppConfig.class, // 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다
         */

        /* 권장 방법
        패키지 위치를 지정하지 않고,
        설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다
        com.hello -> 프로젝트 시작 루트,

        스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 @SpringBootApplication 를
        이 프로젝트 시작 루트 위치에 두는 것이 관례이다.
        @SpringBootApplication 안에 @ComponentScan 이 들어있다
         */

        /* 컴포넌트 스캔 기본 대상
         * @Component : 컴포넌트 스캔에서 사용
         * @Controller : 스프링 MVC 컨트롤러에서 사용
         * @Service : 스프링 비즈니스 로직에서 사용
         * @Repository : 스프링 데이터 접근 계층에서 사용
         * @Configuration : 스프링 설정 정보에서 사용
         */


}
