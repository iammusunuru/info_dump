package info_injecter;
import java.io.*;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class injecter {
	// TODO Auto-generated method stub
	
	@SuppressWarnings("resource")
	public void inject()
	{
		String file_content="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  try {
		    //read csv here
		  System.out.println("enter client end_point name");
		  file_content = new Scanner( new File("/home/musunuru/Desktop/bootstrap.txt"), "UTF-8" ).useDelimiter("\\A").next();
           file_content = "{\"endpoint_name\":\""+br.readLine()+"\",\"booty\":"+file_content+"}";
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/inject");
			
				StringEntity input = new StringEntity(file_content);
				input.setContentType("application/json");
				postRequest.setEntity(input);
		 
				HttpResponse response = httpClient.execute(postRequest);
	 
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				}
		 
				BufferedReader br2 = new BufferedReader(
		                        new InputStreamReader((response.getEntity().getContent())));
		 
				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br2.readLine()) != null) {
					//System.out.println(output);
				}
				System.out.println("done putting info into server");
				httpClient.getConnectionManager().shutdown();
		 
			  } catch (MalformedURLException e) {
		 
				e.printStackTrace();
		 
			  } catch (Exception e) {
		 
				e.printStackTrace();
		 
			  }

	}
}
