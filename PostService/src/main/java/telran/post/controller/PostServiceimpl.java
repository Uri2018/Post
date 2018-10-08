package telran.post.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class PostServiceimpl implements ForumService{
@Autowired

IForumRSepository Forum;
	@Override
	public Post addNewPost(newPostDto newpost) {
		Post p=new Post();
		Post post=new Post(newpost.getTitle()
				, newpost.getContect()
				,newpost.getAuther()
				,LocalDateTime.now()
				,newpost.getTags()
				,p.getComments());
		return Forum.save(post);
	}

	@Override
	public Post getPost(String id) {
		Post post=(Post) Forum.findByAuther(id);
		return post;
	}

	@Override
	public Post removePost(String id) {
		Post post=(Post) Forum.findByAuther(id);
		if(post==null)
		{
			return null;
		}
		int res=Integer.parseInt(post.getId());	
				Forum.deleteById(res);
		return post;
	}

	@Override
	public Post updatePost(PostUpdateDto updatePost) {
		Post post=(Post) Forum.findByAuther(updatePost.getId());
		if(post==null)
		{
			return null;
		}
		post.setContent(updatePost.getContent());
		return post;
	}

	@Override
	public boolean addLike(String id) {
		Post post=(Post) Forum.findByAuther(id);
		if(post==null)
		{
			return false;
		}
		post.setLikes(+1);
		return true;
	}

	@Override
	public Post addComment(String id, newCommentDto newComment) {
		Post post=(Post) Forum.findByAuther(id);
		 if(post==null)
		 {
			 return null;
		 }
		 Comment comment=new Comment(newComment.getUser(), newComment.getMessage(), LocalDate.now()
				 , post.getLikes());
		 Set<Comment>comments=new HashSet<>();
		 comments.add(comment);
		 post.setComments(comments);
		return post;
	}

	@Override
	public
	 Iterable<Post> findByTags(List<String> tags) {
		return Forum.findByTagsIn(tags);
	}

	@Override
	public Iterable<Post> findByAuther(String auther) {
		
		return Forum.findByAuther(auther);
	}

	@Override
	public Iterable<Post> findByDate(DatePeriodDto period) {
		LocalDate from=LocalDate.parse(period.getFrom());
		LocalDate to=LocalDate.parse(period.getTo());
		return Forum.findBycreateDateBetween(from,to);
	}

}
