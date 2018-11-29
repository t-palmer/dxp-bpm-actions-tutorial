package com.fujitsu.actions.gui;


import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.fujitsu.iflow.common.ErrorMessage;
import com.fujitsu.iflow.common.ErrorMessageParam;
import com.fujitsu.iflow.common.Utils;
import com.fujitsu.iflow.gui.actioneditor.ActionEditorField;
import com.fujitsu.iflow.gui.actioneditor.GraphicalActionEditorImpl;
import com.fujitsu.iflow.gui.planview.GVApplet;
import com.fujitsu.iflow.gui.util.AppletList;
import com.fujitsu.iflow.gui.util.WindowUtil;
import com.fujitsu.iflow.model.util.Log;
import com.fujitsu.iflow.model.util.ModelException;
import com.fujitsu.iflow.model.workflow.DataItemRef;
import com.fujitsu.iflow.model.workflow.JavaAction;

public abstract class ActionsGUIBase  extends GraphicalActionEditorImpl{
	
    protected JPanel assignPanel;
    protected JScrollPane descSpane;
	public String callingMethod;

	private ActionMethod actionMethod= null;

	private ArrayList<ActionEditorField> actionEditorFields=null; 

	// If there is an SEC this will be set to 1
	private int secExists=0;

	
	// This is the label and combo box used for the return value of the action.
	protected JLabel lblTarget;
	protected JComboBox cbxReturnUDA;

	static private int DIALOG_Y_BASE=130; 
	static private int DIALOG_Y_INCREMENT=32; 

    // Called by user clicking add or edit from Studio.
    public void show() {
    	System.out.println("ActionsGUIBase.show()");
    	
    	callingMethod= action.getMethodName();
    	actionMethod= getActionMethod();
        buildUI();
        
        try {
        	populateValues();
        }
        catch (ModelException mex) {
            WindowUtil.errDlg(mex);
        }
        catch (Exception ex) {
            ModelException me = new ModelException(ErrorMessage.INVALID_INPUT_PARAMETER ,ex);
            WindowUtil.errDlg(me);
        }
        enableControls();
        showDialog();
    }


	private void buildUI() {
    	System.out.println("ActionsGUIBase.buildGenericUI("+action.getMethodName()+")");
   	
        createDialog(getDialogName(), 550, 450);

        assignPanel = new JPanel();
        descSpane =  new JScrollPane();

        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.addKeyListener(new KeyAdapter()
        {
            public void keyReleased(KeyEvent me)
            {
               validateActionDescription(me);
            }
        });

        assignPanel.setLayout(null);
        addTab(assignPanel);
        assignPanel.setBounds(0,0,520,290);
        lblName.setText(msgCat.getMsg("Name:"));
        assignPanel.add(lblName);
        lblName.setBounds(10,4,100,25);
        assignPanel.add(txtName);
        txtName.setBounds(118,9,326,25);
        lblDescription.setText(msgCat.getMsg("Description:"));
        assignPanel.add(lblDescription);
        lblDescription.setBounds(10,39,100,25);
        assignPanel.add(descSpane);
        descSpane.getViewport().setView(txtDescription);
        descSpane.setBounds(118,44,326,75);
		
        addParameterEditorFields();
        addReturnUDAComboBox();
	}


	protected abstract String getDialogName();


    // Use this check if the Action is new or editing an existing one.
    // Then it populates the values to the GUI.
    protected void populateValues() throws Exception
    {
    	System.out.println("ActionsGUIBase.populateGenericValues()");
        txtName.setText(action.getActionName());
        txtDescription.setText(action.getActionDescription());
        if (brandNewAction == false ) {
        	// populate each action editor field from the parameter  
        	for (int i=0;i<actionEditorFields.size();i++){
        		ActionEditorField editorField=actionEditorFields.get(i); 
        		if (null!=editorField){
        			setActionEditorFieldText(actionEditorFields.get(i),i);
        		}
        	}
    		if (null!=cbxReturnUDA){
    			cbxReturnUDA.setSelectedItem(action.getReturnValueUDAName());
    		}
        }
    }

   
    protected void enableControls() {
    	System.out.println("ActionsGUIBase.enableControls()");
        txtName.setEnabled(inEditMode);
        txtDescription.setEnabled(inEditMode);

        txtName.setDisabledTextColor(java.awt.Color.black);
        txtDescription.setDisabledTextColor(java.awt.Color.black);
        btnOk.setEnabled(inEditMode);

    	for (int i=0;i<actionEditorFields.size();i++){
    		ActionEditorField txtValue = actionEditorFields.get(i);
    		if (null!=txtValue){
	        	txtValue.setEnabled(inEditMode);
	            txtValue.setDisabledTextColor(java.awt.Color.black);
    		}
    	}
        
		if (null!=cbxReturnUDA){
			cbxReturnUDA.setEnabled(inEditMode);
		}
    }

    // Update the Action data after user clicks on OK 
    protected void updateAction() {
    	System.out.println("ActionsGUIBase.updateAction()");
        action.setActionName(txtName.getText()) ;
        action.setActionDescription(txtDescription.getText());

        action.setArgumentsUDANames(getArgumentsFromGUI().toString());
		if (null!=cbxReturnUDA){
			action.setReturnValueUDAName((String)cbxReturnUDA.getSelectedItem());
		}
    }


	// This should build the StringBuffer with the parameters to the server method.
	private StringBuffer getArgumentsFromGUI() {
        StringBuffer inputList = new StringBuffer();
    	for (int i=0;i<actionEditorFields.size();i++){
    		ActionEditorField txtValue = actionEditorFields.get(i);

    		// If txtValue is null, then it is an SEC
    		if (null==txtValue){
    			inputList.append(Utils.makeETags("sec", false));
    		}
    		else{
        		inputList.append(Utils.makeETags(txtValue.getExpression(), false));
    		}
    	}
        return inputList;
	}

    
    // This returns a list of UDAs for actions to use in the return drop down
    public String[] getReturnUDAList() {
    	System.out.println("ActionsGUIBase.getReturnUDAList()");
    	ArrayList<String> udaList= new ArrayList<String>();
        try {
            if (plan != null) {
                DataItemRef[] drefs;
                if (WindowUtil.getSettingDataItemInfo() == null){
                    drefs = plan.getDataItemRefs();
                }
                else{
                    drefs = WindowUtil.getSettingDataItemInfo().getDataItemRefs();
                }
                
                if (drefs != null) {
                    for (DataItemRef d: drefs){
                    	for (int i=0;i<actionMethod.getNumberOfReturnTypes();i++){
                    		String udaType=actionMethod.getReturnType(i);
                    		if ((null==udaType)||udaType.equalsIgnoreCase("")||udaType.equalsIgnoreCase(d.getType())){
                    			udaList.add(d.getName());
                    			// This line assures the UDA only gets added once
                    			i=actionMethod.getNumberOfReturnTypes();
                    		}
                    	}
                    }
                }
            }
        }catch (Exception e) {
            Log.println(Log.LEVEL0, "No UDAs will be populated.");
            // no need to throw Exception.
        }
        String[] strArray = new String[udaList.size()];
        udaList.toArray (strArray);
        return strArray;
    }

    protected void processActionEvent(ActionEvent event){
    	System.out.println("ActionsGUIBase.processActionEvent()");
        Object obj = event.getSource();
        if (obj == btnOk) {
            ModelException me = null;

            
        	for (int i=0;i<actionEditorFields.size();i++){
        		ActionEditorField txtValue = actionEditorFields.get(i);
                if ((null!=txtValue)&&(txtValue.getText().trim().length() == 0)) {
                    //ErrorMessage '<$0>' field cannot be empty. = 21101.
                    me = new ModelException(ErrorMessage.FIELD_CANNOT_BE_EMPTY);
//                    me.setParam(0, msgCat.getMsg("Value"));
                    me.setParam(0,actionMethod.getParameterName(i));
                    WindowUtil.errDlg(
                            msgCat.getMsg("dialog.invalidData.title"), me);
                    txtValue.requestFocus();
                    return;
                }
        	}
            
            if ((null!=cbxReturnUDA)&&(cbxReturnUDA.getSelectedIndex() == -1)) {
                //ErrorMessage '<$0>' field cannot be empty. = 21101.
                me = new ModelException(ErrorMessage.FIELD_CANNOT_BE_EMPTY);
                me.setParam(0, lblTarget.getText());
                WindowUtil.errDlg(msgCat.getMsg("dialog.invalidData.title"), me);
                cbxReturnUDA.requestFocus();
                return;
            }
            if (!validateErrorHandling()){
                return;
            }
            updateAction();
            applyErrorHandling();
            disposeDialog();
        }
        else if (obj == btnCancel) {
            disposeDialog();
        }
        else if (obj == btnHelp) {
             GVApplet ag = (GVApplet)AppletList.getApplet("GPUI");
            if (ag != null)
                ag.showHelpSection("UdaAssignmentActionEditor.html");
        }
    }
    
	protected void validateActionDescription(KeyEvent e)
    {
    	System.out.println("ActionsGUIBase.validateActionDescription()");
        if (txtDescription.getText().length() > JavaAction.MAX_ACTION_DESCRIPTION_LENGTH)
        {
            String text = txtDescription.getText();
            int cursorPos = txtDescription.getCaretPosition();

            ModelException me = new ModelException(ErrorMessage.MAX_NAME_LENGTH_EXCEEDED);
            me.setParam(0, ErrorMessageParam.MAX_LENGTH_EXCEEDED_ACTION_DESCR);
            me.setParam(1, text.substring(0, 50) + "..." );
            me.setParam(2, JavaAction.MAX_ACTION_DESCRIPTION_LENGTH);

            WindowUtil.errDlg(
                    ErrorMessage.getInstance().getString(ErrorMessage.FIELD_VALIDATION_ERROR),
                    me);
            txtDescription.setText(text.substring(0, JavaAction.MAX_ACTION_DESCRIPTION_LENGTH));
            txtDescription.requestFocus();

            if (cursorPos > JavaAction.MAX_ACTION_DESCRIPTION_LENGTH)
                txtDescription.setCaretPosition(JavaAction.MAX_ACTION_DESCRIPTION_LENGTH);
            else
                txtDescription.setCaretPosition(cursorPos);
        }
    }

    // Set the text of the given ActionEditorField from the Action parameter with the given index.
    protected void setActionEditorFieldText(ActionEditorField field,int index) throws Exception{
	    String[] list = Utils.getParametersList(action.getArgumentsUDANames());
	    String token = list[index].trim();
	    try {
	    	field.setText(token);
	    }
	    catch (Exception e) {
	        // UDA value can be set as an empty string,
	        // so if token is empty, then continue.
	        if (!token.trim().equals("")) {
	            throw e;
	        }
	    }
    }
    
    
	// This adds an ActionEditorField for each parameter of the action. 
	private void addParameterEditorFields() {
		actionEditorFields=new ArrayList<ActionEditorField>();
		
    	for (int i=0;i<actionMethod.getNumberOfParameters();i++){
    		String name=actionMethod.getParameterName(i);

    		if (name.equalsIgnoreCase("sec")){
    			// We want to ignore the sec parameters
    			secExists=1;
	            actionEditorFields.add(null);
    		}
    		else{
	    		JLabel lblSource = new JLabel();
	    		ActionEditorField txtValue = new ActionEditorField(wfs, plan, docRepos);
	
	            // BPM expression box for input of Role name
	//            lblSource.setText(msgCat.getMsg("Role:"));
	            lblSource.setText(name);
	            assignPanel.add(lblSource);
	            
	            lblSource.setBounds(10,(DIALOG_Y_BASE+(DIALOG_Y_INCREMENT*(i-secExists))),100,25);
	            assignPanel.add(txtValue);
	            txtValue.setBounds(118,(DIALOG_Y_BASE+(DIALOG_Y_INCREMENT*(i-secExists))),326,27);
	            
	            actionEditorFields.add(txtValue);
    		}
    	}
	}

	// This adds a combo box to select the output UDA for the Action. 
	private void addReturnUDAComboBox() {
    	if (actionMethod.getNumberOfReturnTypes()!=0){
	        // Combo box listing UDAs for output
	        lblTarget = new JLabel();
	        cbxReturnUDA = new JComboBox(getReturnUDAList());
	    	
//	        lblTarget.setText(msgCat.getMsg("Output UDA:"));
	        lblTarget.setText(actionMethod.returnName);
	        assignPanel.add(lblTarget);
	        lblTarget.setBounds(10,(DIALOG_Y_BASE+(DIALOG_Y_INCREMENT*(actionMethod.getNumberOfParameters()-secExists))),100,22);
	        assignPanel.add(cbxReturnUDA);
	        cbxReturnUDA.setBounds(118,(DIALOG_Y_BASE+(DIALOG_Y_INCREMENT*(actionMethod.getNumberOfParameters()-secExists))),326,25);
    	}
	}


    // Implement this method to return an ActionMethod specific to the method that this GUI class should display.
    protected abstract ActionMethod getActionMethod();
}
