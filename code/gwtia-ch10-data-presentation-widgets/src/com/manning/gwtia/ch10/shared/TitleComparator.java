package com.manning.gwtia.ch10.shared;

import java.util.Comparator;

public class TitleComparator implements Comparator<PhotoDetails>{
	public int compare(PhotoDetails o1, PhotoDetails o2) {
		if (o1 == o2) {
			return 0;
		}

		// Compare the title columns.
		if (o1 != null) {
			return (o2 != null) ? o1.getTitle().compareTo(
					o2.getTitle()) : 1;
		}
		return -1;
	}
}
