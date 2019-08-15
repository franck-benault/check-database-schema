package net.franckbenault.checkdb.output;


public class OutputLine {

	private	ResultCode resultCode;
	private String message;
	
	public ResultCode getResultCode() {
		return resultCode;
	}

	public String getMessage() {
		return message;
	}
	
	public OutputLine() {
		resultCode = ResultCode.OK;
	}

	public OutputLine(ResultCode resultCode, String message) {
		this.resultCode = resultCode;
		this.message = message;
	}

	@Override
	public String toString() {
		return "OutputLine [resultCode=" + resultCode + ", message=" + message + "]";
	}


}
