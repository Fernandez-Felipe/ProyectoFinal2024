package DataBase;

import java.io.Serializable;
import java.util.HashMap;

public class Usser implements Serializable {

    private String Nombre, Contrasenia;
    HashMap<Integer,Turno[][][]> TurnosAño;

    public Usser(String Nombre, String Contrasenia, HashMap<Integer,Turno[][][]> TurnosAño){
        this.Nombre = Nombre;
        this.Contrasenia = Contrasenia;
        this.TurnosAño = TurnosAño;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public HashMap<Integer, Turno[][][]> getTurnosAño() {
        return TurnosAño;
    }

    public void setTurnosAño(HashMap<Integer, Turno[][][]> turnosAño) {
        TurnosAño = turnosAño;
    }

}
