package com.dubatovka.app.filter;

import com.dubatovka.app.entity.JAuctionUser;
import com.dubatovka.app.manager.ConfigConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.dubatovka.app.manager.ConfigConstant.ATTR_LOCALE;
import static com.dubatovka.app.manager.ConfigConstant.ATTR_ROLE;

/**
 * The class provides security filter for servlet container which monitors each session to have locale and role attributes set.
 */
@WebFilter(
        filterName = "SecurityFilter",
        servletNames = {"MainController"},
        initParams = {@WebInitParam(name = "ATTR_ROLE", value = ATTR_ROLE),
                @WebInitParam(name = "ATTR_LOCALE", value = ATTR_LOCALE)},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
)
public class SessionAttributesFilter implements Filter {
    
    /**
     * Role attribute name.
     */
    private String role;
    /**
     * Locale attribute name.
     */
    private String locale;
    
    public void init(FilterConfig config) throws ServletException {
        role = config.getInitParameter("ATTR_ROLE");
        locale = config.getInitParameter("ATTR_LOCALE");
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Object userRole = session.getAttribute(role);
        Object userLocale = session.getAttribute(locale);
        if (userRole == null) {
            userRole = JAuctionUser.UserRole.GUEST;
            session.setAttribute(role, userRole);
        }
        if (userLocale == null) {
            userLocale = ConfigConstant.DEFAULT_LOCALE;
            session.setAttribute(locale, userLocale);
        }
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        role = null;
        locale = null;
    }
}
