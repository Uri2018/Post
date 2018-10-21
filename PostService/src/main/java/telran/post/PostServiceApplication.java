package telran.post;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.post.dau.UserAccountRepository;
import telran.post.domain.UserAccount;

@SpringBootApplication
public class PostServiceApplication implements CommandLineRunner{
@Autowired
UserAccountRepository userAccountRepository;
	public static void main(String[] args) {
		SpringApplication.run(PostServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 UserAccount userAccount=UserAccount.builder().id("admin")
			 .password("admin")
			 .firstname("super")
			 .lastname("admin")
			 .role("admin")
			 .expDate(LocalDateTime.now().plusYears(25))
			 .build();
	 userAccountRepository.save(userAccount);
		
	}
}
