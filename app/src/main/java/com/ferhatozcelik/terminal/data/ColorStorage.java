package com.ferhatozcelik.terminal.data;

/**
 * Created by kroot on 9/11/15.
 */
public interface ColorStorage {
	int[] getColorsForScheme(int colorScheme);

	void setGlobalColor(int mCurrentColor, int value);

	int[] getDefaultColorsForScheme(int colorScheme);

	void setDefaultColorsForScheme(int mColorScheme, int mDefaultColor, int mDefaultColor1);
}
