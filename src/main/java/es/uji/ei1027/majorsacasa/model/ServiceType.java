package es.uji.ei1027.majorsacasa.model;

/**
 * El enum realiza la transformación
 */

public enum ServiceType {
    CATTERING(0),
    CLEAR(1),
    HEALTH(2);

    private final int valueService;

    private ServiceType(int value){
        this.valueService=value;
    }
}
