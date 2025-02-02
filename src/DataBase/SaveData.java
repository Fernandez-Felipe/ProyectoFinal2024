package DataBase;
import java.io.*;
public class SaveData {

    Usser NewUser;
    FileOutputStream Archivo;
    ObjectOutputStream OOS;

    public SaveData(Usser NewUser) throws IOException {
        this.NewUser = NewUser;
        Archivo = new FileOutputStream("DataBase/Usuarios.bin",true);
        OOS = new ObjectOutputStream(Archivo);
    }

    public void Save() throws IOException {
        OOS.writeObject(NewUser);
        OOS.close();
    }

}
