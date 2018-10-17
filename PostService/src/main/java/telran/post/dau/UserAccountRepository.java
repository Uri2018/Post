package telran.post.dau;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.post.domain.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String>{

}
