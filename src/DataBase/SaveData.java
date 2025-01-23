package DataBase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveData {
    Usser NewUsser;
    List<Usser> Usuarios = new ArrayList<>();

    public SaveData(Usser NewUsser){
        this.NewUsser = NewUsser;
    }

    public void GuardarUsuario(){

        LoadData.CargarUsuarios(Usuarios);
        Usuarios.add(NewUsser);

        try(ObjectOutputStream OUS = new ObjectOutputStream(new FileOutputStream("Usuarios.bin"))){

            OUS.writeObject(Usuarios);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
