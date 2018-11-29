package com.fujitsu.actions.gui;

import java.util.ArrayList;

public class ActionMethod {

	private ArrayList<String> returnTypes=null;
	public String returnName="Output UDA:";
	private ArrayList<String> parameterNames=null;

	// Type should be one of DataItemRef.TYPE_* 
	public void addReturnType(String type){
		if (null==returnTypes){
			returnTypes=new ArrayList<String>(); 
		}
		returnTypes.add(type);
	}

	public int getNumberOfReturnTypes(){
		if (null==returnTypes){
			return 0; 
		}
		return returnTypes.size();
	}

	public String getReturnType(int index){
		if (null==returnTypes){
			return ""; 
		}
		return returnTypes.get(index);
	}

	
	public void addParameterName(String name){
		if (null==parameterNames){
			parameterNames=new ArrayList<String>(); 
		}
		parameterNames.add(name);
	}
	
	public int getNumberOfParameters(){
		if (null==parameterNames){
			return 0; 
		}
		return parameterNames.size();
	}

	public String getParameterName(int index){
		if (null==parameterNames){
			return ""; 
		}
		return parameterNames.get(index);
	}


}
