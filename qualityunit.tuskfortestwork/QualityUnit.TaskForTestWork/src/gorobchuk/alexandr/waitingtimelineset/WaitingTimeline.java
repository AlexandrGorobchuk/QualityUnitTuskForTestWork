package gorobchuk.alexandr.waitingtimelineset;

import java.util.Calendar;

import gorobchuk.alexandr.enums.ResponseType;

public class WaitingTimeline {
    private Integer service;
    private Integer serviceVariations;
    private Integer questionsType;
    private Integer questionsCategory;
    private Integer questionsSubCategory;
    private ResponseType responseType;
    private Calendar data;
    private Integer time;
    
    public WaitingTimeline (String params) {
       WaitingTimelineServise.convertStringToWaitingTimeline(params, this);
    }
    
    public WaitingTimeline(Integer service, Integer serviceVariations, Integer questionsType, Integer questionsCategory, Integer questionsSubCategory, ResponseType responseType, Calendar data, Integer time) {
        this.service = service;
        this.serviceVariations = serviceVariations;
        this.questionsType = questionsType;
        this.questionsCategory = questionsCategory;
        this.questionsSubCategory = questionsSubCategory;
        this.responseType = responseType;
        this.data = data;
        this.time = time;
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


    void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }


    void setData(Calendar data) {
        this.data = data;
    }


    void setTime(Integer time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "WaitingTimeline{" +
                "service=" + service +
                ", serviceVariations=" + serviceVariations +
                ", questionsType=" + questionsType +
                ", questionsCategory=" + questionsCategory +
                ", questionsSubCategory=" + questionsSubCategory +
                ", responseType=" + responseType +
                ", data=" + data.get(Calendar.DATE)+"."+(data.get(Calendar.MONTH)+1)+"."+data.get(Calendar.YEAR) +
                ", time=" + time +
                '}';
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

    public ResponseType getResponseType() {
        return responseType;
    }

    public Calendar getData() {
        return data;
    }

    public Integer getTime() {
        return time;
    }
}
