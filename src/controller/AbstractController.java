package controller;

import model.DataModel;
import view.MainFrame;
import view.JPanelComponent.ContentView;
import view.JPanelComponent.DashboardView;
import view.JPanelComponent.SearchView;

/**
 * @author quan.lh173316
 *
 */
public abstract class AbstractController {

	protected MainFrame mainFrame;
	protected DataModel dataModel;
	protected ContentView contentView;
	protected DashboardView dashboardView;
	protected SearchView searchView;

	public AbstractController(DataModel dataModel, MainFrame mainFrame) {
		this.dataModel = dataModel;
		this.mainFrame = mainFrame;
		this.contentView = mainFrame.getContentView();
		this.dashboardView = mainFrame.getDashboardView();
		this.searchView = mainFrame.getSearchView();
	}

	protected void run() {

	}

}
