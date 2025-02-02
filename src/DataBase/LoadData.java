package DataBase;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoadData {
    ArrayList<Usser> UsuariosRegistrados = new ArrayList<>();
    ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("DataBase/Usuarios.bin"));
    public LoadData() throws IOException {}

    public ArrayList<Usser> CargarUsuarios(){
        while (true) {
            try {
                Usser Usuario = (Usser) OIS.readObject();
                UsuariosRegistrados.add(Usuario);
            } catch (ClassNotFoundException | IOException e) {
                return UsuariosRegistrados;
            }
        }
    }

}
