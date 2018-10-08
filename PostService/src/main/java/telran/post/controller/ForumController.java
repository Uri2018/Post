package telran.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import telran.post.domain.Post;
import telran.post.dto.PostUpdateDto;
import telran.post.dto.newCommentDto;
import telran.post.dto.newPostDto;
import telran.post.service.ForumService;

@RestController
public class ForumController {
@Autowired
ForumService service;

@PostMapping("/createpost")
public Post addPost(@RequestBody newPostDto postdto)
{
	return service.addNewPost(postdto);
}

@DeleteMapping("/delete/{id}")
public Post deletePost(@RequestParam String id)
{
	return service.removePost(id);
}

@GetMapping("/getpost/{id}")
public Post getPost(@RequestParam String id)
{
	return service.getPost(id);
}
@PutMapping("/update/{id}")
public Post updatePost(@RequestBody PostUpdateDto update)
{
	return service.updatePost(update);	
}
@PutMapping("/addlike/{id}")
public boolean addLike(@RequestParam String id)
{
	return service.addLike(id);
}
@PutMapping("/addcomment/{id}")
public Post addComment(
	@RequestParam	String id,
	@RequestBody	newCommentDto newComment)
{
	return service.addComment(id, newComment);
}


}
