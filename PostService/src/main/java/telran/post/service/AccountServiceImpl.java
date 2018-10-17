package telran.post.service;

import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
