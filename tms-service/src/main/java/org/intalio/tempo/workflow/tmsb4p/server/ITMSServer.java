package org.intalio.tempo.workflow.tmsb4p.server;

import java.util.List;
import java.util.Set;

import org.apache.xmlbeans.XmlObject;
import org.intalio.tempo.workflow.auth.AuthException;
import org.intalio.tempo.workflow.taskb4p.Attachment;
import org.intalio.tempo.workflow.taskb4p.AttachmentInfo;
import org.intalio.tempo.workflow.taskb4p.Comment;
import org.intalio.tempo.workflow.taskb4p.Task;
import org.intalio.tempo.workflow.tms.InvalidTaskStateException;
import org.intalio.tempo.workflow.tms.TMSException;
import org.intalio.tempo.workflow.tms.UnavailableTaskException;
import org.intalio.tempo.workflow.tmsb4p.server.dao.GenericRoleType;

import com.intalio.wsHT.api.TStatus;
import com.intalio.wsHT.api.xsd.TTime;

public interface ITMSServer {
    public void create(Task task, String participantToken) throws TMSException;
    public void remove(String participantToken, String taskId) throws TMSException;
    public List<Task> getMyTasks(String participantToken, String taskType, String genericHumanRole, String workQueue, TStatus.Enum[] statusList, String whereClause, String createdOnClause, int maxTasks) throws TMSException;
    public List<Task> query(String participantToken, String selectClause, String whereClause, String orderByClause, int maxTasks, int taskIndexOffset) throws TMSException;
	public void stop(String participantToken, String identifier) throws TMSException;
	public void start(String participantToken, String identifier) throws TMSException;
	public void claim(String participantToken, String identifier) throws TMSException;
	public void release(String participantToken, String identifier) throws TMSException;
	public void complete(String participantToken, String identifier, XmlObject xmlObject) throws TMSException;
	public void fail(String participantToken, String identifier,
			String faultName, XmlObject faultData) throws TMSException;
	public void resume(String participantToken, String identifier) throws TMSException;
	public void skip(String participantToken, String identifier) throws TMSException;
	public void forward(String participantToken, String identifier) throws TMSException;
	public void delegate(String participantToken, String identifier) throws TMSException;
	
	public void setPriority(String participantToken, String identifier, int priority) throws AuthException, UnavailableTaskException;
	public void addAttachment(String participantToken, String identifier, String attachmentName, String accessType, String value)
    throws AuthException, UnavailableTaskException;
	public List<AttachmentInfo> getAttachmentInfos(String participantToken, String identifier) throws AuthException;
	public List<Attachment> getAttachments(String participantToken, String identifier, String attachmentName) throws AuthException;
	public void deleteAttachments(String participantToken, String identifier, String attachmentName) throws AuthException;
	public void addComment(String participantToken, String identifier, String text) throws AuthException;
	public List<Comment> getComments(String participantToken, String identifier) throws AuthException;
	public Task getTaskByIdentifier(String participantToken, String identifier) throws AuthException, UnavailableTaskException;

	public void suspendUntil(String participantToken, String identifier,
			TTime time);
	public void suspend(String participantToken, String identifier) throws TMSException;

	public void activate(String participantToken, String identifier) throws AuthException, InvalidTaskStateException, UnavailableTaskException;
	public void nominate(String participantToken, String identifier, List<String> principals, boolean isUser) throws AuthException, InvalidTaskStateException, UnavailableTaskException;
	public void setGenericHumanRole(String participantToken, String identifier, GenericRoleType roleType, List<String> principals, boolean isUser) throws AuthException, UnavailableTaskException;

}
