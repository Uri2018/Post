package telran.post.service;

import java.util.List;

import javax.naming.NameNotFoundException;

import telran.post.domain.Post;
import telran.post.dto.DatePeriodDto;
import telran.post.dto.PostUpdateDto;
import telran.post.dto.newCommentDto;
import telran.post.dto.newPostDto;

public interface ForumService {
Post addNewPost(newPostDto newpost,String auth);
Post getPost(String id);
Post removePost(String id,String auth) ;
Post updatePost(PostUpdateDto updatePost,String auth);
boolean addLike(String id);
Post addComment(String id,newCommentDto newComment);
Iterable< Post>findByTags(List<String>tags);
Iterable<Post>findByAuther(String auther);
Iterable<Post>findByDate(DatePeriodDto period);

}