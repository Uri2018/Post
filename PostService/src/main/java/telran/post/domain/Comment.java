package telran.post.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Comment {
	String user;
	String message;
	@JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
	LocalDate datecreated;
	int likes;
	
	public void addLike()
	{
		likes++;
	}

	public Comment(String user, String message) {
		this.user = user;
		this.message = message;
		datecreated=LocalDate.now();
		likes=likes;
	}
	
}

