package com.blundell.asynctaskloader.util;

import java.text.Collator;

import com.blundell.asynctaskloader.domain.AppEntry;

public class Comparator {

	/**
	 * Perform alphabetical comparison on entry objects
	 */
	public static final java.util.Comparator<AppEntry> ALPHA_COMPARATOR = new java.util.Comparator<AppEntry>() {
		private final Collator collator = Collator.getInstance();

		@Override
		public int compare(AppEntry lhs, AppEntry rhs) {
			return collator.compare(lhs.getLabel(), rhs.getLabel());
		}
	};

}
