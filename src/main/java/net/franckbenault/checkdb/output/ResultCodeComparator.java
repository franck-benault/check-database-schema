package net.franckbenault.checkdb.output;

import java.util.Comparator;

public class ResultCodeComparator  implements Comparator<ResultCode> {

	@Override
	public int compare(ResultCode o1, ResultCode o2) {
		
		if( o1.equals(o2))
			return 0;
		else  {
			if(o1.equals(ResultCode.OK))
				return -1;
			else if(o2.equals(ResultCode.ERROR))
				return -1;
			else 
				return 1;
		}
	}

}
