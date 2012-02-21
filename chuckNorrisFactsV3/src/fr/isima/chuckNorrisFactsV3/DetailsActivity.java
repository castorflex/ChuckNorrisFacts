package fr.isima.chuckNorrisFactsV3;

import java.util.Observable;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.isima.chuckNorrisFactsV3.Model.FactModel;
import fr.isima.chuckNorrisFactsV3.entities.Fact;

public class DetailsActivity extends AbstractBasicActivity implements
		OnClickListener {

	static final String KEY_FACT = "fact_key";

	private FactModel mModel;

	private ProgressDialog mProgressDialog;
	private EditText et_id;
	private EditText et_fact;
	private Button b_random;

	public void onClick(View v) {
		if (v.getId() == b_random.getId()) {
			mModel.goToRandomFact();
		}

	}

	public void update(Observable observable, Object data) {
		if (mModel.isRetrieving()) {
			mProgressDialog = ProgressDialog.show(this, getString(R.string.dialog_title),
					getString(R.string.dialog_message));
		} else if (mProgressDialog != null) {
			mProgressDialog.dismiss();
		}

		Fact f = mModel.getCurrentFact();

		if (f != null) {
			String str_id = f.getId() + "";
			if (f.getId() == -1)
				str_id = getString(R.string.unknown_fact);
			et_id.setText(str_id);
			et_fact.setText(f.getFact());
		}
	}

	// //////////////////////////////BASIC
	// ACTIVITY///////////////////////////////////
	@Override
	public void initModels() {
		mModel = FactModel.getInstance();
	}

	@Override
	public void initObservers() {
		mModel.addObserver(this);

	}

	@Override
	public void setBindings() {
		et_id = (EditText) findViewById(R.id.editText_details_id);
		et_fact = (EditText) findViewById(R.id.editText_details_fact);
		b_random = (Button) findViewById(R.id.button_random);
	}

	@Override
	public void registerListeners() {
		b_random.setOnClickListener(this);
	}

	@Override
	public void init() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.details_activity;
	}

	@Override
	public void deleteObservers() {
		mModel.deleteObserver(this);

	}

	@Override
	public void deleteModels() {
		// mModel.setToNull();
		mModel = null;
	}

	// ////////////////////////////////////////////////////////////////////
}
