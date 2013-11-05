package com.manning.gwtia.ch10.client.celltable;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextButtonCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.manning.gwtia.ch10.client.cellwidget.common.PsuedoDataSource;
import com.manning.gwtia.ch10.client.dataproviders.CellTableAsyncDataProvider;
import com.manning.gwtia.ch10.shared.DateComparator;
import com.manning.gwtia.ch10.shared.IdComparator;
import com.manning.gwtia.ch10.shared.PhotoDetails;
import com.manning.gwtia.ch10.shared.TitleComparator;

public class CellTableExample extends Composite {

	interface PhotoListTextViewUiBinder extends
			UiBinder<Widget, CellTableExample> {
	}

	private static PhotoListTextViewUiBinder uiBinder = GWT
			.create(PhotoListTextViewUiBinder.class);

	@UiField(provided = true)
	CellTable<PhotoDetails> cellTable;
	final String COLUMN_NAME_DATE = "Date";
	final String COLUMN_NAME_ID = "ID";
	final String COLUMN_NAME_IMAGE = "Image";
	final String COLUMN_NAME_TAGS = "Tags";
	final String COLUMN_NAME_TITLE = "Title";
	final String COLUMN_NAME_VIEW = "View";

	Column<PhotoDetails, Date> columnDate;
	Column<PhotoDetails, String> columnDetails;
	TextColumn<PhotoDetails> columnId;
	Column<PhotoDetails, SafeHtml> columnImg;
	TextColumn<PhotoDetails> columnTags;
	Column<PhotoDetails, String> columnTitle;

	CellTableAsyncDataProvider dataProviderAsync;
	ListDataProvider<PhotoDetails> dataProviderList;

	Header<String> detailFooter;
	
	@UiField(provided = true)
	SimplePager pager;

	public CellTableExample() {
		cellTable = new CellTable<PhotoDetails>();
		// Create paging controls.
		pager = new SimplePager();
		pager.setDisplay(cellTable);
		initWidget(uiBinder.createAndBindUi(this));
		cellTable.setSize("100%", "100%");
		this.setSize("100%", "100%");

		buildTable();
		Window.enableScrolling(false);

		createWithAsyncDataProvider();
		//createWithListDataProvider();

		cellTable.setPageSize(20);
	}

	private void addColumnSortAsyncHandling() {
		columnId.setSortable(true);
		columnTitle.setSortable(true);
		columnDate.setSortable(true);

		ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(cellTable);
		cellTable.addColumnSortHandler(columnSortHandler);
	}

	private void addColumnSortListHandling() {
		columnId.setSortable(true);
		columnTitle.setSortable(true);
		columnDate.setSortable(true);

		ListHandler<PhotoDetails> columnSortHandler = new ListHandler<PhotoDetails>(
				dataProviderList.getList());

		columnSortHandler.setComparator(columnTitle, new TitleComparator());
		columnSortHandler.setComparator(columnId, new IdComparator());
		columnSortHandler.setComparator(columnDate, new DateComparator());
	
		
		cellTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted by ID by default.
		cellTable.getColumnSortList().push(columnId);
	}

	private Column<PhotoDetails, Date> buildColumnDate() {
		columnDate = new Column<PhotoDetails, Date>(new DatePickerCell()) {
			@Override
			public Date getValue(PhotoDetails object) {
				return object.getDate();
			}
		};
		columnDate.setDataStoreName(COLUMN_NAME_DATE);
		
		columnDate.setFieldUpdater(new FieldUpdater<PhotoDetails, Date>() {
			@Override
			public void update(int index, PhotoDetails object, Date value) {
				object.setDate(value);
				// presenter.savePhoto(object);
			}
		});
		return columnDate;
	}

	private Column<PhotoDetails, String> buildColumnDetails() {
		columnDetails = new Column<PhotoDetails, String>(new TextButtonCell()) {
			@Override
			public String getValue(PhotoDetails object) {
				return "View";
			}
		};
		columnDetails.setDataStoreName(COLUMN_NAME_VIEW);
		columnDetails.setFieldUpdater(new FieldUpdater<PhotoDetails, String>() {
			@Override
			public void update(int index, PhotoDetails object, String value) {
				// presenter.onSelectPhotoClicked(object.getId());
			}
		});
		return columnDetails;
	}

	private TextColumn<PhotoDetails> buildColumnID() {
		columnId = new TextColumn<PhotoDetails>() {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getId();
			}
		};
		columnId.setDataStoreName(COLUMN_NAME_ID);
		return columnId;
	}

	private Column<PhotoDetails, SafeHtml> buildColumnImage() {
		columnImg = new Column<PhotoDetails, SafeHtml>(new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue(PhotoDetails object) {
				SafeHtmlBuilder a = new SafeHtmlBuilder();
				a.appendHtmlConstant("<div style='width:60px;height:60px;background:"
						+ object.getThubnailUrl() + ";'></div>");
				return a.toSafeHtml();
			}
		};
		columnImg.setDataStoreName(COLUMN_NAME_IMAGE);
		return columnImg;
	}

	private TextColumn<PhotoDetails> buildColumnTags() {
		columnTags = new TextColumn<PhotoDetails>() {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getTags();
			}
		};
		columnTags.setDataStoreName(COLUMN_NAME_TAGS);
		return columnTags;
	}

	private Column<PhotoDetails, String> buildColumnTitle() {
		columnTitle = new Column<PhotoDetails, String>(new EditTextCell()) {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getTitle();
			}
		};
		columnTitle.setDataStoreName(COLUMN_NAME_TITLE);
		columnTitle.setFieldUpdater(new FieldUpdater<PhotoDetails, String>() {
			@Override
			public void update(int index, PhotoDetails object, String value) {
				object.setTitle(value);
				// presenter.savePhoto(object);
			}
		});
		return columnTitle;
	}

	private Header<String> buildHeader(final String text) {
		Header<String> head = new Header<String>(new TextCell()) {
			@Override
			public String getValue() {
				return text;
			}
		};
		return head;
	}

	private void buildTable() {

		// Create common footer
		detailFooter = new TextHeader("Details");

		// Build each of the 7 columns
		columnId = buildColumnID();
		columnImg = buildColumnImage();
		columnTitle = buildColumnTitle();
		columnTags = buildColumnTags();
		columnDate = buildColumnDate();
		columnDetails = buildColumnDetails();

		cellTable.addColumn(columnId,  buildHeader(COLUMN_NAME_ID), buildHeader(COLUMN_NAME_ID));
		cellTable.addColumn(columnImg,  buildHeader(COLUMN_NAME_IMAGE), detailFooter);
		cellTable.addColumn(columnTitle,  buildHeader(COLUMN_NAME_TITLE), detailFooter);
		cellTable.addColumn(columnTags,  buildHeader(COLUMN_NAME_TAGS), detailFooter);
		cellTable.addColumn(columnDate,  buildHeader(COLUMN_NAME_DATE), detailFooter);
		cellTable.addColumn(columnDetails,  buildHeader(COLUMN_NAME_VIEW), buildHeader(COLUMN_NAME_VIEW));
				
		cellTable.setColumnWidth(0, 60, Unit.PX);
		cellTable.setColumnWidth(1, 60, Unit.PX);
		cellTable.setColumnWidth(2, 250, Unit.PX);
		cellTable.setColumnWidth(3, 250, Unit.PX);
	}

	private void createWithAsyncDataProvider() {
		// Create a data provider.
		dataProviderAsync = new CellTableAsyncDataProvider();
		dataProviderAsync.addDataDisplay(cellTable);
		dataProviderAsync.updateRowCount(24, false);

		addColumnSortAsyncHandling();
	}

	private void createWithListDataProvider() {
		List<PhotoDetails> theList;

		dataProviderList = new ListDataProvider<PhotoDetails>();
		dataProviderList.addDataDisplay(cellTable);
		theList = dataProviderList.getList();
		PsuedoDataSource.populate(theList, dataProviderList);
		// Keep the size of the list up to date - easier to know that we are
		// showing 51-100 of 2000 etc
		cellTable.setRowCount(theList.size(), true);

		// Add sorting capability that is based on the list
		addColumnSortListHandling();

	}
}
