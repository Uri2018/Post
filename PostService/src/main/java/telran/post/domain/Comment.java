package telran.post.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
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
}

