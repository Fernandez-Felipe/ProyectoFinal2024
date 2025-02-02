package DataBase;

import java.io.Serializable;

public class Usser implements Serializable {

    private String Nombre, Contrasenia;

    public String getNombre() {
        return Nombre;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public Usser(String Nombre, String Contrasenia){
        this.Nombre = Nombre;
        this.Contrasenia = Contrasenia;
    }

    @Override
    public String toString() {
        return "Usser{" +
                "Nombre='" + Nombre + '\'' +
                ", Contrasenia='" + Contrasenia + '\'' +
                '}';
    }
}
