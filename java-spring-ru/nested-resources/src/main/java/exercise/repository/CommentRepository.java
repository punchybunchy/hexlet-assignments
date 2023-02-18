package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    @Query("select c from Comment c where c.post.id = ?1")
    Iterable<Comment> findAllByPostId(long postId);

    @Query("select c from Comment c where c.post.id = ?1 and c.id = ?2")
    Optional<Comment> findByPostIdAndCommentId(long postId, long commentId);

    // END
}
