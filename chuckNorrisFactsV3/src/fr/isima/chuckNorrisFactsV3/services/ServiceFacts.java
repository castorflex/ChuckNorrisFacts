package fr.isima.chuckNorrisFactsV3.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;

import android.util.Log;
import fr.isima.chuckNorrisFactsV3.Utils.Connections;

public class ServiceFacts implements IServiceFacts {

	public static final String RANDOM_URL = "http://api.icndb.com/jokes/random";

	public String getOneRandomFact() throws ClientProtocolException, IOException {
		String ret = null;

		HttpEntity entity = Connections.getHttpEntity(RANDOM_URL);

		if (entity != null) {
			InputStream is = entity.getContent();

			// lecture du retour au format json
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(is));
			StringBuilder stringBuilder = new StringBuilder();

			String lineRead = bufferedReader.readLine();
			while (lineRead != null) {
				stringBuilder.append(lineRead + "\n");
				lineRead = bufferedReader.readLine();
			}
			bufferedReader.close();

			ret = stringBuilder.toString();
		}
		Log.i(this.getClass().toString(), "returning " + ret);
		return ret;

	}

}
