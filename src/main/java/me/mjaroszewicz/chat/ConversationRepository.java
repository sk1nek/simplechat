package me.mjaroszewicz.chat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    List<Conversation> findAllByParticipants(Long participantId);

}
