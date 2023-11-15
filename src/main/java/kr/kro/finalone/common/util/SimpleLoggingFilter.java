package kr.kro.finalone.common.util;

import jakarta.servlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;


@Component
public class SimpleLoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(SimpleLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Error occurred while filtering the request/response", e);
        } finally {
            logger.info("[" + httpResponse.getStatus() + "] " + httpRequest.getMethod() + " " + httpRequest.getRequestURI());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Initialization code if required
    }

    @Override
    public void destroy() {
        // Cleanup code if required
    }
}
