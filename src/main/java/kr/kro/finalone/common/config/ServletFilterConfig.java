package kr.kro.finalone.common.config;

import jakarta.servlet.Filter;
import kr.kro.finalone.common.util.SimpleLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletFilterConfig {

    /**
     * 로깅 필터를 등록하기 위한 Bean입니다.
     *
     * 이 메서드는 FilterRegistrationBean을 생성하고, SimpleLoggingFilter로 설정하며, 필터가 적용될
     * URL 패턴을 설정합니다. 필터는 URL 패턴 때문에 모든 요청에 적용됩니다.
     *
     * @param simpleLoggingFilter 미리 설정된 SimpleLoggingFilter
     * @return 필터를 등록할 준비가 된 설정된 FilterRegistrationBean
     * @author Hojun Song
     *
     */

    @Bean
    public FilterRegistrationBean<Filter> loggingFilter(SimpleLoggingFilter simpleLoggingFilter) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(simpleLoggingFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
