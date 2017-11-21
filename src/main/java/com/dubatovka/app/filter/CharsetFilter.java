package com.dubatovka.app.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * The class provides charset filter for servlet container which converts each request and response data to definite encoding.
 */
@WebFilter(
        filterName = "CharsetFilter",
        urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding param")}
)
public class CharsetFilter implements Filter {
    
    /**
     * Encoding to set.
     */
    private String code;
    
    
    public void init(FilterConfig config) throws ServletException {
        code = config.getInitParameter("encoding");
    }
    
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (codeRequest == null || !code.equals(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }
    
    public void destroy() {
        code = null;
    }
}