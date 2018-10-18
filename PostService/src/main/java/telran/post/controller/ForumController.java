package telran.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.post.domain.Post;
import telran.post.dto.DatePeriodDto;
import telran.post.dto.PostUpdateDto;
import telran.post.dto.newCommentDto;
import telran.post.dto.newPostDto;
import telran.post.service.ForumService;

@RestController
//@RequestMapping("/post")
public class ForumController {
@Autowired
ForumService service;

@PostMapping("/post")
public Post addPost(@RequestBody newPostDto postdto, @RequestHeader(value="Authorization")  String auth)
{
	return service.addNewPost(postdto, auth);
}

@DeleteMapping("/post/{id}")
public Post deletePost(@PathVariable String id, @RequestHeader(value="Authorization") String auth)
{
	return service.removePost(id, auth);
}

@GetMapping("/post/{id}")
public Post getPost(@PathVariable String id)
{
	return service.getPost(id);
}
@PutMapping("/post")
public Post updatePost(@RequestBody PostUpdateDto update, @RequestHeader(value="Authorization")  String auth)
{
	return service.updatePost(update, auth);	
}
@PutMapping("/post/{id}/like")
public boolean addLike(@PathVariable String id)
{
	return service.addLike(id);
}
@PutMapping("/post/{id}/comment")
public Post addComment(
	@PathVariable	String id,
	@RequestBody	newCommentDto newComment)
{
	return service.addComment(id, newComment);
}
@PostMapping("/post/tags")
Iterable< Post>findByTags( @RequestBody List<String>tags){
	return service.findByTags(tags);
	
}
@GetMapping("/post/auther")
Iterable<Post>findByAuther( @ PathVariable String auther){
	return service.findByAuther(auther);
	
}
@PostMapping("/post/date")
Iterable<Post>findByDate( @RequestBody DatePeriodDto period){
	return service.findByDate(period);
	
}

}
