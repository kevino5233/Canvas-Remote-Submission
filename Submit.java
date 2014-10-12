import java.net.*;
import java.io.*;

public class Submit{
    public static void main(String[] args)throws IOException{
	//arbitrary?
	int client_id = 111;
	String response_type = "code";
	String redirect_uri = "./";
	String state = "CRS";
	String query = String.format("client_id=%d&response_type=%s&redirect_uri=%s&state=%s",
	    client_id,
	    response_type,
	    redirect_uri,
	    state);
	URL authRequest = new URL("https://utexas.instructure.com/login/oauth2/auth?" + query);
	System.out.println(authRequest);
	URLConnection authRequestConnection = authRequest.openConnection();
	InputStream authRequestResponse = authRequestConnection.getInputStream();
	int inputByte = authRequestResponse.read();
	while (inputByte != -1){
	   System.out.println((char)inputByte);
	   inputByte = authRequestResponse.read();
	}

    }
}
