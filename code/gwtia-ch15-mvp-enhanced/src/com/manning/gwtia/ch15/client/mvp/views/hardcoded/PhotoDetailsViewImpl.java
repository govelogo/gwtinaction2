package com.manning.gwtia.ch15.client.mvp.views.hardcoded;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoDetailsPresenter;
import com.manning.gwtia.ch15.client.mvp.ui.AppMenuBar;
import com.manning.gwtia.ch15.client.mvp.ui.EditableLabel;
import com.manning.gwtia.ch15.client.mvp.ui.TagListWidget;
import com.manning.gwtia.ch15.client.mvp.views.PhotoDetailsView;

public class PhotoDetailsViewImpl extends Composite implements PhotoDetailsView {
	private PhotoDetailsPresenter presenter;
	private final EditableLabel title;
	private final TagListWidget tags;
	private final DockLayoutPanel container;

	public PhotoDetailsViewImpl() {

		DockLayoutPanel view = new DockLayoutPanel(Unit.PX);

		view.addNorth(new AppMenuBar(), 25);

		container = new DockLayoutPanel(Unit.PX);
		title = new EditableLabel();
		tags = new TagListWidget();
		thePhoto = new Image();
		thePhoto.setSize("500px", "400px");
		
		container.addNorth(title, 30);
		FlowPanel photo = new FlowPanel();
		
		photo.add(thePhoto);
		photo.add(tags);
		container.add(photo);
		container.forceLayout();
		
		view.add(container);

		initWidget(view);

		view.setSize("100%", "100%");
		view.forceLayout();


		bind();
	}
	
	Image thePhoto;

	public HasValue<String> getPhotoTitle() {
		return title;
	}

	public HasValue<String> getPhotoTags() {
		return tags;
	}

	public void setPresenter(PhotoDetailsPresenter presenter) {
		this.presenter = presenter;
	}


	public void bind() {
		title.addValueChangeHandler(new ValueChangeHandler<String>(){
			public void onValueChange(ValueChangeEvent<String> event) {
				if (presenter != null){
					presenter.onUpdateTitle();
				}
			}
			
		});
		tags.addValueChangeHandler(new ValueChangeHandler<String>(){
			public void onValueChange(ValueChangeEvent<String> event) {
				if (presenter != null){
					presenter.onUpdateTags();
				}
			}			
		});
	}

	public void setPhoto(String url) {
		//thePhoto.setUrl(url);
		// Actually, the URL in our example is really the background colour
		thePhoto.getElement().getStyle().setBackgroundColor(url);
	}
}
