package com.fujitsu.actions;

import java.io.File;
import java.util.Properties;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.fujitsu.iflow.common.EmailMessageStruct;
import com.fujitsu.iflow.model.util.IflowEnumeration;
import com.fujitsu.iflow.model.util.ModelException;
import com.fujitsu.iflow.model.workflow.XData;
import com.fujitsu.iflow.server.intf.ServerEnactmentContext;

public class TestSeverEnactmentContext implements ServerEnactmentContext{
	int priority=10;
	String[] assignees = new String[] {"UserA","UserB","UserC"};
	String[] processOwners = new String[] {"UserA","UserB","UserC"};
	String activityDescription= "Activity Description";
	String processDescription= "Process Description";
	String activityName= "Activity Name";
	String processName= "Process Name";
	String processTitle= "Process Title";
	public Properties attachments= new Properties();
	public Properties udas= new Properties();
	
	
	public TestSeverEnactmentContext(){
		attachments.put("Attachment Name", "attPath");
		udas.put("UDA1", "UDA1 Value");
		udas.put("UDA2", "UDA2 Value");
		udas.put("UDA3", "UDA3 Value");
	}

	public void addAttachment(String arg0, String arg1) throws Exception {
		System.out.println("addAttachment: "+arg0+", "+arg1);
		attachments.put(arg0, arg1);
	}

	public void addProcessXMLAttributeSubstructure(String arg0, String arg1,String arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void addProcessXMLAttributeSubstructure(String arg0, String arg1,Node arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void addProcessXMLAttributeSubstructureByIdentifier(String arg0,	String arg1, String arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void addProcessXMLAttributeSubstructureByIdentifier(String arg0,String arg1, Node arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void deleteAttachment(String arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void deleteProcessXMLAttributeSubStructure(String arg0, String arg1)	throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void deleteProcessXMLAttributeSubStructureByIdentifier(String arg0,String arg1) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void escalateActivity(String arg0) throws Exception {
		assignees = new String[1];
		assignees[0]=arg0;
	}

	public String evaluateScript(String arg0, String arg1) throws Exception {
		System.out.println("evaluateScript: "+arg0+", "+arg1);
		return "Result";
	}

	public String getActivityActor(String arg0) throws Exception {
		return assignees[0];
	}

	public String[] getActivityAssignees() throws Exception {
		return assignees;

	}

	public String getActivityDescription() throws Exception {
		return activityDescription;
	}

	public Document getActivityExtendedAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public IflowEnumeration getActivityHistory(int[] arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public int getActivityIteratorIndex() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getActivityName() throws Exception {
		return activityName;
	}

	public int getActivityPriority() throws Exception {
		return priority;
	}

	public String getActor() throws Exception {
		return assignees[0];
	}

	public String[] getAllAttachmentNames() throws Exception {
		Set<Object> s= attachments.keySet();
		return s.toArray(new String[0]);
	}

	public String[] getAllAttributeNames() throws Exception {
		Set<Object> s= udas.keySet();
		return s.toArray(new String[0]);
	}

	public String getApplicationId() throws Exception {
		return "ActionTester";
	}

	public File getApplicationResource(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getApplicationVariable(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAttachment(String arg0) throws Exception {
		return attachments.getProperty(arg0);
	}

	public String[] getChoices() throws Exception {
		return new String[] {"Choice1","Choice2","Choice3"};
	}

	public long getCurrentActivityId() throws Exception {
		return 12345;
	}

	public long getCurrentProcessId() throws Exception {
		// TODO Auto-generated method stub
		return 123456;
	}

	public String getExternalizedProcessAttribute(String arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void getExternalizedProcessAttribute(String arg0, Node arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String[] getGroupMembers(String arg0) throws Exception {
		return new String[] {"UserA","UserB","UserC"};
	}

	public Exception getLastException() {
		return null;
	}

	public String getProcessAttribute(String arg0) throws Exception {
		return udas.getProperty(arg0);
	}

	public String getProcessAttributeByIdentifier(String arg0) throws Exception {
		return udas.getProperty(arg0);
	}

	public String getProcessAttributeStringType(String arg0) throws Exception {
		return "STRING";
	}

	public int getProcessAttributeType(String arg0) throws Exception {
		return 0;
	}

	public Document getProcessDefinitionExtendedAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProcessDefinitionId() throws Exception {
		return "Process Definition ID";
	}

	public String getProcessDefinitionName() throws Exception {
		return "Process Definition Name";
	}

	public String getProcessDescription() {
		return processDescription; 
	}

	public IflowEnumeration getProcessHistory(int[] arg0) throws Exception {
		return null;
	}

	public String getProcessInitiator() throws Exception {
		return processOwners[0];
	}

	public String getProcessName() {
		return processName;
	}

	public String[] getProcessOwners() throws Exception {
		return processOwners;
	}

	public int getProcessPriority() {
		return priority;
	}

	public String getProcessTitle() {
		return processTitle;
	}

	public Document getProcessXMLAttributeDocument(String arg0)throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Document getProcessXMLAttributeDocumentByIdentifier(String arg0)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Document getProcessXMLAttributeDocumentInternal(String arg0)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProcessXMLAttributeElementValue(String arg0, String arg1)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getProcessXMLAttributeSubstructure(String arg0, String arg1)
			throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getProcessXMLAttributeSubstructureByIdentifier(String arg0,
			String arg1) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTenantName() throws Exception {
		return "default";
	}

	public String joinString(String[] arg0) throws Exception {
		String list= "";
		for(int i=0;i<arg0.length;i++){
			// if this is the first one, don't add the delimiter
	    	if (i!=0){list+=",";}
	    	list+=arg0[i];
		}
		return list;
	}

	public void makeChoiceAction(long arg0, String arg1) throws Exception {
		System.out.println("makeChoiceAction: "+arg0+", "+arg1);
	}

	public String[] resolveRelationship(String arg0, String arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void sendEmail(EmailMessageStruct arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sendEmail(String arg0, String arg1, String arg2, String arg3,
			String arg4, String arg5) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sendEmail(String arg0, String arg1, String arg2, String arg3,
			String arg4, String arg5, String arg6) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void sendEmailwithMimeType(String arg0, String arg1, String arg2,
			String arg3, String arg4, String arg5, String arg6)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setActivityAssignees(String[] arg0) throws Exception {
		assignees= arg0;		
	}

	public void setActivityPriority(int arg0) throws Exception {
		priority=arg0;
	}

	public void setBreakLoop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setContinueLoop() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setOwners(String[] arg0) throws Exception {
		processOwners=arg0;
	}

	public void setProcessAttribute(String arg0, String arg1) throws Exception {
		udas.setProperty(arg0, arg1);
	}

	public void setProcessAttributeByIdentifier(String arg0, String arg1)throws Exception {
		udas.setProperty(arg0, arg1);
	}

	public void setProcessDescription(String arg0) throws Exception {
		processDescription=arg0;	
	}

	public void setProcessName(String arg0) throws Exception {
		processName=arg0;
	}

	public void setProcessOwners(String[] arg0) throws Exception {
		processOwners=arg0;
	}

	public void setProcessPriority(int arg0) throws Exception {
		priority=arg0;
	}

	public void setProcessTitle(String arg0) throws Exception {
		processTitle=arg0;
	}

	public void setProcessXMLAttribute(String arg0, Document arg1)
			throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeByIdentifier(String arg0, Document arg1)
			throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeElementValue(String arg0, String arg1,
			String arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeElementValueByIdentifier(String arg0,
			String arg1, String arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeSubstructure(String arg0, String arg1,
			Node arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeSubstructure(String arg0, String arg1,
			String arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeSubstructureByIdentifier(String arg0,
			String arg1, Node arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setProcessXMLAttributeSubstructureByIdentifier(String arg0,
			String arg1, String arg2) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public String[] splitString(String arg0) throws Exception {
		return arg0.split(",");
	}

	public void validateProcessXMLAttributeValue(String arg0)
			throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void validateProcessXMLAttributeValueByIdentifier(String arg0)
			throws ModelException {
		// TODO Auto-generated method stub
		
	}
	
	// **********************************************************
	// These are inherited from XData
	// **********************************************************
	public void copy(String arg0, XData arg1) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public int count(String arg0) throws ModelException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getValue(String arg0) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public XData getXData(String arg0) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(String arg0) throws ModelException {
		// TODO Auto-generated method stub
		
	}

	public void setValue(String arg0, String arg1) throws ModelException {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void eliminateUsersFromActivityAssignees(String[] arg0)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void selectRandomAssignee() throws Exception {
        // TODO Auto-generated method stub
        
    }

	
	
}
