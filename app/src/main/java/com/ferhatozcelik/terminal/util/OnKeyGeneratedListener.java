package com.ferhatozcelik.terminal.util;

import java.security.KeyPair;

public interface OnKeyGeneratedListener {
	void onGenerationError(Exception e);

	void onGenerationSuccess(KeyPair keyPair);
}
