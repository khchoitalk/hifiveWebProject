package user.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import user.wrapper.PwdWrapper;



/**
 * Servlet Filter implementation class CryptoFilter
 */
//@WebFilter("*.cp")
public class PwdFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PwdFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 전송된 userpwd 암호화 처리용 필터
		HttpServletRequest hRequest = (HttpServletRequest)request;
		
		PwdWrapper cw = new PwdWrapper(hRequest);

		// pass the request along the filter chain
		chain.doFilter(cw, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
