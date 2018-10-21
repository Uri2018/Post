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
	public UserProfileDto editUser(UserRegesterDto userRegesterDto, String auth) {
		AccountUserCredential credental= accountconfigoration.tokenDecode(auth);
		UserAccount useraccount=userRepository.findById(credental.getLogin()).get();
		useraccount.setFirstname(userRegesterDto.getFirstname());
		useraccount.setLastname(userRegesterDto.getLastname());
		userRepository.save(useraccount);
		return new UserProfileDto(credental.getLogin(), userRegesterDto.getFirstname(), userRegesterDto.getLastname());
		
		
		
	}

	@Override
	public UserProfileDto removeUser(String id, String auth) {
		// TODO remove forum user
		AccountUserCredential credentials = accountconfigoration.tokenDecode(auth);
		UserAccount user = userRepository.findById(credentials.getLogin()).get();
		Set<String>roles = user.getRoles();
		boolean hasRight = roles.stream().anyMatch(s -> "Admin".equals(s) || "Moderators".equals(s));
		hasRight = hasRight || credentials.getLogin().equals(id);
		if (!hasRight) {
			throw new ForbiddenExeception();
		}
		UserAccount userAccount = userRepository.findById(id).get();
		userRepository.delete(userAccount);
		return new UserProfileDto(userAccount.getId(), userAccount.getFirstname(), userAccount.getLastname());
	}
	

}
