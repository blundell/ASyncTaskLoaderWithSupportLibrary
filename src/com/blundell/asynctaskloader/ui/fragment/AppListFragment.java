package com.blundell.asynctaskloader.ui.fragment;

import java.util.List;

import com.blundell.asynctaskloader.domain.AppEntry;
import com.blundell.asynctaskloader.ui.adapter.AppListAdapter;
import com.blundell.asynctaskloader.ui.loader.AppListLoader;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class AppListFragment extends ListFragment implements LoaderCallbacks<List<AppEntry>> {

	private AppListAdapter adapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setEmptyText("No applications");

		adapter = new AppListAdapter(getActivity());
		setListAdapter(adapter);

		setListShown(false);

		// Prepare the loader
		// either reconnect with an existing one or start a new one
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getActivity(), "Clicked: "+ id, Toast.LENGTH_SHORT).show();
	}

	@Override
	public Loader<List<AppEntry>> onCreateLoader(int id, Bundle args) {
		// This is called when a new loader needs to be created.
		// This sample only has one Loader with no arguments, so it is simple.
		return new AppListLoader(getActivity());
	}

	@Override
	public void onLoadFinished(Loader<List<AppEntry>> loader, List<AppEntry> data) {
		adapter.setData(data);

		// The list should now be shown
		if(isResumed()){
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<List<AppEntry>> loader) {
		// Clear the data in the adapter
		adapter.setData(null);
	}
}