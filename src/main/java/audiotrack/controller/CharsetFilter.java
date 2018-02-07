package audiotrack.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharsetFilter implements Filter{
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null){
            encoding="UTF-8";
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {

        String requestURI = ((HttpServletRequest) request).getRequestURI();
        if (requestURI != null && requestURI.endsWith("/faces/")) {
            ((HttpServletRequest) request).getSession();
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }

        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    public void destroy() {}
}
