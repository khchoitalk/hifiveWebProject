package user.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PwdWrapper extends HttpServletRequestWrapper{

	public PwdWrapper(HttpServletRequest request) {
		super(request);		
	}
	
	@Override
	public String getParameter(String name){
		String value = null;
		
		if(name != null && name.equals("userpwd1") || name.equals("userpw") || name.equals("nowpw") || name.equals("changepw") || name.equals("chpw")){
			value = getSha512(super.getParameter(name));
		}else{
			 value = super.getParameter(name);
		}
		
		return value;
	}

	private String getSha512(String userpwd1) {
		String Pwd = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] pwdValues = userpwd1.getBytes(Charset.forName("UTF-8"));
			md.update(pwdValues);
			Pwd = Base64.getEncoder().encodeToString(pwdValues);
			System.out.println(Pwd);
			
		} catch (Exception e) {
			System.out.println("Sha512 error...");
			e.printStackTrace();
		}
		
		return Pwd;
	}

}
