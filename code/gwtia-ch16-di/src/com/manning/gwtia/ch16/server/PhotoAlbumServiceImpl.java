package com.manning.gwtia.ch16.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.manning.gwtia.ch16.shared.PhotoAlbumService;
import com.manning.gwtia.ch16.shared.PhotoDetails;
import com.manning.gwtia.ch16.shared.PhotoNotFoundException;

public class PhotoAlbumServiceImpl extends RemoteServiceServlet implements
		PhotoAlbumService {

	private static final long serialVersionUID = 1L;
	Vector<PhotoDetails> data = null;

	/** Just pretend the update is successful by returning the passed in PhotoDetails object
	 */
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
				System.out.println("Saving: "+temp.getId());
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
					System.out.println(id);
					thePhoto.setTitle(reader.readLine());
					thePhoto.setTags(reader.readLine());
					thePhoto.setThubnailUrl(reader.readLine());
					thePhoto.setLargeUrl(reader.readLine());
					thePhoto.setDate(reader.readLine());

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
		/**
		 * Uncomment the following code if you wish to try the onCancel method of the
		 * PhotoDetailsView - it puts the reply to sleep for 20 seconds.
		 * 
		  try {
		 
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
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

	public Vector<PhotoDetails> getPhotoList() {
		return getPhotoList(0,12);
	}
	
	public Vector<PhotoDetails> getPhotoList(int start, int length) {
		Vector<PhotoDetails> toReturn = new Vector<PhotoDetails>();
		if (data==null) loadPhotoData();
		Iterator<PhotoDetails> i = data.iterator();
		int pos = 0;
		while (i.hasNext() && pos++<start) i.next();
		while (i.hasNext() && pos++<(start+length)) toReturn.add(i.next());
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
