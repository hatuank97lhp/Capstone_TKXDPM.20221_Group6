package ebike.view;

import javax.swing.*;

import ebike.core.application.IBikeAppService;
import ebike.core.application.IDockingStationAppService;
import ebike.core.application.IUserAppService;
import ebike.view.components.BarCodeInput;
import ebike.view.components.Header;
import ebike.view.components.RentBikePreview;
import ebike.view.components.RentalTx;
import ebike.view.components.ReturnBikePreview;
import ebike.view.components.ReturnResult;
import ebike.view.components.StationDetail;
import ebike.view.components.StationList;
import ebike.view.components.StationListReturn;

import java.awt.*;

public class ApplicationUI extends JFrame {
    private IBikeAppService bikeAppService;
    private IDockingStationAppService dockingStationAppService;
    private IUserAppService userAppService;
    private StateStore stateStore;
    private JPanel contentPanel;
    private Header headerPanel;
    private StationDetail stationDetailPanel;
    private StationList stationListPanel;
    private BarCodeInput barCodeInputPanel;
    private RentBikePreview rentBikePreviewPanel;
    private RentalTx rentalTxPanel;
    private StationListReturn stationListReturnPanel;
    private ReturnBikePreview returnBikePreviewPanel;
    private ReturnResult returnResultPanel;

    public ApplicationUI(IBikeAppService bikeAppService, IDockingStationAppService dockingStationAppService,
            IUserAppService userAppService) {
        super("Ebike");

        this.bikeAppService = bikeAppService;
        this.dockingStationAppService = dockingStationAppService;
        this.userAppService = userAppService;

        stateStore = new StateStore();
        stateStore.userId = 1;

        headerPanel = new Header(this);
        stationListPanel = new StationList(this);
        stationDetailPanel = new StationDetail(this);
        barCodeInputPanel = new BarCodeInput(this);
        rentBikePreviewPanel = new RentBikePreview(this);
        rentalTxPanel = new RentalTx(this);
        stationListReturnPanel = new StationListReturn(this);
        returnBikePreviewPanel = new ReturnBikePreview(this);
        returnResultPanel = new ReturnResult(this);

        contentPanel = new JPanel();
        var contentLayout = new CardLayout();
        contentPanel.setLayout(contentLayout);

        contentPanel.add(stationListPanel, "stationList");
        contentPanel.add(stationDetailPanel, "stationDetail");
        contentPanel.add(barCodeInputPanel, "barcodeInput");
        contentPanel.add(rentBikePreviewPanel, "rentBikePreview");
        contentPanel.add(rentalTxPanel, "rentalTx");
        contentPanel.add(stationListReturnPanel, "stationListReturn");
        contentPanel.add(returnBikePreviewPanel, "returnBikePreview");
        contentPanel.add(returnResultPanel, "returnResult");

        contentLayout.show(contentPanel, "stationList");

        contentPanel.setPreferredSize(new Dimension(800, 550));

        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void changeToStationListScreen() {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "stationList");
        stationListPanel.init();
    }

    public void changeToStationDetailScreen(long id) {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "stationDetail");
        stationDetailPanel.init(id);
    }

    public void changeToBarCodeInputScreen() {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "barcodeInput");
        barCodeInputPanel.init();
    }

    public void changeToRentBikePreviewScreen(String code) {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "rentBikePreview");
        rentBikePreviewPanel.init(code);
    }

    public void changeToCurrentRentTxScreen() {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "rentalTx");
        rentalTxPanel.init();
    }

    public void changeToStationListReturnScreen() {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "stationListReturn");
        stationListReturnPanel.init();
    }

    public void changeToReturnBikePreview(long dockId) {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "returnBikePreview");
        returnBikePreviewPanel.init(dockId);
    }

    public void changeToReturnResultScreen() {
        ((CardLayout) contentPanel.getLayout()).show(contentPanel, "returnResult");
        returnResultPanel.init();
    }

    public void resetBalanceView() {
        headerPanel.init();
    }

    public IBikeAppService getBikeAppService() {
        return bikeAppService;
    }

    public void setBikeAppService(IBikeAppService bikeAppService) {
        this.bikeAppService = bikeAppService;
    }

    public IDockingStationAppService getDockingStationAppService() {
        return dockingStationAppService;
    }

    public void setDockingStationAppService(IDockingStationAppService dockingStationAppService) {
        this.dockingStationAppService = dockingStationAppService;
    }

    public IUserAppService getUserAppService() {
        return userAppService;
    }

    public void setUserAppService(IUserAppService userAppService) {
        this.userAppService = userAppService;
    }

    public StateStore getStateStore() {
        return stateStore;
    }

    public void setStateStore(StateStore stateStore) {
        this.stateStore = stateStore;
    }

}
