package telran.post.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Comment {
	String user;
	String message;
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
	LocalDate datecreated;
	int likes;
}

