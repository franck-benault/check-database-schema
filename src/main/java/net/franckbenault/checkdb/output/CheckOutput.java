package net.franckbenault.checkdb.output;

import java.util.ArrayList;
import java.util.List;

public class CheckOutput {
	
	private	ResultCode resultCode;
	private List<String> messages;
	
	public CheckOutput() {
		resultCode = ResultCode.OK;
		messages = new ArrayList<>();
	}
	
	public void addLine(OutputLine line) {
		if (line.getMessage()!=null) 
			messages.add(line.getMessage());
		if(resultCode== ResultCode.OK)
			resultCode= line.getResultCode();
		
		if(resultCode== ResultCode.WARN && line.getResultCode()==ResultCode.ERROR)
			resultCode= line.getResultCode();
	}
	
	public ResultCode getResultCode() {
		return resultCode;
	}

	public List<String> getMessages() {
		return messages;
	}



}
