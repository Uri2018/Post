package telran.post.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.post.configoration.AccountConfigoration;
import telran.post.configoration.AccountUserCredential;
import telran.post.dau.IForumRSepository;
import telran.post.dau.UserAccountRepository;
import telran.post.domain.Comment;
import telran.post.domain.Post;
import telran.post.domain.UserAccount;
import telran.post.dto.DatePeriodDto;
import telran.post.dto.PostUpdateDto;
import telran.post.dto.UserRegesterDto;
import telran.post.dto.newCommentDto;
import telran.post.dto.newPostDto;
import telran.post.service.ForumService;
import telran.post.service.filter.AutherticationFilter;
@Service
public class ForumServiceimpl implements ForumService{
@Autowired

IForumRSepository forum;
UserAccountRepository userRepository;
AutherticationFilter filter;
AccountConfigoration accountconfigoration;
	@Override
	public Post addNewPost(newPostDto newpost,String auth) {	
		AccountUserCredential credental= accountconfigoration.tokenDecode(auth);
		if(userRepository.existsById(credental.getLogin()))
		{
			Post post=convetToPost(newpost);
		 forum.save(post);
		 return post;
		}
		
		 return null;
	}

	
	private Post convetToPost(newPostDto newpost) {
		return new Post(newpost.getTitle(), newpost.getContect(),newpost.getAuther() ,newpost.getTags());
	}


	@Override
	public Post getPost(String id) {
		
			
		return  forum.findById(id).orElse(null);
		
	}

	@Override
	public Post removePost(String id, String auth) {
		// TODO service remove post
		Post post = forum.findById(id).orElse(null);
		if (post != null) {
			AccountUserCredential credentials = accountconfigoration.tokenDecode(auth);
			UserAccount user = userRepository.findById(credentials.getLogin()).get();
			Set<String>roles = user.getRoles();
			boolean hasRight = roles.stream().anyMatch(s -> "Admin".equals(s) || "Moderators".equals(s));
			hasRight = hasRight || credentials.getLogin().equals(post.getAuther());
			if (!hasRight) {
				throw new ForbiddenExeception();
			}
			forum.delete(post);
		}
		return post;
	}

	@Override
	public Post updatePost(PostUpdateDto updatePost,String auth) {
		Post post=forum.findById(updatePost.getId()).orElse(null);
		if(post!=null)
		{
			AccountUserCredential credential=accountconfigoration.tokenDecode(auth);
			if(!credential.getLogin().equals(post.getAuther()))
			{
				throw new ForbiddenExeception();
			}
			post.setContent(updatePost.getContent());
			forum.save(post);
		}
			return post;
	}

	@Override
	public boolean addLike(String id) {
		Post post=forum.findById(id).orElse(null);
		if(post!=null)
		{
			post.addlike();
			return true;
		}
		return false;
	}

	@Override
	public Post addComment(String id, newCommentDto newComment) {
		Post post=forum.findById(id).orElse(null);
		 if(post!=null)
		 {
			 Comment comment=new Comment(newComment.getUser(), newComment.getMessage());
			post.addComment(comment);
			 forum.save(post);
			 return post;
		 }
		return post;
	}

	@Override
	public
	 Iterable<Post> findByTags(List<String> tags) {
		return forum.findByTagsIn(tags);
	}

	@Override
	public Iterable<Post> findByAuther(String auther) {
		
		return forum.findByAuther(auther);
	}

	@Override
	public Iterable<Post> findByDate(DatePeriodDto period) {
		return forum.findBycreateDateBetween(LocalDate.parse(period.getFrom()),LocalDate.parse(period.getTo()));
	}
	
  
}
