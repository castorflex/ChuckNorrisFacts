package fr.isima.chuckNorrisFactsV3.Utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import fr.isima.chuckNorrisFactsV3.entities.Fact;


public class JSONParser  {

	public Fact getFactFromJSON(JSONObject jsonObject) throws JSONException{
		Fact ret = null;
		int id = -1;
		String fact = "";
		List<String> categories = new ArrayList<String>();
		
		
		String type = jsonObject.getString("type");

		if(type.equals("success")){
			JSONObject values = jsonObject.getJSONObject("value");

			id = values.getInt("id");

			fact = values.getString("joke");

			JSONArray jsonCategories = values.getJSONArray("categories");

			
			for (int i = 0 ; i < jsonCategories.length() ; ++i) {
				Log.i(this.getClass().toString(), "6 ");

				categories.add(jsonCategories.getString(i));
			}
			ret = new Fact(id, fact, categories);
		}
		return ret;		
	}
	public Fact getFactFromJSONString(String jsonString) throws JSONException{
		return getFactFromJSON(new JSONObject(jsonString));
	}

}

