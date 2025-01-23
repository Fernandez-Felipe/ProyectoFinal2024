package DataBase;

import java.io.*;
import java.util.List;

public class LoadData {

    public static void CargarUsuarios(List<Usser> Ussers){

        try(ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("Usuarios.bin"))){

            Ussers = (List<Usser>) OIS.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
