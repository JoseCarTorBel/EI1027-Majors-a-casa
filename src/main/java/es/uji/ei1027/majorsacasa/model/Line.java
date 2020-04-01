package es.uji.ei1027.majorsacasa.model;

public class Line {

    private String codInvoice;
    private String codRequest;
    private String concept;
    private Request request;
    private Float price;

    public Line() { }

    public Line(String codInvoice, String codRequest, String concept, Request request, Float price) {
        this.codInvoice = codInvoice;
        this.codRequest = codRequest;
        this.concept = concept;
        this.request = request;
        this.price = price;
    }

    public String getCodInvoice() {
        return codInvoice;
    }

    public void setCodInvoice(String codInvoice) {
        this.codInvoice = codInvoice;
    }

    public String getCodRequest() {
        return codRequest;
    }

    public void setCodRequest(String codRequest) {
        this.codRequest = codRequest;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }


}
