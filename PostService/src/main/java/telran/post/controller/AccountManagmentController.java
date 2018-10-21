package telran.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.post.domain.UserAccount;
import telran.post.dto.UserProfileDto;
import telran.post.dto.UserRegesterDto;
import telran.post.service.AccountService;
import telran.post.service.ForbiddenExeception;

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
@DeleteMapping("/delete")
public UserProfileDto deleteUser(@PathVariable String id,@RequestHeader(value="Authorization") String auth) throws ForbiddenExeception
{
	return accountService.removeUser(id, auth);
}
@PutMapping("/editprofile")
public UserProfileDto editUser( @ RequestBody UserRegesterDto userAccount, @RequestHeader(value="Authorization")  String auth)
{
	return accountService.editUser(userAccount, auth);
	
}
}
