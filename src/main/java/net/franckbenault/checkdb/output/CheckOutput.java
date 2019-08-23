package net.franckbenault.checkdb.output;

import java.util.ArrayList;
import java.util.List;

public class CheckOutput {
	
	@Override
	public String toString() {
		return "CheckOutput [resultCode=" + resultCode + ", messages=" + messages + "]";
	}

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

	public void addOutput(CheckOutput output) {
		if (output.getMessages()!=null) 
			messages.addAll(output.getMessages());
		if(resultCode== ResultCode.OK)
			resultCode= output.getResultCode();
		
		if(resultCode== ResultCode.WARN && output.getResultCode()==ResultCode.ERROR)
			resultCode= output.getResultCode();
		
	}



}
