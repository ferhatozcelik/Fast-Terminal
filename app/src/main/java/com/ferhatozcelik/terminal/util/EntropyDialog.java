package com.ferhatozcelik.terminal.util;

import com.ferhatozcelik.terminal.R;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

public class EntropyDialog extends Dialog implements OnEntropyGatheredListener {

	public EntropyDialog(Context context) {
		super(context);

		this.setContentView(R.layout.dia_gatherentropy);
		this.setTitle(R.string.pubkey_gather_entropy);

		((EntropyView) findViewById(R.id.entropy)).addOnEntropyGatheredListener(this);
	}

	public EntropyDialog(Context context, View view) {
		super(context);

		this.setContentView(view);
		this.setTitle(R.string.pubkey_gather_entropy);

		((EntropyView) findViewById(R.id.entropy)).addOnEntropyGatheredListener(this);
	}

	@Override
	public void onEntropyGathered(byte[] entropy) {
		this.dismiss();
	}

}
