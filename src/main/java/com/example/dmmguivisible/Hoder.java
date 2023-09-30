package com.example.dmmguivisible;

import java.sql.ResultSet;

public class Hoder {

    private String[] comNmae;

    private ResultSet resultSetall;

    private String[] resultdataforOneyear;

    private String[] resultdataforTwoyear;

    private String[] resultdatafortoday;

    public String[] getComNmae() {
        return comNmae;
    }

    public void setComNmae(String[] comNmae) {
        this.comNmae = comNmae;
    }

    public ResultSet getResultSetall() {
        return resultSetall;
    }

    public void setResultSetall(ResultSet resultset) {
        this.resultSetall = resultset;
    }

    public void setResultdataforOneyear(String[] resultdataforOneyear) {
        this.resultdataforOneyear = resultdataforOneyear;
    }

    public void setResultdataforTwoyear(String[] resultdataforTwoyear) {
        this.resultdataforTwoyear = resultdataforTwoyear;
    }

    public String[] getResultdataforOneyear() {
        return resultdataforOneyear;
    }

    public String[] getResultdataforTwoyear() {
        return resultdataforTwoyear;
    }

    public String[] getResultdatafortoday() {
        return resultdatafortoday;
    }

    public void setResultdatafortoday(String[] resultdatafortoday) {
        this.resultdatafortoday = resultdatafortoday;
    }
}
