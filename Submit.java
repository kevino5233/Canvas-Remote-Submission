import java.net.*;
import java.io.*;

public class Submit{
    public static void main(String[] args)throws IOException{
	//arbitrary?
	BufferedReader oauthKeyFile = new BufferedReader(new FileReader("oauth.txt"));
	String oauthKey = oauthKeyFile.readLine();
	System.out.println(oauthKey);
	
	//changed to use oauth key. remove this stuff and replace with the post methods described on Canvas's API
	
	URL authRequest = new URL("https://utexas.instructure.com/login/oauth2/auth?");
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
