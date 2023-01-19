package login_registro;
import java.util.HashMap;

public class IDandPasswords {


//BD falta
	HashMap<String,String> logininfo = new HashMap<String,String>();

	

	IDandPasswords(){

		

		logininfo.put("Aitor","1234");


	}

	

	public HashMap getLoginInfo(){

		return logininfo;

	}

}