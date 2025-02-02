package DataBase;
import java.io.*;
public class SaveData {

    Usser NewUser;
    FileOutputStream Archivo;
    ObjectOutputStream OOS;

    public SaveData(Usser NewUser) throws IOException {
        this.NewUser = NewUser;

        File file = new File("DataBase/Usuarios.bin");

        boolean Append = file.exists() && file.length() > 0;

        Archivo = new FileOutputStream(file,true);
        OOS = Append ? new AppendObjectOutputStream(Archivo) : new ObjectOutputStream(Archivo);
    }

    public void Save() throws IOException {
        OOS.writeObject(NewUser);
        OOS.close();
    }

    private static class AppendObjectOutputStream extends ObjectOutputStream {
        public AppendObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();  // Evita escribir una nueva cabecera
        }
    }

}
