package modelos;

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
