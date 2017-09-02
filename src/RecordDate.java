public class RecordDate implements Comparable<RecordDate>{
    int date;
    int month;
    int year;
    public RecordDate(int date, int month, int year){
        this.date = date;
        this.month = month;
        this.year = year;
        checkDate();
    }
    public String toString(){
        //return date + "." + month + "." + year;
        return String.format("%2d-%2d-%4d", date, month, year).replace(' ', '0');
    }
    @Override
    public int compareTo(RecordDate d) {
        if(year < d.year)
            return -1;
        if(year > d.year)
            return 1;
        if(month < d.month)
            return -1;
        if(month > d.month)
            return 1;
        if(date < d.date)
            return -1;
        if(date > d.date)
            return 1;
        return 0;
    }
    public void setDateByString(String dateByString) {
        String[] fields = dateByString.split("-");
        date = Integer.parseInt(fields[0]);
        month = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
        checkDate();
    }
    public void setDateByStringFromDatePicker(String dateByDatePicker){
        String[] fields = dateByDatePicker.split("-");
        date = Integer.parseInt(fields[2]);
        month = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[0]);
        checkDate();
    }
    private void checkDate(){
        if(date < 0 || month < 0 || year < 0)
            throw new IllegalArgumentException("Incorrect date");
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(date > 31)
                    throw new IllegalArgumentException("Incorrect date");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if(date > 30)
                    throw new IllegalArgumentException("Incorrect date");
                break;
            case 2:
                //if((year%4 == 0) && (date > 29))
                //    throw new IllegalArgumentException("Incorrect date");
                if(date > 29)
                    throw new IllegalArgumentException("Incorrect date");
                break;
            default:
                throw new IllegalArgumentException("Incorrect date");
        }
    }
}