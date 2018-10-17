package telran.post.service;

import telran.post.dto.UserProfileDto;
import telran.post.dto.UserRegesterDto;

public interface AccountService {

	public UserProfileDto addUser(UserRegesterDto userRegesterDto,String auto);
	
}
