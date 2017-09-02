import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Database {
    public static ArrayList<Record> RECORDS = new ArrayList<>();
    public static void loadData(){
        File file = new File("data.csv");;
        try{
            if(!file.exists())
                throw new FileNotFoundException("The data.csv file does not exist");
            Scanner input = new Scanner(file);
            boolean errorInLine = false;
            while(input.hasNextLine()){
                try {
                    String[] data = input.nextLine().split(",");
                    Record record = new Record();

                    record.seriesDate.setDateByString(data[0]);
                    record.purchaseDate.setDateByString(data[1]);
                    record.saleDate.setDateByString(data[2]);
                    record.setEquityName(data[3]);
                    record.setLotSize(Integer.parseInt(data[4]));
                    record.setNumberOfLots(Integer.parseInt(data[5]));
                    record.setPurchasePrice(new BigDecimal(data[6]));
                    record.setSalePrice(new BigDecimal(data[7]));
                    record.setPurchaseBrokerage(new BigDecimal(data[8]));
                    record.setSaleBrokerage(new BigDecimal(data[9]));
                    record.setNetProfitLoss(new BigDecimal(data[10]));

                    record.convertToStrings();
                    RECORDS.add(record);
                }catch (Exception e){
                    errorInLine = true;
                }

                if(errorInLine)
                    throw new Exception("Could not read a few records");
            }
        }catch(Exception e){
            MessageBox.display("Data read error", "An error occured while reading data from file\n" + e.getLocalizedMessage());
        }
    };
    public static boolean saveData(){
        File file;
        Formatter formatter;
        try{
            file = new File("data.csv");
            formatter = new Formatter(file);
            for(Record record: Database.RECORDS)
                formatter.format("%s,%s,%s,%s,%d,%d,%s,%s,%s,%s,%s\n", record.getSeriesDate(), record.getPurchaseDate(),
                        record.getSaleDate(), record.getEquityName(), record.getNumberOfLots(),
                        record.getLotSize(), record.getPurchasePriceString(), record.getSalePriceString(),
                        record.getPurchaseBrokerageString(), record.getSaleBrokerageString(),
                        record.getNetProfitLoss().toString());
            formatter.close();
        }catch (Exception e){
            return  YesNoBox.display("Unable to save the data\n" + e.getLocalizedMessage() + "\nAre you sure you want to quit?" );
        }
        return true;
    };
}
