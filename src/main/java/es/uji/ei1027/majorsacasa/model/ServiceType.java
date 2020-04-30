package es.uji.ei1027.majorsacasa.model;

/**
 * El enum realiza la transformación
 */

public enum ServiceType {
    CATTERING(0,"Servicio de catering"),
    CLEAR(1,"Servicio de limpieza"),
    HEALTH(2,"Servicio de salud");

    private final String descripcion;
    private final int position;

    private ServiceType(int pos, String descripcion){
        this.descripcion = descripcion;
        this.position = pos;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public int getPosition() {
        return this.position;
    }

    public static ServiceType getOpcion(int posicion){
        return values()[posicion];
    }
}
