import org.json.*;

import java.util.*;
import java.net.*;
import java.io.*;

public class Submit{
    public static void main(String[] args)throws IOException{
	//arbitrary?
	BufferedReader oauthKeyFile = new BufferedReader(new FileReader("oauth.txt"));
	String oauthKey = oauthKeyFile.readLine();
	Scanner in = new Scanner(System.in);	
	//changed to use oauth key. remove this stuff and replace with the post methods described on Canvas's API
	JSONArray courseJSON = new JSONArray(httpGet(new URL("https://canvas.instructure.com/api/v1/courses?access_token=" + oauthKey)));
	System.out.println("Courses you are enrolled in");
	for (int i = 0; i < courseJSON.length(); i++){
	    JSONObject course = courseJSON.getJSONObject(i);
	    System.out.println(course.get("name") + ": " + course.get("id"));
	}
	System.out.print("Enter the course ID you want to submit for: ");
	String courseID = in.next();
	JSONArray assignmentJSON = new JSONArray(httpGet(new URL("https://utexas.instructure.com/api/v1/courses/" + courseID + "/assignments?access_token=" + oauthKey)));
	System.out.println("Assignments you can submit to");
	for (int i = 0; i < assignmentJSON.length(); i++){
	    JSONObject assignment = assignmentJSON.getJSONObject(i);
	    if (!assignment.getBoolean("locked_for_user")) System.out.println(assignment.get("name") + ": " + assignment.get("id"));
	}
	System.out.print("Enter the assignment ID you want to submit for: ");
	String assignmentID = in.next();
	System.out.print("Enter the directory that contains your filesi: ");
	File dir = new File(in.next());
	while (!dir.isDirectory()){
	    System.out.println("The path you entered is not a directory: ");
	    dir = new File(in.next());
	}
	//make into a method for epic recursion?
	for (File fileName : dir.listFiles()){
	    if (!fileName.isDirectory()){
		System.out.println("Do you want to upload " + fileName " ? [Y/N]");
		String response = in.next();
		if (response.toUpperCase(response).equals("Y")){
		    //code for posting file;
		}
	    }
	}
	//http post method to post assignment
	System.out.println("Done");
    }

    public static String httpGet(URL authRequest) throws IOException{
	URLConnection authRequestConnection = authRequest.openConnection();
	InputStream authRequestResponse = authRequestConnection.getInputStream();
	int inputByte = authRequestResponse.read();
	String rawJSON = "";	
	while (inputByte != -1){
	   rawJSON += (char)inputByte;
	   inputByte = authRequestResponse.read();
	}
	return rawJSON;
    }
}
