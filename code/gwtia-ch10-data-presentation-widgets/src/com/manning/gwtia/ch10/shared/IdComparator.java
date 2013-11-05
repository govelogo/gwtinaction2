package com.manning.gwtia.ch10.shared;

import java.util.Comparator;

public class IdComparator implements Comparator<PhotoDetails>{
	public int compare(PhotoDetails o1, PhotoDetails o2) {
		if (o1 == o2) {
			return 0;
		}

		// Compare the id columns.
		if (o1 != null) {
			return (o2 != null) ? o1.getId().compareTo(
					o2.getId()) : 1;
		}
		return -1;
	}
}
