package DataBase;

import java.io.Serializable;

public class Usser implements Serializable {

    private String Nombre, Contrasenia;

    public Usser(String Nombre, String Contrasenia){
        this.Nombre = Nombre;
        this.Contrasenia = Contrasenia;
    }

}
