package com.manning.gwtia.ch10.client.cellwidget.common;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.Random;
import com.google.gwt.view.client.ListDataProvider;
import com.manning.gwtia.ch10.shared.PhotoDetails;

public class PsuedoDataSource {

	private int DATA_SIZE = 100;

	private final int NUMBER_YEARS = 5;
	private final int START_YEAR = 2012;

	private String[] titles0 = new String[] { "", "Anna", "The cat", "",
			"Our elephant", "Marianne", "Claire", "Peter", "Paul", "", "", "",
			"" };
	private String[] titles1 = new String[] { "walking", "eating", "shooting",
			"going", "hiding", "running", "swimming", "playing", "stopping",
			"dancing" };
	private String[] titles2 = new String[] { "with", "on", "to", "above",
			"beyond", "before", "after", "next to", "in front of", "behind" };
	private String[] titles3 = new String[] { "the dog", "Anna", "the cat",
			"the space hopper", "the elephant", "the wine", "Marianne",
			"Claire", "Peter", "Paul", "the house", "the car", "the pub",
			"the disco" };
	private String[] images = new String[] { "springgreen", "skyblue",
			"powderblue", "orange", "deeppink", "chartreuse", "aquamarine",
			"azure", "aqua", "blue", "fuchsia", "gray", "green", "lime",
			"maroon", "navy", "olive", "purple", "red", "silver", "teal",
			"white", "yellow" };

	private static PsuedoDataSource instance;

	public static List<PhotoDetails> createData(int dataSize) {
		if (instance == null)
			instance = new PsuedoDataSource();
		instance.DATA_SIZE = dataSize;
		return PsuedoDataSource.createData();
	}
	
	static int idCounter = 0;

	@SuppressWarnings("deprecation")
	public static List<PhotoDetails> createData() {
		if (instance == null)
			instance = new PsuedoDataSource();
		PhotoDetails[] thePhotos = new PhotoDetails[instance.DATA_SIZE];
		for (int i = 0; i < instance.DATA_SIZE; i++) {
			PhotoDetails photo = new PhotoDetails(""+idCounter++);
			int title0 = Random.nextInt(instance.titles0.length);
			int title1 = Random.nextInt(instance.titles1.length);
			int title2 = Random.nextInt(instance.titles2.length);
			int title3 = Random.nextInt(instance.titles3.length);
			if (instance.titles0[title0].equals("")) {
				StringBuilder result = new StringBuilder(
						instance.titles1[title1].length());
				StringBuilder x = result.append(
						Character.toUpperCase(instance.titles1[title1]
								.charAt(0))).append(
						instance.titles1[title1].substring(1));
				photo.setTitle(x.toString() + " " + instance.titles2[title2]
						+ " " + instance.titles3[title3]);
			} else {
				photo.setTitle(instance.titles0[title0] + " "
						+ instance.titles1[title1] + " "
						+ instance.titles2[title2] + " "
						+ instance.titles3[title3]);
			}
			int day = Random.nextInt(30)+1;
			int month = Random.nextInt(11);
			int year = Random.nextInt(instance.NUMBER_YEARS);
			Date date = new Date();
			date.setDate(day);
			date.setMonth(month);
			date.setYear(instance.START_YEAR-1900-year);
			photo.setDate(date);
			int col = Random.nextInt(instance.images.length);
			photo.setLargeUrl(instance.images[col]);
			photo.setThubnailUrl(instance.images[col]);
			photo.setTags("");
			thePhotos[i] = photo;
		}
		return Arrays.asList(thePhotos);
	}

	static final int maxData = 2000;
	static final int chunks = 40;
	
	public static void populate(final List<PhotoDetails> theList, final ListDataProvider<PhotoDetails> dataListProvider){
		
		Scheduler.get().scheduleIncremental(new RepeatingCommand(){
			int counter = 0;
			
			@Override
			public boolean execute() {
				theList.addAll(PsuedoDataSource.createData(chunks));
				dataListProvider.refresh();
				counter += chunks;
				return (counter<maxData);
			}
		});
	}
	
}
