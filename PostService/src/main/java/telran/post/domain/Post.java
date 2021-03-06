package telran.post.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode

@Document(collection="Post")
public class Post {
@Id
String id;
String title;
String content;
String auther;
@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")

LocalDateTime createDate;
Set<String>tags;
int likes;
Set<Comment>comments;




public boolean removeTag(String tag)
{
	return tags.remove(tag);
}




public Post( String title, String content, String auther, Set<String> tags) {
	
	
	this.title = title;
	this.content = content;
	this.auther = auther;
	createDate = LocalDateTime.now();
	this.tags = tags;
	comments = new HashSet<>();
}
public boolean addComment(Comment comment)
{
	return comments.add(comment);
}

public boolean addTag(String tag)
{
	return tags.add(tag);
}
public void addlike()
{
	likes++;
}
}
