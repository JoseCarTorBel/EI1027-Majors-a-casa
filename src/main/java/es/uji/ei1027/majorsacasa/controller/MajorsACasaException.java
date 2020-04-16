package es.uji.ei1027.majorsacasa.controller;

public class MajorsACasaException extends RuntimeException {

    String message;  // Missatge per mostrar a la vista
    String errorName;     // Identificador de l’error
    String returnPath;

    public MajorsACasaException(String message, String errorName)
    {
        this.message=message;
        this.errorName=errorName;
        this.returnPath="redirect:/";
    }



    public MajorsACasaException(String message, String errorName, String returnPath)
    {
        this.message=message;
        this.errorName=errorName;
        this.returnPath=returnPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getReturnPath() {
        return returnPath;
    }

    public void setReturnPath(String returnPath) {
        this.returnPath = returnPath;
    }

}