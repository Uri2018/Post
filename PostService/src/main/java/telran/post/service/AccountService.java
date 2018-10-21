package telran.post.service;

import telran.post.domain.UserAccount;
import telran.post.dto.UserProfileDto;
import telran.post.dto.UserRegesterDto;

public interface AccountService {

	public UserProfileDto addUser(UserRegesterDto userRegesterDto,String auth);

	
public	UserProfileDto editUser(UserRegesterDto userRegesterDto, String auth);

public	UserProfileDto removeUser(String id, String auth);

}
