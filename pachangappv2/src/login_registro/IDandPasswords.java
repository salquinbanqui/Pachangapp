package login_registro;
import java.util.HashMap;

public class IDandPasswords {



	HashMap<String,String> logininfo = new HashMap<String,String>();

	

	IDandPasswords(){

		

		logininfo.put("Aitor","123");


	}

	

	public HashMap getLoginInfo(){

		return logininfo;

	}

}