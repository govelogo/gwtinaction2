package com.manning.gwtia.ch10.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.manning.gwtia.ch10.shared.AsyncDays;
import com.manning.gwtia.ch10.shared.AsyncMonths;
import com.manning.gwtia.ch10.shared.AsyncYears;
import com.manning.gwtia.ch10.shared.DateComparator;
import com.manning.gwtia.ch10.shared.IdComparator;
import com.manning.gwtia.ch10.shared.PhotoAlbumService;
import com.manning.gwtia.ch10.shared.PhotoDetails;
import com.manning.gwtia.ch10.shared.PhotoNotFoundException;
import com.manning.gwtia.ch10.shared.TitleComparator;

public class PhotoAlbumServiceImpl extends RemoteServiceServlet implements
		PhotoAlbumService {

	private static final long serialVersionUID = 1L;
	Vector<PhotoDetails> data = null;


	public PhotoDetails updatePhotoDetails(PhotoDetails photo) {
		if (data==null) loadPhotoData();
		Iterator<PhotoDetails> i = data.iterator();
		PhotoDetails toReplace=null;
		while(i.hasNext()){
			PhotoDetails temp  = i.next(); 
			if(temp.getId().equals(photo.getId())){
				toReplace=temp;
				break;
			}
		}
		if (toReplace!=null){
			data.set(data.indexOf(toReplace), photo);
			savePhotoData();
		}
		return photo;
	}
	
	String filename = "/PhotoData.txt";
	
	private void savePhotoData(){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getServletContext().getRealPath("/") + File.separator + filename),"UTF8"));
			Iterator<PhotoDetails> i = data.iterator();
			while(i.hasNext()){
				PhotoDetails temp = i.next();
				out.write(temp.getId()+"\n");
				out.write(temp.getTitle()+"\n");
				out.write(temp.getTags()+"\n");
				out.write(temp.getThubnailUrl()+"\n");
				out.write(temp.getLargeUrl()+"\n");
				out.write(temp.getDate()+"\n");
				out.write("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadPhotoData(){
		if (data!=null) return;
		data = new Vector<PhotoDetails>();
		ServletContext context = getServletContext();

		InputStream inp = context.getResourceAsStream(filename);
		if (inp != null) {
			InputStreamReader isr = new InputStreamReader(inp);
			BufferedReader reader = new BufferedReader(isr);

			String text = "";
			try {
				while ((text = reader.readLine()) != null){
					String id = text;
					PhotoDetails thePhoto = new PhotoDetails(id);
					thePhoto.setTitle(reader.readLine());
					thePhoto.setTags(reader.readLine());
					thePhoto.setThubnailUrl(reader.readLine());
					thePhoto.setLargeUrl(reader.readLine());
					try{
						thePhoto.setDate(ServerCalendarFactory.getFormatter().parse(reader.readLine().trim()));
					} catch (Exception e){
						thePhoto.setDate(new Date());
					}
					reader.readLine();
					data.add(thePhoto);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
					isr.close();
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("No file found");
		}		
	}
	
	public PhotoDetails getPhotoDetails(String id) throws PhotoNotFoundException{
		PhotoDetails thePhoto = null;
		if (data==null) loadPhotoData();
		Iterator<PhotoDetails> i = data.iterator();
		while(i.hasNext()){
			PhotoDetails temp = i.next();
			if (temp.getId().equals(id)){
				thePhoto = temp;
				break;
			}
		}
		if (thePhoto==null) throw new PhotoNotFoundException();
		return thePhoto;
	}
	
	public List<AsyncYears> getYearsList(int rangeStart, int rangeLength){
		if (data==null) loadPhotoData();
		AbstractSet<AsyncYears> toReturn = new TreeSet<AsyncYears>();
		Iterator<PhotoDetails> iter = data.iterator();
		while(iter.hasNext())
			toReturn.add(new AsyncYears(iter.next().getYear()));
		return new ArrayList<AsyncYears>(toReturn);
	}
	
	public List<AsyncMonths> getMonthsList(int year, int rangeStart, int rangeLength){
		if (data==null) loadPhotoData();
		AbstractSet<AsyncMonths> toReturn = new TreeSet<AsyncMonths>();
		Iterator<PhotoDetails> iter = data.iterator();
		while(iter.hasNext()){
			PhotoDetails photo = iter.next();
			int photoYear=photo.getYear();
			if (photoYear == (year)){
				toReturn.add(new AsyncMonths(photo.getMonth()));
			}
		}
		return new ArrayList<AsyncMonths>(toReturn);
	}
	
	public List<AsyncDays> getDaysList(int year, int month, int rangeStart, int rangeLength){
		if (data==null) loadPhotoData();
		AbstractSet<AsyncDays> toReturn = new TreeSet<AsyncDays>();
		Iterator<PhotoDetails> iter = data.iterator();
		while(iter.hasNext()){
			PhotoDetails photo = iter.next();
			int photoYear=photo.getYear();
			int photoMonth=photo.getMonth();
			if ((photoYear == year) && (photoMonth == month)) 
				toReturn.add(new AsyncDays(photo.getDay()));
		}
		return new ArrayList<AsyncDays>(toReturn);
	}
	
	public List<PhotoDetails> getPhotoByDateList(int year, int month, int day, int rangeStart, int rangeLength){
		if (data==null) loadPhotoData();
		Vector<PhotoDetails> toReturn = new Vector<PhotoDetails>();
		Iterator<PhotoDetails> iter = data.iterator();
		while(iter.hasNext()){
			PhotoDetails photo =iter.next();
			int photoYear = photo.getYear();
			int photoMonth = photo.getMonth();
			int photoDay = photo.getDay();
			if ((photoYear == year) && (photoMonth == month)  && (photoDay == day)) 
				toReturn.add(photo);
		}
		return toReturn;
	}

	
	public Vector<PhotoDetails> getPhotoList(int rangeStart, int rangeLength) {
		System.out.println("Start: "+rangeStart+"  Length: "+rangeLength);
		Vector<PhotoDetails> toReturn = new Vector<PhotoDetails>();
		if (data==null) loadPhotoData();
		Iterator<PhotoDetails> i = data.iterator();
		int pos = 0;
		while (i.hasNext() && pos++<(rangeStart)) i.next();
		while (i.hasNext() && pos++<((rangeStart)+rangeLength+1)) toReturn.add(i.next());
		return toReturn;
	}
	
	/**
	 * 
	 */
	public Vector<PhotoDetails> getPhotoList(int rangeStart, int rangeLength, String sortOn, boolean isAscending){
		Vector<PhotoDetails> toReturn = new Vector<PhotoDetails>();
		
		// If data isn't loaded, load it.
		if (data==null) loadPhotoData();
		
		// Determine which comparator we need to use for sorting
		Comparator<PhotoDetails> c = null;
		if (sortOn.equals("Title"))  c = new TitleComparator();
		if (sortOn.equals("id")) c= new IdComparator();
		if (sortOn.equals("Date")) c = new DateComparator();
		
		// Do we have to reverse the order of the sort?
		if (!isAscending) c = Collections.reverseOrder(c);
		
		// Perform the sorting
		Collections.sort(data,c);
		
		// Extract the range of data requested
		Iterator<PhotoDetails> i = data.iterator();
		int pos = 0;
		while (i.hasNext() && pos++<(rangeStart)) i.next();
		while (i.hasNext() && pos++<((rangeStart)+rangeLength+1)) toReturn.add(i.next());
		
		// Send the result back
		return toReturn;
	}

	
	public void savePhoto(PhotoDetails photo) {
		if (data==null) loadPhotoData();
		Iterator<PhotoDetails> i = data.iterator();
		PhotoDetails toReplace=null;
		while(i.hasNext()){
			PhotoDetails temp  = i.next(); 
			if(temp.getId().equals(photo.getId())){
				toReplace=temp;
				break;
			}
		}
		if (toReplace!=null){
			data.set(data.indexOf(toReplace), photo);
			savePhotoData();
		}
	}	
}
