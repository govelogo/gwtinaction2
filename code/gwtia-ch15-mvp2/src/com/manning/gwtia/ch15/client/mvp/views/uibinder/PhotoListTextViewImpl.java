package com.manning.gwtia.ch15.client.mvp.views.uibinder;

import java.util.Comparator;
import java.util.Date;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch15.client.mvp.MyDataProvider;
import com.manning.gwtia.ch15.client.mvp.presenters.PhotoListPresenter;
import com.manning.gwtia.ch15.client.mvp.views.PhotoListTextView;
import com.manning.gwtia.ch15.shared.PhotoDetails;

public class PhotoListTextViewImpl extends Composite implements
		PhotoListTextView {

	interface PhotoListTextViewUiBinder extends
			UiBinder<Widget, PhotoListTextViewImpl> {
	}

	private static PhotoListTextViewUiBinder uiBinder = GWT
			.create(PhotoListTextViewUiBinder.class);

	private PhotoListPresenter presenter;

	@UiField(provided = true)
	SimplePager pager;
	@UiField(provided = true)
	CellTable<PhotoDetails> cellTable;
	@UiField
	Button gridView;

	public PhotoListTextViewImpl() {
		cellTable = new CellTable<PhotoDetails>();
		// Create paging controls.
		SimplePager.Resources pagerResources = GWT
				.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0,
				true);
		pager.setDisplay(cellTable);
		initWidget(uiBinder.createAndBindUi(this));
		buildTable();
		Window.enableScrolling(false);
	}

	private void buildTable() {
		TextColumn<PhotoDetails> idColumn = new TextColumn<PhotoDetails>() {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getId();
			}
		};
		cellTable.addColumn(idColumn, "Id");

		Column<PhotoDetails, SafeHtml> imgColumn = new Column<PhotoDetails, SafeHtml>(
				new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue(PhotoDetails object) {
				SafeHtmlBuilder a = new SafeHtmlBuilder();
				a.appendHtmlConstant("<div style='width:20px;height:20px;background:"
						+ object.getThubnailUrl() + ";'></div>");
				return a.toSafeHtml();
			}
		};
		cellTable.addColumn(imgColumn, "Img");

		Column<PhotoDetails, String> titleColumn = new Column<PhotoDetails, String>(
				new EditTextCell()) {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getTitle();
			}
		};
		cellTable.addColumn(titleColumn, "Title");
		titleColumn.setSortable(true);

		TextColumn<PhotoDetails> tagsColumn = new TextColumn<PhotoDetails>() {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getTags();
			}
		};
		cellTable.addColumn(tagsColumn, "Tags");

		Column<PhotoDetails, Date> dateColumn = new Column<PhotoDetails, Date>(
				new DatePickerCell()) {
			@Override
			public Date getValue(PhotoDetails object) {
				return new Date(object.getDate());
			}
		};
		cellTable.addColumn(dateColumn, "Date");
		dateColumn.setSortable(true);

		Column<PhotoDetails, String> detailsColumn = new Column<PhotoDetails, String>(
				new ButtonCell()) {
			@Override
			public String getValue(PhotoDetails object) {
				return "View";
			}
		};
		cellTable.addColumn(detailsColumn, "View");

		detailsColumn.setFieldUpdater(new FieldUpdater<PhotoDetails, String>() {
			@Override
			public void update(int index, PhotoDetails object, String value) {
				presenter.onSelectPhotoClicked(object.getId());
			}
		});
		titleColumn.setFieldUpdater(new FieldUpdater<PhotoDetails, String>() {
			@Override
			public void update(int index, PhotoDetails object, String value) {
				object.setTitle(value);
				presenter.savePhoto(object);
			}
		});
		dateColumn.setFieldUpdater(new FieldUpdater<PhotoDetails, Date>() {
			@Override
			public void update(int index, PhotoDetails object, Date value) {
				int day = value.getDate();
				String Day;
				if (day < 10)
					Day = "0" + day;
				else
					Day = "" + day;
				int month = value.getMonth() + 1;
				String Month;
				if (month < 10)
					Month = "0" + month;
				else
					Month = "" + month;
				String Year = ("" + (value.getYear() + 1900));
				object.setDate(Month + "/" + Day + "/" + Year);
				presenter.savePhoto(object);
			}
		});

		//Won't really work!
		ListHandler<PhotoDetails> columnSortHandler = new ListHandler<PhotoDetails>(
				cellTable.getVisibleItems());
		columnSortHandler.setComparator(titleColumn,
				new Comparator<PhotoDetails>() {
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
				});
		columnSortHandler.setComparator(idColumn,
				new Comparator<PhotoDetails>() {
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
				});
		cellTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted by ID by default.
		cellTable.getColumnSortList().push(idColumn);

	}

	@UiHandler("gridView")
	public void switchGridView(ClickEvent evt) {
		if (presenter != null)
			presenter.switchGridView();
	}

	MyDataProvider dataProvider;

	public void setPresenter(PhotoListPresenter presenter) {
		this.presenter = presenter;
		// Create a data provider.
		dataProvider = presenter.getDataProvider();
		// Add the cellList to the dataProvider.
		cellTable.setPageSize(10);
		dataProvider.addDataDisplay(cellTable);
		dataProvider.updateRowCount(12, false);
	}
}
