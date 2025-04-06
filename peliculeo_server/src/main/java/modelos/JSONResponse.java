package modelos;

/**
 * Esta clase se usa para enviar un mensaje de OK (200/201) ó ERROR (400/204...) al
 * cliente que consuma la API.
 * Consta de una variable booleana, que indica si la petición se ha realizado correctamente,
 * y de una variable msg, en la que se guardará generalmente un string con un mensaje
 * para el consumidor de la API.
 */
public class JSONResponse {
    private boolean done;
    private String msg;

    public JSONResponse(boolean done, String msg) {
        this.done = done;
        this.msg = msg;
    }

    public JSONResponse() {
        done = true;
        msg = "Este es mensaje por defecto :s";
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
