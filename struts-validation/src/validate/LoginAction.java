package validate;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String a;
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	@Override
	public String execute() throws Exception {
		System.out.println("action����execute");
		if (getA().equals("admin")) {
			return SUCCESS;
		} else {
			super.addActionError("�û�������!");
			return INPUT;
		}
	}
}

