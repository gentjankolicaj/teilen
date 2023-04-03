package org.teilen.web.dao;

import org.teilen.web.model.Message;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface MessageDao extends CrudDao<Message, Long> {


    public abstract List<Message> customFindAllDeleted() throws Exception;

    public abstract List<Message> customFindAllUnDeleted() throws Exception;

    public abstract List<Message> customFindBySenderId(Long senderId) throws Exception;

    public abstract List<Message> customFindByReceiverId(Long receiverId) throws Exception;

    public abstract List<Message> customFindByTeamId(Long teamId) throws Exception;

    public abstract int customDeleteBySenderId(Long senderId) throws Exception;

    public abstract int customDeleteByReceiverId(Long receiverId) throws Exception;

    public abstract int customDeleteByTeamId(Long teamId) throws Exception;

}
