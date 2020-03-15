package es.uji.ei1027.majorsacasa.model;

public class Line {

    private String concept;
    private Request request;
    private float price;

    public Line() { }

    public Line(String concept, Request request, float price) {

        this.concept = concept;
        this.request = request;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
