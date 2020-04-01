package es.uji.ei1027.majorsacasa.model;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

public class Invoice {

    private String dniElderlyPeople;
    private String codInvoice;
    private Float price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private ArrayList<Line> lines;

    public Invoice() {
    }

    public Invoice(String dniElderlyPeople, String codInvoice, Float price, LocalDate date) {
        this.dniElderlyPeople = dniElderlyPeople;
        this.codInvoice = codInvoice;
        this.price = price;
        this.date = date;
        this.lines = new ArrayList<Line>();
    }

    public String getDniElderlyPeople() {
        return dniElderlyPeople;
    }

    public void setDniElderlyPeople(String dniElderlyPeople) {
        this.dniElderlyPeople = dniElderlyPeople;
    }

    public String getCodInvoice() {
        return codInvoice;
    }

    public void setCodInvoice(String codInvoice) {
        this.codInvoice = codInvoice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addLine(Line line){
        this.lines.add(line);
    }

    public ArrayList<Line> getLines(){
        return this.lines;
    }
}
