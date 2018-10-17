package telran.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.post.dto.UserProfileDto;
import telran.post.dto.UserRegesterDto;
import telran.post.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountManagmentController {
@Autowired
AccountService accountService;
@PostMapping("/register")
public UserProfileDto register( @RequestBody UserRegesterDto userRegesterDto, @ RequestHeader(value="Authorization")   String auth)
{
	return accountService.addUser(userRegesterDto, auth);
}

}
