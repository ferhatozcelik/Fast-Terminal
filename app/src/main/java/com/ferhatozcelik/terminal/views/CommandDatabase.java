package com.ferhatozcelik.terminal.views;

import java.util.ArrayList;

import com.ferhatozcelik.terminal.model.FastButton;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class CommandDatabase {

	SharedPreferences prefs;

	public CommandDatabase(Context context) {
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void saveCommandList(ArrayList<FastButton> fastButtonList) {
		String jsonString = new Gson().toJson(fastButtonList);
		prefs.edit().putString("commands", jsonString).apply();
	}

	public ArrayList<FastButton> getCommandList() {
		String getLocalData = prefs.getString("commands", null);
		if (getLocalData != null) {
			return new Gson().fromJson(getLocalData, new TypeToken<ArrayList<FastButton>>() {
			}.getType());
		} else {
			return new ArrayList<>();
		}
	}


	public void editCommand(String title, String newiId, String oldId) {
		String getLocalData = prefs.getString("commands", null);
		ArrayList<FastButton> objectsList = new Gson().fromJson(getLocalData, new TypeToken<ArrayList<FastButton>>() {
		}.getType());

		for (int i = 0; i < objectsList.size(); i++) {
			if (objectsList.get(i).getId().equals(oldId)) {
				objectsList.get(i).setTitle(title);
				objectsList.get(i).setId(newiId);
			}
		}

		saveCommandList(objectsList);
	}

	public void deleteCommand(String id) {
		String getLocalData = prefs.getString("commands", null);
		ArrayList<FastButton> objectsList = new Gson().fromJson(getLocalData, new TypeToken<ArrayList<FastButton>>() {
		}.getType());

		for (int i = 0; i < objectsList.size(); i++) {
			if (objectsList.get(i).getId().equals(id)) {
				objectsList.remove(objectsList.get(i));
			}
		}

		saveCommandList(objectsList);
	}

}
