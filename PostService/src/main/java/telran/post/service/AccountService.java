package telran.post.service;

import telran.post.domain.UserAccount;
import telran.post.dto.UserProfileDto;
import telran.post.dto.UserRegesterDto;

public interface AccountService {

	public UserProfileDto addUser(UserRegesterDto userRegesterDto,String auth);
	public UserAccount editUser(UserAccount userAccount,String auth);
	public void deleteUser(String auth);
}
