package mitras.test.teppi.model;

import org.springframework.util.StringUtils;

/**
 * Ajax response body for ajax request
 * 
 * @author TIEP KNIGHT
 *
 */

public class AjaxResponseBody {

	private String msg;
	private Object data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void addMsg(String text) {
		if (StringUtils.isEmpty(msg)) {
			setMsg(text);
		} else {
			setMsg(msg + "<br />" + text);
		}
	}

}