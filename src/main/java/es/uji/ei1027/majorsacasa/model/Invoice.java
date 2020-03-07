package es.uji.ei1027.majorsacasa.model;

import java.sql.Date;
import java.util.ArrayList;

public class Invoice {

    private String codInvoice;
    private float price;
    private Date date;
    private ArrayList<Line> lines;

    public Invoice() {
    }

    public Invoice(String codInvoice, float price, Date date) {
        this.codInvoice = codInvoice;
        this.price = price;
        this.date = date;
        this.lines = new ArrayList<Line>();
    }

    public String getCodInvoice() {
        return codInvoice;
    }

    public void setCodInvoice(String codInvoice) {
        this.codInvoice = codInvoice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addLine(Line line){
        this.lines.add(line);
    }

    public ArrayList<Line> getLines(){
        return this.lines;
    }
}
