package org.tennisscoreboard.utils;

import lombok.Getter;

@Getter
public class Pagination {

    long previousPage;
    long currentPage;
    long nextPage;
    long previousPageView;
    long currentPageView;
    long nextPageView;
    long lastPage;
    long currentPageNumber;

    public Pagination(long currentPageNumber, long matchCount) {
        previousPage = 0;
        currentPage = 0;
        nextPage = 0;
        previousPageView = 0;
        currentPageView = 0;
        nextPageView = 0;
        lastPage = 0;
        setPagination(currentPageNumber, matchCount);
    }

    public void setPagination(long currentPageNumber, long matchCount){
        this.currentPageNumber =currentPageNumber;
        if(currentPageNumber <=1){
            previousPage=1;
            currentPage=2;
            nextPage=3;
            previousPageView=1;
            currentPageView=2;
            nextPageView=3;
        } else {
            previousPage= currentPageNumber -1;
            currentPage= currentPageNumber;
            nextPage= currentPageNumber +1;
            previousPageView=previousPage;
            currentPageView=currentPage;
            nextPageView=nextPage;
        }
        if(matchCount%5!=0){
            lastPage=matchCount/5+1;
        } else {
            lastPage = matchCount/5;
        }
        if(currentPage>lastPage){
            currentPage= currentPageNumber;
            nextPage= currentPageNumber;
        }
        if(currentPageNumber==lastPage){
            nextPage=currentPageNumber;
            currentPage=currentPageNumber-1;
            previousPage=currentPageNumber-2;
            previousPageView=currentPageNumber-2;
            currentPageView=currentPageNumber-1;
            nextPageView=currentPageNumber;
        }
    }


    public boolean isPreviousSelected() {
        return currentPageNumber == previousPageView;
    }

     public boolean isCurrentSelected(){
        return currentPageNumber==currentPageView;
    }

    public boolean isNextSelected() {
        return currentPageNumber==nextPageView;
    }

}
