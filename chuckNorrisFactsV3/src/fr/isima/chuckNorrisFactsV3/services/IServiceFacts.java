package fr.isima.chuckNorrisFactsV3.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface IServiceFacts {
	
	public String getOneRandomFact() throws ClientProtocolException, IOException;

}
