package telran.post.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Singular;
import telran.post.configoration.AccountConfigoration;
import telran.post.configoration.AccountUserCredential;
import telran.post.dau.UserAccountRepository;
import telran.post.domain.UserAccount;
import telran.post.dto.UserProfileDto;
import telran.post.dto.UserRegesterDto;
@Service
public class AccountServiceImpl implements AccountService {
@Autowired
UserAccountRepository userRepository;
@Autowired
AccountConfigoration accountconfigoration;

	@Override
	public UserProfileDto addUser(UserRegesterDto userRegesterDto, String auth) {
		AccountUserCredential credental= accountconfigoration.tokenDecode(auth);
	if(userRepository.existsById(credental.getLogin()))
	{
		throw new UserExitExcepation();
	}
	UserAccount userAccount=	 UserAccount.builder().id(credental.getLogin()).password(credental.getPassword()).firstname(userRegesterDto.getFirstname())
				.lastname(userRegesterDto.getLastname()).role("User").expDate(LocalDateTime.now().plusDays(accountconfigoration.getExpPeriod()))
				.build();
	userRepository.save(userAccount);
		return new UserProfileDto(credental.getLogin(),userRegesterDto.getFirstname(), userRegesterDto.getLastname());
	}

	@Override
	public UserAccount editUser(UserAccount userAccount, String auth) {
		AccountUserCredential credental= accountconfigoration.tokenDecode(auth);
		if(userRepository.existsById(credental.getLogin()))
		{
			UserAccount user=userRepository.findById(credental.getLogin()).orElse(null);
			if(user!=null)
			{
				String firstname=userAccount.getFirstname();
				if(firstname!=null)
				{
			user.setFirstname(firstname);
				}
				String lastname=userAccount.getLastname();
				if(lastname!=null)
				{
					user.setLastname(lastname);
				}
			String login=userAccount.getId();
			if(login!=null)
			{
			user.setId(login);
			}
			String password=userAccount.getPassword();
			if(password!=null)
			{
				user.setPassword(password);
			}
			LocalDateTime expDate=LocalDateTime.now();
		if(expDate!=null)
		{
			user.setExpDate(expDate);
		}
			Set<String>roles=userAccount.getRoles();
			if(roles!=null)
			{
				user.setRoles(roles);
			}
			
			}
			userRepository.save(user);
			return user;
		}
		return null;
		
	
		
		
	}

	@Override
	public void deleteUser(  String auth) {
		AccountUserCredential credental= accountconfigoration.tokenDecode(auth);
		if(userRepository.existsById(credental.getLogin()))
		{
			UserAccount userAccount=	userRepository.findById(credental.getLogin()).orElse(null);
			if(userAccount!=null)
			{
			userRepository.delete(userAccount);
			}
		}
		
	}

}
