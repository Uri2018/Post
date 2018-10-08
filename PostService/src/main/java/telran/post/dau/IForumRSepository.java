package telran.post.dau;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.post.domain.Post;

public interface IForumRSepository extends MongoRepository<Post, Integer>{
Iterable<Post>findByTagsIn(List<String>tags);
Iterable<Post>findByAuther(String auther);
Iterable<Post>findBycreateDateBetween(LocalDate from,LocalDate to);
Stream<Post>findAllBy();
}
