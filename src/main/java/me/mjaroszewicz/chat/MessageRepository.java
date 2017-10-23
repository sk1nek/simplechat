package me.mjaroszewicz.chat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAllByAuthorId(Long authorId);
    Message findById(Long id);

}
