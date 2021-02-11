package gorobchuk.alexandr.waitingtimelineset;

import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import gorobchuk.alexandr.enums.InitialConstant;
import gorobchuk.alexandr.enums.ResponseType;

class WaitingTimelineServise {

    static void convertStringToWaitingTimeline (String input, WaitingTimeline waitingTineLine ){
        try {
            String[] params = input.split(" ");
            validateTypeAddedObject(params);
            
            String[] temp = params[1].split("\\.");
            waitingTineLine.setService(setValue(temp, 0, "Service", InitialConstant.MAXSERVICE));
            waitingTineLine.setServiceVariations(setValue(temp, 1, "Service Variations", InitialConstant.MAXVARIATION));

            temp = params[2].split("\\.");
            waitingTineLine.setQuestionsType(setValue(temp, 0, "Question Type", InitialConstant.MAXQUESTIONS));
            waitingTineLine.setQuestionsCategory(setValue(temp, 1, "Question Category", InitialConstant.MAXQUESTIONSCATEGORY));
            waitingTineLine.setQuestionsSubCategory(setValue(temp, 2, "Question SubCategory", InitialConstant.MAXQUESTIONSSUBCATEGORY));

            waitingTineLine.setResponseType(setResponseType(params[3]));
            waitingTineLine.setData(setCalendar(params[4]));
            waitingTineLine.setTime(setTime(params[5]));
        } catch (Exception e) {
            System.err.println("Error in method addList. Added parametrs dont match Template:");
            System.err.println("C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time");
            e.printStackTrace();  
        }
    }

    private static Integer setValue(String[] input, int position, String nameVariable, InitialConstant cons){
        String service;
        Integer result;
        
        try {
            service = input[position];
        } catch (Exception e){
            return 0;
        }
        try {
            result = Integer.parseInt(service);
        } catch (Exception e){
            throw new IllegalArgumentException(e+"\n"+"Incorrect "+nameVariable+" value. Insert correct value variation: 3.1 or 5 | Incorrect "+nameVariable+": '" + Arrays.toString(input) + "'");
        }
    
        if (result > cons.getValue()) {
            throw new IllegalArgumentException("Incorrect "+nameVariable+" value. Max value: " + cons.getValue() + ". Insert correct value "+nameVariable+". | Incorrect value: '" + Arrays.toString(input) + "'");
        } else if (result < 1) {
            throw new IllegalArgumentException("Incorrect "+nameVariable+" value. Min value: 1. Insert correct value "+nameVariable+". | Incorrect value: '" + Arrays.toString(input) + "'");
        }

        return result;
    }
    private static Integer setTime (String input){
        Integer result;
        try {
            result = Integer.parseInt(input);
        } catch (Exception e){
            throw new IllegalArgumentException(e+"\n"+"Incorrect time value. Insert correct time: 84, 3. value: '" + input + "'");
        }
        if (result<1){
            throw new IllegalArgumentException("Incorrect time value. Min correct time = 1. value: '" + input + "'");
        }
        return result;
    }
    private static Calendar setCalendar(String value){
        Integer years, month, day;
        String[] strings = value.split("\\.");
        if (strings.length != 3){
            throw new IllegalArgumentException("Incorrect Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        }
        
        Calendar calendar = new GregorianCalendar();

        try{
            years = Integer.parseInt(strings[2]);
            month = Integer.parseInt(strings[1]);
            day = Integer.parseInt(strings[0]);

        } catch (Exception e){
            throw new IllegalArgumentException(e+"\n"+"Incorrect Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        }

        if (String.valueOf(years).toCharArray().length != 4 || years > Year.now().getValue()){
            throw new IllegalArgumentException("Incorrect years in Date. Insert correct date in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        } else if (month > 12 || month < 1){
            throw new IllegalArgumentException("Incorrect month in Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        }
        
        YearMonth yearsMonth = YearMonth.of(years, month);
        if (yearsMonth.isAfter(YearMonth.now())){
            throw new IllegalArgumentException("Incorrect month in Date. Insert correct in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        }
        
        int maxDaysOfMonth = yearsMonth.lengthOfMonth();
        if (day > maxDaysOfMonth){
            throw new IllegalArgumentException("Incorrect day in Date. Max day "+maxDaysOfMonth+" in "+yearsMonth+ ".Insert correct in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        } else if (day < 1){
            throw new IllegalArgumentException("Incorrect day in Date. Min day 1.Insert correct in format dd.mm.yyyy | Incorrect value: '" + value + "'");
        }

        calendar.set(years, month-1, day);
        if (calendar.after(GregorianCalendar.getInstance())){
            throw new IllegalArgumentException("Incorrect Date. Date object > date now. Insert correct in format dd.mm.yyyy | Incorrect value: '" + value+ "'");
        }
        return calendar;
    }
    private static ResponseType setResponseType(String input){
        switch (input) {
            case "P":
                return ResponseType.P;
            case "N":
                return ResponseType.N;
            default:
                throw new IllegalArgumentException("Incorrect Response Type. Insert correct P or N | Incorrect value: '" + input + "'");
        }
    }
    private static void validateTypeAddedObject(String[] params){

        if (!params[0].equals("C")){
            throw new IllegalArgumentException("Added Object to list WaitingTimelineList Must Be C (" + Arrays.toString(params) + ")" );
        }
    }
}
