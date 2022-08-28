package com.ferhatozcelik.terminal.model;

import java.util.ArrayList;

import android.content.Context;

public class FastButton {

	String id;
	String title;
	Integer icon;

	public FastButton(String id, String title, Integer icon) {
		this.id = id;
		this.title = title;
		this.icon = icon;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}
}
