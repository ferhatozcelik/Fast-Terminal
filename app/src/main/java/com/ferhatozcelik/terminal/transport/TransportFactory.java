package com.ferhatozcelik.terminal.transport;

import java.util.HashMap;
import java.util.Map;

import com.ferhatozcelik.terminal.bean.HostBean;
import com.ferhatozcelik.terminal.data.HostStorage;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class TransportFactory {
	private static final String TAG = "CB.TransportFactory";

	private static String[] transportNames = {
		SSH.getProtocolName(),
		Telnet.getProtocolName(),
		Local.getProtocolName(),
	};

	/**
	 * @param protocol
	 * @return
	 */
	public static AbsTransport getTransport(String protocol) {
		if (SSH.getProtocolName().equals(protocol)) {
			return new SSH();
		} else if (Telnet.getProtocolName().equals(protocol)) {
			return new Telnet();
		} else if (Local.getProtocolName().equals(protocol)) {
			return new Local();
		} else {
			return null;
		}
	}

	public static Uri getUri(String scheme, String input) {
		Log.d("TransportFactory", String.format(
				"Attempting to discover URI for scheme=%s on input=%s", scheme,
				input));
		if (SSH.getProtocolName().equals(scheme))
			return SSH.getUri(input);
		else if (Telnet.getProtocolName().equals(scheme))
			return Telnet.getUri(input);
		else if (Local.getProtocolName().equals(scheme)) {
			Log.d("TransportFactory", "Got to the local parsing area");
			return Local.getUri(input);
		} else
			return null;
	}

	public static String[] getTransportNames() {
		return transportNames;
	}

	public static boolean isSameTransportType(AbsTransport a, AbsTransport b) {
		if (a == null || b == null)
			return false;

		return a.getClass().equals(b.getClass());
	}

	public static boolean canForwardPorts(String protocol) {
		// TODO uh, make this have less knowledge about its children
		return SSH.getProtocolName().equals(protocol);
	}

	/**
	 * @param protocol text name of protocol
	 * @param context
	 * @return expanded format hint
	 */
	public static String getFormatHint(String protocol, Context context) {
		if (SSH.getProtocolName().equals(protocol)) {
			return SSH.getFormatHint(context);
		} else if (Telnet.getProtocolName().equals(protocol)) {
			return Telnet.getFormatHint(context);
		} else if (Local.getProtocolName().equals(protocol)) {
			return Local.getFormatHint(context);
		} else {
			return AbsTransport.getFormatHint(context);
		}
	}

	/**
	 * @param hostdb Handle to HostDatabase
	 * @param uri URI to target server
	 * @return true when host was found
	 */
	public static HostBean findHost(HostStorage hostdb, Uri uri) {
		AbsTransport transport = getTransport(uri.getScheme());

		Map<String, String> selection = new HashMap<>();

		transport.getSelectionArgs(uri, selection);
		if (selection.isEmpty()) {
			Log.e(TAG, String.format("Transport %s failed to do something useful with URI=%s",
					uri.getScheme(), uri.toString()));
			throw new IllegalStateException("Failed to get needed selection arguments");
		}

		return hostdb.findHost(selection);
	}
}
