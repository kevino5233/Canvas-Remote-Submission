import org.json;
import java.net.*;
import java.io.*;

public class Submit{
    public static void main(String[] args)throws IOException{
	//arbitrary?
	BufferedReader oauthKeyFile = new BufferedReader(new FileReader("oauth.txt"));
	String oauthKey = oauthKeyFile.readLine();
	
	//changed to use oauth key. remove this stuff and replace with the post methods described on Canvas's API
	
	URL authRequest = new URL("https://canvas.instructure.com/api/v1/courses?access_token=" + oauthKey);
	URLConnection authRequestConnection = authRequest.openConnection();
	InputStream authRequestResponse = authRequestConnection.getInputStream();
	int inputByte = authRequestResponse.read();
	String courseRawJSON = "";	
	while (inputByte != -1){
	   courseRawJSON += (char)inputByte;
	   inputByte = authRequestResponse.read();
	}
	JSONObject courseJSON = new JSONObject(courseRawJSON);
	System.out.println(courseJSON);	
    }
}
