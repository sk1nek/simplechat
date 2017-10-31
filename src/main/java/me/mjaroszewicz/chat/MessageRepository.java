package me.mjaroszewicz.chat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllByAuthorId(Long authorId);
    Message findById(Long id);

    /**
     * @param authorId Id of author entity
     * @param targetId Id of recipient entity
     * @return All messages sent by author to recipient
     */
    ArrayList<Message> findAllByAuthorIdAndTargetId(Long authorId, Long targetId);

    ArrayList<Message> findByAuthorIdAndTargetId(Long authorId, Long targetId);

    ArrayList<Message> findByAuthorIdOrTargetId(Long authorId, Long targetId);

    

}
