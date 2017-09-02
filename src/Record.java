import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Record {
    RecordDate seriesDate = new RecordDate(1, 1, 1);
    RecordDate purchaseDate = new RecordDate(1, 1, 1);
    RecordDate saleDate = new RecordDate(1, 1, 1);
    String equityName;
    int numberOfLots;
    int lotSize;
    BigDecimal purchasePrice  = new BigDecimal("0");
    BigDecimal salePrice = new BigDecimal("0") ;
    BigDecimal purchaseBrokerage = new BigDecimal("0");
    BigDecimal saleBrokerage = new BigDecimal("0");
    BigDecimal netProfitLoss = new BigDecimal("0");

    String purchasePriceString;
    String salePriceString;
    String purchaseBrokerageString;
    String saleBrokerageString;
    String netProfitLossString;
    private DecimalFormat df = new DecimalFormat("#.##");

    public RecordDate getSeriesDate() {
        return seriesDate;
    }
    public void setSeriesDate(RecordDate seriesDate) {
        this.seriesDate = seriesDate;
    }
    public RecordDate getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(RecordDate saleDate) {
        this.saleDate = saleDate;
    }
    public RecordDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(RecordDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getEquityName() {
        return equityName;
    }
    public void setEquityName(String equityName) {
        this.equityName = equityName;
    }
    public int getNumberOfLots() {
        return numberOfLots;
    }
    public void setNumberOfLots(int numberOfLots) {
        this.numberOfLots = numberOfLots;
    }
    public int getLotSize() {
        return lotSize;
    }
    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public BigDecimal getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    public BigDecimal getPurchaseBrokerage() {
        return purchaseBrokerage;
    }
    public void setPurchaseBrokerage(BigDecimal purchaseBrokerage) {
        this.purchaseBrokerage = purchaseBrokerage;
    }
    public BigDecimal getSaleBrokerage() {
        return saleBrokerage;
    }
    public void setSaleBrokerage(BigDecimal saleBrokerage) {
        this.saleBrokerage = saleBrokerage;
    }
    public BigDecimal getNetProfitLoss() {
        return netProfitLoss;
    }
    public void setNetProfitLoss(BigDecimal netProfitLoss) {
        this.netProfitLoss = netProfitLoss;
    }

    public String getPurchasePriceString() {
        return purchasePriceString;
    }
    public void setPurchasePriceString(String purchasePriceString) {
        this.purchasePriceString = purchasePriceString;
    }
    public String getSalePriceString() {
        return salePriceString;
    }
    public void setSalePriceString(String salePriceString) {
        this.salePriceString = salePriceString;
    }
    public String getPurchaseBrokerageString() {
        return purchaseBrokerageString;
    }
    public void setPurchaseBrokerageString(String purchaseBrokerageString) {
        this.purchaseBrokerageString = purchaseBrokerageString;
    }
    public String getSaleBrokerageString() {
        return saleBrokerageString;
    }
    public void setSaleBrokerageString(String saleBrokerageString) {
        this.saleBrokerageString = saleBrokerageString;
    }
    public String getNetProfitLossString() {
        return netProfitLossString;
    }
    public void setNetProfitLossString(String netProfitLossString) {
        this.netProfitLossString = netProfitLossString;
    }

    public void convertToStrings(){
        purchasePriceString = df.format(purchasePrice);
        salePriceString = df.format(salePrice);
        purchaseBrokerageString = df.format(purchaseBrokerage);
        saleBrokerageString = df.format(saleBrokerage);
        netProfitLossString = df.format(netProfitLoss);

        purchasePrice = new BigDecimal(purchasePriceString);
        salePrice = new BigDecimal(salePriceString);
        purchaseBrokerage = new BigDecimal(purchaseBrokerageString);
        saleBrokerage = new BigDecimal(saleBrokerageString);
        netProfitLoss = new BigDecimal(netProfitLossString);
    }

    public void calculate(){
        int numberOfShares = lotSize*numberOfLots;
        //Purchase Brokerage
        purchaseBrokerage = purchasePrice.multiply(new BigDecimal(numberOfShares)).multiply(calculateBrokerageFactor(purchasePrice)).divide(new BigDecimal("100"));
        //Sale Brokerage
        saleBrokerage = salePrice.multiply(new BigDecimal(numberOfShares)).multiply(calculateBrokerageFactor(salePrice)).divide(new BigDecimal("100"));
        //Net Profit or loss
        netProfitLoss = salePrice.subtract(purchasePrice).multiply(new BigDecimal(numberOfShares)).subtract(purchaseBrokerage.add(saleBrokerage));
    }
    private BigDecimal calculateBrokerageFactor(BigDecimal price){
        BigDecimal factor = new BigDecimal("0.03");
        if(price.compareTo(new BigDecimal("100")) <= 0)
            return factor;
        factor = new BigDecimal("0.02");
        BigDecimal factorTwo = new BigDecimal(price.toString());
        factorTwo = factorTwo.subtract(new BigDecimal("100"));
        factorTwo = factorTwo.divideToIntegralValue(new BigDecimal("50"));
        factorTwo = factorTwo.multiply(new BigDecimal("0.01"));
        if(price.remainder(new BigDecimal("50")).compareTo(BigDecimal.ZERO) > 0)
            factorTwo = factorTwo.add(new BigDecimal("0.01"));
        factor = factor.add(factorTwo);
        return factor;
    }
}
