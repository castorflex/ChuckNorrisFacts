package fr.isima.chuckNorrisFactsV3.Model;

import java.io.IOException;
import java.util.Observable;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.os.AsyncTask;

import fr.isima.chuckNorrisFactsV3.Utils.JSONParser;
import fr.isima.chuckNorrisFactsV3.entities.Fact;
import fr.isima.chuckNorrisFactsV3.services.IServiceFacts;
import fr.isima.chuckNorrisFactsV3.services.ServiceFacts;

public class FactModel extends Observable {
	private Fact mCurrentFact;
	private static volatile FactModel instance = null;

	private boolean mRetrieving;

	private FactModel() {
		mRetrieving = false;
	}

	public final static FactModel getInstance() {
		if (FactModel.instance == null) {
			synchronized (FactModel.class) {
				if (FactModel.instance == null) {
					FactModel.instance = new FactModel();
				}
			}
		}
		return FactModel.instance;
	}

	public boolean isRetrieving() {
		return mRetrieving;
	}

	public void setRetrieving(boolean mRetrieving) {
		this.mRetrieving = mRetrieving;

		setChanged();
		notifyObservers();
	}

	public Fact getCurrentFact() {
		return mCurrentFact;
	}

	public void setCurrentFact(Fact mCurrentFact) {
		this.mCurrentFact = mCurrentFact;

		setChanged();
		notifyObservers();
	}

	// public void goToNextFact(){
	// setCurrentFact(ListFactModel.getInstance().getNextFact(mCurrentFact));
	// }

	// public void goToPreviousFact(){
	// setCurrentFact(ListFactModel.getInstance().getPreviousFact(mCurrentFact));
	// }

	public void goToRandomFact() {
		// setCurrentFact(ListFactModel.getInstance().getRandomFact());
		RetrieveFactTask task = new RetrieveFactTask();
		task.execute();

	}

	//
	// public void setToNull() {
	// mCurrentFact = null;
	// }

	class RetrieveFactTask extends AsyncTask<Integer, Integer, Fact> {

		@Override
		protected void onPreExecute() {
			setRetrieving(true);
			super.onPreExecute();
		}

		@Override
		protected Fact doInBackground(Integer... params) {
			IServiceFacts service = new ServiceFacts();
			Fact f = null;
			try {
				String strFact = service.getOneRandomFact();
				JSONParser parser = new JSONParser();
				f = parser.getFactFromJSONString(strFact);

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return f;

		}

		@Override
		protected void onPostExecute(Fact result) {
			setRetrieving(false);
			setCurrentFact(result);
			super.onPostExecute(result);
		}
	}

}
