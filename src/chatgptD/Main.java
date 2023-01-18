package chatgptD;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Started");
		URL url = new URL("https://api.openai.com/v1/completions");
		
		String query=new String();
		//query="Decide whether a Tweet's sentiment is positive, neutral, or negative. Tweet: I loved the new Batman movie! Sentiment:";
		query="How to design operations dashboard using power BI ?";
		StringBuffer request=new StringBuffer();
		request.append("{  "
				+ " \"model\" : \"text-davinci-003\",\n  "
				+ " \"prompt\":  \""+ query+"\",\n"
				+ " \"temperature\" : 0,\n");
		request.append("");
		request.append("  \"max_tokens\": 60,\n");
		request.append("  \"top_p\": 1,\n");
		request.append("  \"frequency_penalty\": 0.5,\n");
		request.append("  \"presence_penalty\": 0}");
		System.out.println(request.toString());
		
				
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("Content-Type", "application/json");
		httpConn.setRequestProperty("Authorization", "Bearer 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");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write(request.toString());
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
		
		s.close();
	}
}


