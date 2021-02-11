package gorobchuk.alexandr.filter;

import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import gorobchuk.alexandr.enums.InitialConstant;
import gorobchuk.alexandr.waitingtimelineset.WaitingTimeline;

class FilterServise {
    String result;

    static void convertStringToFilter(String value, Filter filter) {
        try {
            String[] params = value.split(" ");
            validateAddedObject(params);
            
            String[] temp = params[1].split("\\.");
            filter.setService(setValue(temp, 0, "Service", InitialConstant.MAXSERVICE));
            filter.setServiceVariations(setValue(temp, 1, "Service Variations", InitialConstant.MAXVARIATION));
    
            temp = params[2].split("\\.");
            filter.setQuestionsType(setValue(temp, 0, "Question Type", InitialConstant.MAXQUESTIONS));
            filter.setQuestionsCategory(setValue(temp, 1, "Question Category", InitialConstant.MAXQUESTIONSCATEGORY));
            filter.setQuestionsSubCategory(setValue(temp, 2, "Question SubCategory", InitialConstant.MAXQUESTIONSSUBCATEGORY));
    //      ResponseType responseType = setResponseType(params[3]);
            
            temp = params[4].split("-");
            filter.setDataStart(setCalendar(temp, 0));
            filter.setDataEnd(setCalendar(temp, 1));
        
        } catch (Exception e){
            System.err.println("Error in method addList. Added parametrs dont match Template.");
            System.err.println("D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time");
            e.printStackTrace(); 
        }

    }

    
    static void startFilter(List<WaitingTimeline> listInput, Filter filterInput){

        if (filterInput.getDataEnd() != null){
            listInput = listInput.stream()
                    .filter(x->x.getData().getTime().after(filterInput.getDataStart().getTime()))
                    .filter(x->x.getData().getTime().before(filterInput.getDataEnd().getTime()))
                    .collect(Collectors.toList());
        } else {
            listInput = listInput.stream()
                    .filter(x->x.getData().get(Calendar.YEAR) == filterInput.getDataStart().get(Calendar.YEAR))
                    .filter(x->x.getData().get(Calendar.MONTH) == filterInput.getDataStart().get(Calendar.MONTH))
                    .filter(x->x.getData().get(Calendar.DATE) == filterInput.getDataStart().get(Calendar.DATE))
                    .collect(Collectors.toList());
        }
        if (filterInput.getService()!=0) {
            listInput = listInput.stream()
                    .filter(x -> x.getService().equals(filterInput.getService()))
                    .collect(Collectors.toList());
        }
        if (filterInput.getServiceVariations() != 0){
            listInput = listInput.stream()
                    .filter((x)-> x.getServiceVariations().equals(filterInput.getServiceVariations()))
                    .collect(Collectors.toList());
        }
        if (filterInput.getQuestionsType() != 0){
            listInput = listInput.stream()
                    .filter(x-> x.getQuestionsType().equals(filterInput.getQuestionsType()))
                    .collect(Collectors.toList());
        }
        if (filterInput.getQuestionsCategory()!= 0){
            listInput = listInput.stream()
                    .filter(x-> x.getQuestionsCategory().equals(filterInput.getQuestionsCategory()))
                    .collect(Collectors.toList());
        }
        if (filterInput.getQuestionsSubCategory()!= 0){
            listInput = listInput.stream()
                    .filter(x-> x.getQuestionsSubCategory().equals(filterInput.getQuestionsSubCategory()))
                    .collect(Collectors.toList());
        }

        if (listInput.size()<=0){
            System.out.println("-");
        } else {
            double summ = listInput.stream().mapToInt((x)->Integer.parseInt(x.getTime().toString())).sum() / (double) listInput.size();
            System.out.println(summ);
        }
        
    }
    private static Integer setValue(String[] input, int position, String nameVariable, InitialConstant cons){

        String service;
        try {
            service = input[position];
        } catch (Exception e){
            return 0;
        }
        if (input[position].equals("*")){
            return 0;
        }
        Integer result;
        try {
            result = Integer.parseInt(service);
        } catch (Exception e){
            throw new IllegalArgumentException(e+"\n"+"Incorrect "+nameVariable+" value. Insert correct value variation: 3.1 or 5 | Incorrect "+nameVariable+": '" + Arrays.toString(input) + "'");
        }
        if (result > cons.getValue()){
            throw new IllegalArgumentException("Incorrect "+nameVariable+" value. Max value: " + cons.getValue() + ". Insert correct value "+nameVariable+". | Incorrect value: '" + Arrays.toString(input) + "'");
        }
        if (result < 1){
            throw new IllegalArgumentException("Incorrect "+nameVariable+" value. Min value: 1. Insert correct value "+nameVariable+". | Incorrect value: '" + Arrays.toString(input) + "'");
        }
        return result;
    }
    private static Calendar setCalendar(String[] input, int position){
        String value;
        try {
            value = input[position];
        } catch (Exception e){
            return null;
        }
        String[] strings = value.split("\\.");
        if (strings.length != 3){
            throw new IllegalArgumentException("Incorrect Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input) + "'");
        }

        Calendar calendar = new GregorianCalendar();
        Integer year;
        Integer month;
        Integer day;

        try{
            year = Integer.parseInt(strings[2]);
            month = Integer.parseInt(strings[1]);
            day = Integer.parseInt(strings[0]);
        } catch (Exception e){
            throw new IllegalArgumentException(e+"\n"+"Incorrect Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input) + "'");
        }

        if (String.valueOf(year).toCharArray().length != 4 || year > Year.now().getValue()){
            throw new IllegalArgumentException("Incorrect years in Date. Insert correct date in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input) + "'");
        }
        if (month > 12 || month < 1){
            throw new IllegalArgumentException("Incorrect month in Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input) + "'");
        }
        YearMonth yearsMonth = YearMonth.of(year, month);
        if (yearsMonth.isAfter(YearMonth.now())){
            throw new IllegalArgumentException("Incorrect month in Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input) + "'");
        }
        int daysOfMonth = yearsMonth.lengthOfMonth();

        if (day > daysOfMonth ||day < 0){
            throw new IllegalArgumentException("Incorrect days in Date. Max Days "+daysOfMonth+" in "+yearsMonth+ ".Insert correct in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input) + "'");
        }

        calendar.set(year, month-1, day);
        if (calendar.after(GregorianCalendar.getInstance())){
            throw new IllegalArgumentException("Incorrect Date. Date object > date now. Insert correct in format dd.mm.yyyy | Incorrect value: '" + Arrays.toString(input)+ "'");
        }
        return calendar;
    }
//    private ResponseType setResponseType(String input){
//        if (input.equals("P")){
//            return ResponseType.P;
//        } else if (input.equals("N")){
//            return ResponseType.N;
//        } else{
//            throw new IllegalArgumentException("Incorrect ResponseType. Insert correct P or N | Incorrect value: '" + input + "'");
//        }
//    }
    private static void validateAddedObject(String[] params){

        if (!params[0].equals("D")){
            throw new IllegalArgumentException("Added Object to list FilterDOP Must Be D (" + Arrays.toString(params) + ")" );
        }
    }

    public String getResult() {
        return result;
    }
}
