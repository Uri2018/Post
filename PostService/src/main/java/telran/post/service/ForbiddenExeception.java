package telran.post.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import telran.post.configoration.AccountConfigoration;
import telran.post.configoration.AccountUserCredential;
import telran.post.dau.UserAccountRepository;
import telran.post.domain.UserAccount;


@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenExeception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
