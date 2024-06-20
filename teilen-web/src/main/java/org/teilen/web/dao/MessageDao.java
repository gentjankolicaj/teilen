package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Message;


/**
 * @author gentjan kolicaj
 */
public interface MessageDao extends CrudDao<Message, Long> {


  List<Message> customFindAllDeleted() throws Exception;

  List<Message> customFindAllUnDeleted() throws Exception;

  List<Message> customFindBySenderId(Long senderId) throws Exception;

  List<Message> customFindByReceiverId(Long receiverId) throws Exception;

  List<Message> customFindByTeamId(Long teamId) throws Exception;

  int customDeleteBySenderId(Long senderId) throws Exception;

  int customDeleteByReceiverId(Long receiverId) throws Exception;

  int customDeleteByTeamId(Long teamId) throws Exception;

}
