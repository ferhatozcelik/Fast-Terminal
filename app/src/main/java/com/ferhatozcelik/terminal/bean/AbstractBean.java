package com.ferhatozcelik.terminal.bean;

import java.util.Map.Entry;

import com.ferhatozcelik.terminal.util.XmlBuilder;

import android.content.ContentValues;

/**
 * @author Kenny Root
 *
 */
abstract class AbstractBean {
	public abstract ContentValues getValues();
	public abstract String getBeanName();

	public String toXML() {
		XmlBuilder xml = new XmlBuilder();

		xml.append(String.format("<%s>", getBeanName()));

		ContentValues values = getValues();
		for (Entry<String, Object> entry : values.valueSet()) {
			Object value = entry.getValue();
			if (value != null)
				xml.append(entry.getKey(), value);
		}
		xml.append(String.format("</%s>", getBeanName()));

		return xml.toString();
	}
}
