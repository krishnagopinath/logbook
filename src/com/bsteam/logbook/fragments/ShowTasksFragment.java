package com.bsteam.logbook.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bsteam.logbook.data.*;

import com.bsteam.logbook.R;

public class ShowTasksFragment extends Fragment {

	@Override
	public void onResume() {
		UpdateList(getView());
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.frg_showtasks, container,
				false);

		UpdateList(rootView);
		return rootView;
	}

	public void UpdateList(View rootView) {
		SQLiteAdapter db = new SQLiteAdapter(getActivity());

		List<TaskType> tasks = db.getAllTasks();

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (TaskType task : tasks) {
			// "TaskName", "TaskDate", "TaskId"
			HashMap<String, String> temp2 = new HashMap<String, String>();
			temp2.put("TaskName", task.TaskType);
			temp2.put("TaskDate", task.TaskDate.toString());
			list.add(temp2);
		}

		if (tasks.size() > 0) {

			SimpleAdapter adapter = new SimpleAdapter(getActivity(), list,
					R.layout.custom_listview_row, new String[] { "TaskName",
							"TaskDate" }, new int[] { R.id.text1, R.id.text2 }

			);

			ListView listview = (ListView) rootView
					.findViewById(R.id.listView1);

			listview.setAdapter(adapter);
		}

	}
}
