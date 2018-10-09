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

import telran.post.dau.IForumRSepository;
import telran.post.domain.Comment;
import telran.post.domain.Post;
import telran.post.dto.DatePeriodDto;
import telran.post.dto.PostUpdateDto;
import telran.post.dto.newCommentDto;
import telran.post.dto.newPostDto;
import telran.post.service.ForumService;
@Service
public class ForumServiceimpl implements ForumService{
@Autowired

IForumRSepository forum;
	@Override
	public Post addNewPost(newPostDto newpost) {
		
		Post post=new Post(newpost.getTitle(), newpost.getContect(), newpost.getAuther(),newpost.getTags());
		return forum.save(post);
	}

	
	@Override
	public Post getPost(String id) {
		Post post=(Post) forum.findAllBy().filter(x->x.getId().equals(id));
		if(post==null)
		{
			return null;
		}
		return post;
	}

	@Override
	public Post removePost(String id)  {
		Post post=forum.findAll().get(Integer.parseInt(id));
		if(post==null)
		{
			return null;
		}
			  forum.delete(post);
				  return post;
	}

	@Override
	public Post updatePost(PostUpdateDto updatePost) {
		Post post=forum.findAll().get(Integer.parseInt(updatePost.getId()));
		if(post==null)
		{
			return null;
		}
		post.setContent(updatePost.getContent());
		return forum.save(post);
	}

	@Override
	public boolean addLike(String id) {
		Post post=forum.findAll().get(Integer.parseInt(id));
		if(post==null)
		{
			return false;
		}
		post.setLikes(post.getLikes()+1);
		return forum.save(post) != null;
	}

	@Override
	public Post addComment(String id, newCommentDto newComment) {
		Post post=forum.findAll().get(Integer.parseInt(id));
		 if(post==null)
		 {
			 return null;
		 }
		 Comment comment=new Comment(newComment.getUser(), newComment.getMessage(), LocalDate.now()
				 , post.getLikes());
		 Set<Comment>comments=new HashSet<>();
		 comments.add(comment);
		 post.setComments(comments);
		return forum.save(post);
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
		LocalDate from=LocalDate.parse(period.getFrom());
		LocalDate to=LocalDate.parse(period.getTo());
		return forum.findBycreateDateBetween(from,to);
	}
	
  public Stream<Post>findAll()
  {
	  return forum.findAll().stream();
  }
}
