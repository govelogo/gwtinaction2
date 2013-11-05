package com.manning.gwtia.ch10.client.datagrid;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.cell.client.IconCellDecorator;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextButtonCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.AbstractCellTableBuilder;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.AbstractCellTable.Style;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.manning.gwtia.ch10.client.cellwidget.common.CalendarFactory;
import com.manning.gwtia.ch10.client.cellwidget.common.PsuedoDataSource;
import com.manning.gwtia.ch10.client.dataproviders.CellTableAsyncDataProvider;
import com.manning.gwtia.ch10.shared.DateComparator;
import com.manning.gwtia.ch10.shared.IdComparator;
import com.manning.gwtia.ch10.shared.PhotoDetails;
import com.manning.gwtia.ch10.shared.TitleComparator;

public class CellDataGridExample extends Composite{

	
	public class CustomCellTableBuilder extends AbstractCellTableBuilder<PhotoDetails>{

		String evenRowStyle;
		String oddRowStyle;
		String rowStyle;
		String selectedRowStyle;
		
		StringBuilder trClasses;

		public CustomCellTableBuilder(AbstractCellTable<PhotoDetails> cellTable) {
			super(cellTable);
			Style style = dataGrid.getResources().style();
		      rowStyle = style.evenRow();
		      selectedRowStyle = " " + style.selectedRow();
		      evenRowStyle = " " + style.evenRow();
		      oddRowStyle = " " + style.oddRow();
		}
		
		protected void buildNewTagRowImpl(PhotoDetails rowValue, int absRowIndex){
			// Start Row
			TableRowBuilder row = startRow();
		      
		    // Render Empty Id cell
		    TableCellBuilder idTD = row.startTD();
		    idTD.endTD();
		      
		    // Render Empty Image cell
		    TableCellBuilder imageTD = row.startTD();
		    imageTD.endTD();
		    
		    // Render Empty Title cell
		    TableCellBuilder titleTD = row.startTD();
		    titleTD.endTD();
		    
		    // Render Tags cell
		    TableCellBuilder tagsTD = row.startTD();
		    HasCell<PhotoDetails, String> cell = new NewTagCell();
		    this.renderCell(tagsTD, createContext(3), cell, rowValue);
		    tagsTD.endTD();
		    
		    // Render Empty Date cell
		    TableCellBuilder dateTD = row.startTD();
		    dateTD.endTD();
		    
		    // Render Empty View cell	    
		    TableCellBuilder viewTD = row.startTD();
		    viewTD.endTD();
		    
		    // End Row
		    row.endTR();
		}
		
		@Override
		protected void buildRowImpl(PhotoDetails rowValue, int absRowIndex) {
			buildStandardRowImpl(rowValue, absRowIndex);
			if (itemsToExpand.contains(rowValue)){
				for(int tagIndex=0;tagIndex<rowValue.getTagsList().length;tagIndex++)
					buildTagRowImpl(rowValue, tagIndex, absRowIndex);
				buildNewTagRowImpl(rowValue, absRowIndex);
			}
		}
		
		protected void buildStandardRowImpl(PhotoDetails rowValue, int absRowIndex) {
		    determineStyle(rowValue,absRowIndex);
			// Start Row
			TableRowBuilder row = startRow();
			
			row.className(trClasses.toString());
		      
		    // Render Id cell
		    TableCellBuilder idTD = row.startTD();
		    this.renderCell(idTD, createContext(0), dataGrid.getColumn(0), rowValue);
		    idTD.endTD();
		      
		    // Render Image cell
		    TableCellBuilder imageTD = row.startTD();
		    this.renderCell(imageTD, createContext(1), dataGrid.getColumn(1), rowValue);
		    imageTD.endTD();
		    
		    // Render Title cell
		    TableCellBuilder titleTD = row.startTD();
		    this.renderCell(titleTD, createContext(2), dataGrid.getColumn(2), rowValue);
		    titleTD.endTD();
		    
		    // Render Tags cell
		    TableCellBuilder tagsTD = row.startTD();
		    if(!itemsToExpand.contains(rowValue))
		    	this.renderCell(tagsTD, createContext(3), dataGrid.getColumn(3), rowValue);
		    tagsTD.endTD();
		    
		    // Render Date cell
		    TableCellBuilder dateTD = row.startTD();
		    this.renderCell(dateTD, createContext(4), dataGrid.getColumn(4), rowValue);
		    dateTD.endTD();
		    
		    // Render View cell	    
		    TableCellBuilder viewTD = row.startTD();
		    this.renderCell(viewTD, createContext(5), dataGrid.getColumn(5), rowValue);
		    viewTD.endTD();
		    
		    // End Row
		    row.endTR();
		}
		
		protected void buildTagRowImpl(PhotoDetails rowValue, int tagIndex, int absRowIndex){
			// Start Row
			TableRowBuilder row = startRow();
		      
		    // Render Empty Id cell
		    TableCellBuilder idTD = row.startTD();
		    idTD.endTD();
		      
		    // Render Empty Image cell
		    TableCellBuilder imageTD = row.startTD();
		    imageTD.endTD();
		    
		    // Render Empty Title cell
		    TableCellBuilder titleTD = row.startTD();
		    titleTD.endTD();
		    
		    // Render Tags cell
		    TableCellBuilder tagsTD = row.startTD();
		    HasCell<PhotoDetails, String> cell = new TagCell(tagIndex);
		    this.renderCell(tagsTD, createContext(3), cell, rowValue);
		    tagsTD.endTD();
		    
		    // Render Empty Date cell
		    TableCellBuilder dateTD = row.startTD();
		    dateTD.endTD();
		    
		    // Render Empty View cell	    
		    TableCellBuilder viewTD = row.startTD();
		    viewTD.endTD();
		    
		    // End Row
		    row.endTR();
		}
		
		private void determineStyle(PhotoDetails rowValue, int absRowIndex){
			SelectionModel<? super PhotoDetails> selectionModel = dataGrid.getSelectionModel();
		      boolean isSelected =
		          (selectionModel == null || rowValue == null) ? false : selectionModel
		              .isSelected(rowValue);
		      boolean isEven = absRowIndex % 2 == 0;
		      trClasses = new StringBuilder(rowStyle);
		      if (isEven) {
			        trClasses.append(evenRowStyle);
			  } else {
				  trClasses.append(oddRowStyle);
			  }
		      if (isSelected) {
		        trClasses.append(selectedRowStyle);
		      }
		}
		
	}
	
	public class NewTagCell implements HasCell<PhotoDetails, String>{
		private TextInputCell cell = new TextInputCell();
		
		public NewTagCell(){
		}
		
		@Override
		public Cell<String> getCell() {
			return cell;
		}

		@Override
		public FieldUpdater<PhotoDetails, String> getFieldUpdater() {
			return null;
		}

		@Override
		public String getValue(PhotoDetails object) {
			return "New Tag";
		}
    }

	interface PhotoListTextViewUiBinder extends UiBinder<Widget, CellDataGridExample> {}


	public class TagCell implements HasCell<PhotoDetails, String>{
		private EditTextCell cell = new EditTextCell();
	    private int index = 0;
		
		TagResources tagResources = GWT.create(TagResources.class);
	    IconCellDecorator<String> decoratorCell = new IconCellDecorator<String>(tagResources.delete(), cell);
		
		public TagCell(int index){
			this.index = index;
		}
		
		@Override
		public Cell<String> getCell() {
			return decoratorCell;
		}

		@Override
		public FieldUpdater<PhotoDetails, String> getFieldUpdater() {
			return null;
		}

		@Override
		public String getValue(PhotoDetails object) {
			return object.getTagsList()[index];
		}
		
		public void setIndex(int index){
			this.index = index;
		}
    }
	
	public interface TagResources extends ClientBundle {
		  @Source("delete.png")
		  public ImageResource delete();
	}
	
	private static PhotoListTextViewUiBinder uiBinder = GWT.create(PhotoListTextViewUiBinder.class);
	
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

	@UiField(provided = true)
	DataGrid<PhotoDetails> dataGrid;
	
	CellTableAsyncDataProvider dataProviderAsync;
	ListDataProvider<PhotoDetails> dataProviderList;
	
	Header<String> detailFooter;

	Set<PhotoDetails> itemsToExpand = new TreeSet<PhotoDetails>();
	
	
	@UiField(provided = true)
	SimplePager pager;
	
	
	public CellDataGridExample() {
		dataGrid = new DataGrid<PhotoDetails>();
		dataGrid.setEmptyTableWidget(new Label("No Data to Display"));
		// Create paging controls.
		pager = new SimplePager();
		pager.setDisplay(dataGrid);
		
		initWidget(uiBinder.createAndBindUi(this));
		this.setSize("100%", "100%");

		//buildTableDefault();
		buildTableCustom();
		
		createWithAsyncDataProvider();
		//createWithListDataProvider();
		
		dataGrid.setPageSize(15);
	}
	
	private Column<PhotoDetails, Date> buildColumnDate(){
		columnDate = new Column<PhotoDetails, Date>(
			new DatePickerCell((DateTimeFormat) CalendarFactory.getDisplayFormatter())) {
			@Override
			public Date getValue(PhotoDetails object) {
				return object.getDate();
			}
		};
		columnDate.setDataStoreName(COLUMN_NAME_DATE);
		columnDate.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		columnDate.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);		
		
		columnDate.setFieldUpdater(new FieldUpdater<PhotoDetails, Date>() {
			@Override
			public void update(int index, PhotoDetails object, Date value) {
				object.setDate(value);
				//presenter.savePhoto(object);
			}
		});
		return columnDate;
	}


	private Column<PhotoDetails, String> buildColumnDetails(){
		columnDetails = new Column<PhotoDetails, String>(
				new TextButtonCell()) {
			@Override
			public String getValue(PhotoDetails object) {
				if (itemsToExpand.contains(object))
					return "Save Tags";
				else
					return "Edit Tags";
			}
		};
		columnDetails.setDataStoreName(COLUMN_NAME_VIEW);
		columnDetails.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		columnDetails.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);		
		columnDetails.setFieldUpdater(new FieldUpdater<PhotoDetails, String>() {
			@Override
			public void update(final int index, final PhotoDetails object, String value) {
				if (itemsToExpand.contains(object)){
					itemsToExpand.remove(object);					
				} else {
					itemsToExpand.add(object);
				}
				dataGrid.redraw();
			}
		});	
		return columnDetails;
	}
	
	private TextColumn<PhotoDetails> buildColumnID(){
		columnId = new TextColumn<PhotoDetails>() {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getId();
			}
		};
		columnId.setDataStoreName(COLUMN_NAME_ID);
		columnId.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		columnId.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		return columnId;
	}

	private Column<PhotoDetails, SafeHtml> buildColumnImage(){
		columnImg = new Column<PhotoDetails, SafeHtml>(
				new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue(PhotoDetails object) {
				SafeHtmlBuilder a = new SafeHtmlBuilder();
				a.appendHtmlConstant("<div style='width:40px;height:40px;background:"
						+ object.getThubnailUrl() + ";'></div>");
				return a.toSafeHtml();
			}
		};
		columnImg.setDataStoreName(COLUMN_NAME_IMAGE);
		columnImg.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		columnImg.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		return columnImg;
	}

	
	private void addColumnSortAsyncHandling() {
		columnId.setSortable(true);
		columnTitle.setSortable(true);
		columnDate.setSortable(true);

		ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(dataGrid);
		dataGrid.addColumnSortHandler(columnSortHandler);
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
	
		
		dataGrid.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted by ID by default.
		dataGrid.getColumnSortList().push(columnId);
	}
	
	
	private TextColumn<PhotoDetails> buildColumnTags(){
		columnTags = new TextColumn<PhotoDetails>() {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getTags();
			}
		};
		columnTags.setDataStoreName(COLUMN_NAME_TAGS);
		columnTags.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		columnTags.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		return columnTags;
	}

	private Column<PhotoDetails, String> buildColumnTitle(){
		columnTitle = new Column<PhotoDetails, String>(
				new EditTextCell()) {
			@Override
			public String getValue(PhotoDetails object) {
				return object.getTitle();
			}
		};
		columnTitle.setDataStoreName(COLUMN_NAME_TITLE);
		columnTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		columnTitle.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		columnTitle.setFieldUpdater(new FieldUpdater<PhotoDetails, String>() {
			@Override
			public void update(int index, PhotoDetails object, String value) {
				object.setTitle(value);
				//presenter.savePhoto(object);
			}
		});
		return columnTitle;
	}
	
	
	private Header<String> buildHeader(final String text){
		Header<String> head = new Header<String>(new TextCell()){
			@Override
			public String getValue() {
				return text;
			}
		};
		return head;
	}

	
	private void buildTableCustom(){

		CustomCellTableBuilder tableBuilder = new CustomCellTableBuilder(dataGrid);
		dataGrid.setTableBuilder(tableBuilder);
		buildTableDefault();
	}
		
	private void buildTableDefault() {

		// Create common footer
		detailFooter = new TextHeader("Details");
		
		// Build each of the 7 columns
		columnId = buildColumnID();
		columnImg = buildColumnImage();
		columnTitle = buildColumnTitle();
		columnTags = buildColumnTags();
		columnDate = buildColumnDate();
		columnDetails = buildColumnDetails();

		// Add the columns to the widget
		dataGrid.addColumn(columnId,  buildHeader(COLUMN_NAME_ID), buildHeader(COLUMN_NAME_ID));
		dataGrid.addColumn(columnImg,  buildHeader(COLUMN_NAME_IMAGE), detailFooter);
		dataGrid.addColumn(columnTitle,  buildHeader(COLUMN_NAME_TITLE), detailFooter);
		dataGrid.addColumn(columnTags,  buildHeader(COLUMN_NAME_TAGS), detailFooter);
		dataGrid.addColumn(columnDate,  buildHeader(COLUMN_NAME_DATE), detailFooter);
		dataGrid.addColumn(columnDetails,  buildHeader(COLUMN_NAME_VIEW), buildHeader(COLUMN_NAME_VIEW));
		
		// Set the column widths
		dataGrid.setColumnWidth(0, 60, Unit.PX);
		dataGrid.setColumnWidth(1, 60, Unit.PX);
		dataGrid.setColumnWidth(2, 250, Unit.PX);
		dataGrid.setColumnWidth(3, 250, Unit.PX);
	
		// Make efficient for IE
		dataGrid.setSkipRowHoverCheck(true);
		dataGrid.setSkipRowHoverFloatElementCheck(true);
		dataGrid.setSkipRowHoverStyleUpdate(true);
	};
	

	private void createWithAsyncDataProvider(){
		// Create a data provider.
		dataProviderAsync = new CellTableAsyncDataProvider();
		dataProviderAsync.addDataDisplay(dataGrid);
		dataProviderAsync.updateRowCount(24, true);
		addColumnSortAsyncHandling();
	}
	
	private void createWithListDataProvider(){
		List<PhotoDetails> theList;

		dataProviderList = new ListDataProvider<PhotoDetails>();
		dataProviderList.addDataDisplay(dataGrid);
		theList = dataProviderList.getList();
		PsuedoDataSource.populate(theList, dataProviderList);
		// Keep the size of the list up to date - easier to know that we are showing 51-100 of 2000 etc
		dataGrid.setRowCount(theList.size(), true);
		addColumnSortListHandling();
	}
}
