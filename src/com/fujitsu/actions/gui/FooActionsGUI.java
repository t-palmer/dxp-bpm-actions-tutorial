package com.fujitsu.actions.gui;

import com.fujitsu.iflow.model.workflow.DataItemRef;

public class FooActionsGUI extends ActionsGUIBase
{
    
    public FooActionsGUI() {
        super();
        setDefaultNamePrefix(msgCat.getMsg("assign_uda_"));
    }

    
	@Override
	protected String getDialogName() {
		// This gets called when Studio opens a GUI for the Action
		// It should return the name that is displayed in the Dialog Title.
		return "Foo Actions";
	}


	@Override
	protected ActionMethod getActionMethod() {
		// this is called to create the Action GUI on the fly
		// We look at: callingMethod

		// Our Action class has two methods:
		// addFoo
		// appendToProcessName
        // It should match what is defined in the JavaActionDef.xml file in MethodName:
		// <MethodName>addFoo(String)</MethodName>
		// <MethodName>appendToProcessName(String,ServerEnactmentContext)</MethodName>
		
		if (callingMethod.equalsIgnoreCase("addFoo(String)")){
			return createAddFooGUI();
		} else if (callingMethod.equalsIgnoreCase("appendToProcessName(ServerEnactmentContext,String)")){
			return createAppendToProcessNameGUI();
		}

		return null;
	}

	
	private ActionMethod createAddFooGUI() {
		ActionMethod actionMethod= new ActionMethod();
		actionMethod.addParameterName("Input:");
		actionMethod.addReturnType(DataItemRef.TYPE_STRING);
		return actionMethod;
	}


	private ActionMethod createAppendToProcessNameGUI() {
		ActionMethod actionMethod= new ActionMethod();
		actionMethod.addParameterName("sec");
		actionMethod.addParameterName("Input:");
		actionMethod.addReturnType(DataItemRef.TYPE_STRING);
		return actionMethod;
	}
    
    
} // end of class.
