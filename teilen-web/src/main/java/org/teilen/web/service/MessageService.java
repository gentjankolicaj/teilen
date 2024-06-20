package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Message;
import org.teilen.web.model.Team;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
public interface MessageService {


  List<Message> getAll() throws Exception;

  List<Message> getAllById(List<Long> ids) throws Exception;

  Message getById(Long id) throws Exception;

  Message create(Message entity) throws Exception;

  List<Message> createAll(List<Message> entities) throws Exception;

  Message edit(Message entity) throws Exception;

  List<Message> editAll(List<Message> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Message entity) throws Exception;

  void deleteAll(List<Message> entities) throws Exception;

  boolean existById(Long id) throws Exception;


  List<Message> getAllDeleted() throws Exception;

  List<Message> getAllUnDeleted() throws Exception;

  List<Message> getBySenderId(Long senderId) throws Exception;

  List<Message> getByReceiverId(Long receiverId) throws Exception;

  List<Message> getByTeamId(Long teamId) throws Exception;

  int deleteBySenderId(Long senderId) throws Exception;

  int deleteByReceiverId(Long receiverId) throws Exception;

  int deleteByTeamId(Long teamId) throws Exception;


  Message sendMessage(User sender, User receiver, String message) throws Exception;

  Message sendMessage(User sender, Team team, String message) throws Exception;

  Message sendMessage(Message message) throws Exception;

  Message sendMessage(User sender, String receiverId, String message)
      throws Exception;


}
