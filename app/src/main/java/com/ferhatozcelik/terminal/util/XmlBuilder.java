package com.ferhatozcelik.terminal.util;

import java.io.UnsupportedEncodingException;

import com.trilead.ssh2.crypto.Base64;

public class XmlBuilder {
	private StringBuilder sb;

	public XmlBuilder() {
		sb = new StringBuilder();
	}

	public XmlBuilder append(String data) {
		sb.append(data);

		return this;
	}

	public XmlBuilder append(String field, Object data) {
		if (data == null) {
			sb.append(String.format("<%s/>", field));
		} else if (data instanceof String) {
			String input = (String) data;
			boolean binary = false;

			for (byte b : input.getBytes()) {
				if (b < 0x20 || b > 0x7e) {
					binary = true;
					break;
				}
			}

			try {
				sb.append(String.format("<%s>%s</%s>", field,
						binary ? new String(Base64.encode(input.getBytes("UTF-8"))) : input, field));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException("UTF-8 unavailable");
			}
		} else if (data instanceof Integer) {
			sb.append(String.format("<%s>%d</%s>", field, (Integer) data, field));
		} else if (data instanceof Long) {
			sb.append(String.format("<%s>%d</%s>", field, (Long) data, field));
		} else if (data instanceof byte[]) {
			sb.append(String.format("<%s>%s</%s>", field, new String(Base64.encode((byte[]) data)), field));
		} else if (data instanceof Boolean) {
			sb.append(String.format("<%s>%s</%s>", field, (Boolean) data, field));
		}

		return this;
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}
