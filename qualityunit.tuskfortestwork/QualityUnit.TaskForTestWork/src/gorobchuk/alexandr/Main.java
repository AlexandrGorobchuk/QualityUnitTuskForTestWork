package gorobchuk.alexandr;

import java.util.ArrayList;
import java.util.List;

import gorobchuk.alexandr.filter.Filter;
import gorobchuk.alexandr.waitingtimelineset.WaitingTimeline;

public class Main {

    public static void main(String[] args) {
        List <WaitingTimeline> list = new ArrayList<>();
        list.add(new WaitingTimeline("C 1.1 8.15.1 P 15.10.2012 83"));
        list.add(new WaitingTimeline("C 1 10.1 P 01.12.2012 65"));
        list.add(new WaitingTimeline("C 1.1 5.5.1 P 01.11.2012 117"));
        list.add(new WaitingTimeline("C 3 10.2 N 02.10.2012 100"));
        
        Filter filterList = new Filter("D 1.1 8 P 01.01.2012-01.12.2012", list);
        
        filterList.setFilter("D 1 * P 08.10.2012-20.11.2012");
        filterList.getResultFilter(list);
        
        filterList.setFilter("D 3 10 P 01.12.2012");
        filterList.getResultFilter(list);

    }

}
