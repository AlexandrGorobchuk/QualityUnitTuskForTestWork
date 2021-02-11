package gorobchuk.alexandr.filter;

import java.util.Calendar;
import java.util.List;

import gorobchuk.alexandr.waitingtimelineset.WaitingTimeline;

public class Filter {
    private Integer service;
    private Integer serviceVariations;
    private Integer questionsType;
    private Integer questionsCategory;
    private Integer questionsSubCategory;
    private Calendar dataStart;
    private Calendar dataEnd;

    public Filter(String input, List<WaitingTimeline> waitingTimeline) {
        FilterServise.convertStringToFilter(input, this);
        FilterServise.startFilter(waitingTimeline, this);
        
    }
    
    public Filter(String input) {
        FilterServise.convertStringToFilter(input, this);
        System.out.println(this.toString());
    }
    
    public void getResultFilter(List<WaitingTimeline> waitingTimeline) {
        FilterServise.startFilter(waitingTimeline, this);
    }
    
    public void setFilter (String input) {
        FilterServise.convertStringToFilter(input, this);
    }
    
    public void getResult (List<WaitingTimeline> waitingTimeline) {
        FilterServise.startFilter(waitingTimeline, this);
    }
    
    void setService(Integer service) {
        this.service = service;
    }

    void setServiceVariations(Integer serviceVariations) {
        this.serviceVariations = serviceVariations;
    }

    void setQuestionsType(Integer questionsType) {
        this.questionsType = questionsType;
    }

    void setQuestionsCategory(Integer questionsCategory) {
        this.questionsCategory = questionsCategory;
    }

    void setQuestionsSubCategory(Integer questionsSubCategory) {
        this.questionsSubCategory = questionsSubCategory;
    }

    void setDataStart(Calendar dataStart) {
        this.dataStart = dataStart;
    }

    void setDataEnd(Calendar dataEnd) {
        this.dataEnd = dataEnd;
    }

    public Integer getService() {
        return service;
    }

    public Integer getServiceVariations() {
        return serviceVariations;
    }

    public Integer getQuestionsType() {
        return questionsType;
    }

    public Integer getQuestionsCategory() {
        return questionsCategory;
    }

    public Integer getQuestionsSubCategory() {
        return questionsSubCategory;
    }

    public Calendar getDataStart() {
        return dataStart;
    }

    public Calendar getDataEnd() {
        return dataEnd;
    }

    @Override
    public String toString() {
        String dataEndNull;
        if (dataEnd == null){
            dataEndNull = null;
        } else {
            dataEndNull = dataEnd.get(Calendar.DATE) +"."+ dataEnd.get(Calendar.MONTH) +"."+ dataEnd.get(Calendar.YEAR);
        }

        return "FilterDOP{" +
                "service=" + service +
                ", serviceVariations=" + serviceVariations +
                ", questionsType=" + questionsType +
                ", questionsCategory=" + questionsCategory +
                ", questionsSubCategory=" + questionsSubCategory +
                ", dataStart=" + dataStart.get(Calendar.DATE) +"."+ dataStart.get(Calendar.MONTH) +"."+ dataStart.get(Calendar.YEAR) +
                ", dataEnd=" + dataEndNull +
                '}';
    }
}
