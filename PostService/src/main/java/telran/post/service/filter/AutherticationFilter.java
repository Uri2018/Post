package telran.post.service.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import telran.post.configoration.AccountConfigoration;
import telran.post.configoration.AccountUserCredential;
import telran.post.dau.UserAccountRepository;
import telran.post.domain.UserAccount;
@Service
@Order(1)
public class AutherticationFilter implements Filter {
@Autowired
	AccountConfigoration userconfigoration;
	@Autowired
	UserAccountRepository userAccountRepository;
	
	
	
	@Override
	public void destroy() {
		

	}

	@Override
	public void doFilter(ServletRequest regs, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)regs;
		HttpServletResponse response=(HttpServletResponse)resp;
		if(request.getServletPath().startsWith("/forum")) {
		String auth=request.getHeader("Authorization");
	AccountUserCredential	usercredental=userconfigoration .tokenDecode(auth);
       UserAccount userAccount=userAccountRepository.findById(usercredental.getLogin()).orElse(null);
       if(userAccount==null)
       {
    	   response.sendError(401,"Unauthorized");
       }
       else
       {
    	   if(!userAccount.getPassword().equals(usercredental.getPassword()))
    	   {
    		   response.sendError(403,"Forbidden");
    	   }
       }
       }
       chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	

	}

}
