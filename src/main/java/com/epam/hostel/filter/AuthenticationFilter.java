
package com.epam.hostel.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.hostel.model.user.Role;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.epam.hostel.model.user.User;
import com.epam.hostel.service.IUserService;


/**
 * Authentication Filter implementation
 */
public class AuthenticationFilter implements Filter {

    private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

    public static final String AUTH_USER_ID = "uid";
    public static final String AUTH_USER_REQ_ATTR = "authUser";

    private IUserService userService;

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        ApplicationContext ctxt = WebApplicationContextUtils.getWebApplicationContext(cfg.getServletContext());
        this.userService = ctxt.getBean(IUserService.class);
    }

    /**
     * Method is checked session attribute. If this attribute
     * is exist - request can be processed. If not it means that some one want to
     * get client or admin rights with out login.
     *
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = ((HttpServletRequest) req);
        HttpSession session = httpReq.getSession(true);
        Long uid = (Long) session.getAttribute(AUTH_USER_ID);


        if (uid != null) {
            User user = userService.findById(uid);
            httpReq.setAttribute(AUTH_USER_REQ_ATTR, user);
            // is not Admin
            if (!(user.getRole().equals(Role.ADMIN))) {
                if (httpReq.getRequestURI().equals("/admin-users") || httpReq.getRequestURI().equals("/admin-orders")
                        || httpReq.getRequestURI().equals("/admin-rooms")) {
                    logger.info("Unauthorized access");
                    ((HttpServletResponse) resp).sendRedirect("/");
                }
            }
            logger.info("Authenticated");
            logger.info(httpReq.getRequestURI());
        } else {
            logger.info("Not Authenticated");
            if (httpReq.getRequestURI().equals("/admin-users") || httpReq.getRequestURI().equals("/admin-orders")
                    || httpReq.getRequestURI().equals("/admin-rooms") || httpReq.getRequestURI().equals("/client")) {
                ((HttpServletResponse) resp).sendRedirect("/");
                logger.info("Unauthorized access");
            }
        }
        //Transfers control to another filter
        chain.doFilter(req, resp);
    }


    @Override
    public void destroy() {

    }

}
