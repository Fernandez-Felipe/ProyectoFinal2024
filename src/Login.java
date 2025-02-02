import DataBase.SaveData;
import DataBase.Usser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Login extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new Login();
    }

    Point Ubicacion;
    JLabel Bienvenida, Email, Password;
    JTextField TFEmail, TFPassword;
    JButton Entrar, Registrase;
    ImagenDeFondo Fondo = new ImagenDeFondo(this);
    public Login(){

        setLayout(null);
        setSize(350,485);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setContentPane(Fondo);

        Init();

        setResizable(false);
        setVisible(true);

    }

    private void Init(){

        Ubicacion = this.getLocation();

        Bienvenida = new JLabel("Bienvenido");
        Bienvenida.setBounds(75,50,200,55);
        Bienvenida.setFont(new Font("Great Vibes",1,48));
        Bienvenida.setForeground(Color.black);
        add(Bienvenida);

        Email = new JLabel("Usuario");
        Email.setBounds(50,170,100,30);
        add(Email);

        TFEmail = new JTextField();
        TFEmail.setBounds(50,200,230,30);
        add(TFEmail);

        Password = new JLabel("Contraseña");
        Password.setBounds(50,250,100,30);
        add(Password);

        TFPassword = new JTextField();
        TFPassword.setBounds(50,280,230,30);
        add(TFPassword);

        Entrar = new JButton("Entrar");
        Entrar.setBounds(50,320,100,30);
        Entrar.addActionListener(this);
        add(Entrar);

        Registrase = new JButton("Resgistarse");
        Registrase.setBounds(160,320,120,30);
        Registrase.addActionListener(this);
        add(Registrase);

    }

    //Intento de validar a un usuario
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == Entrar){

            try {
                ObjectInputStream OIS = new ObjectInputStream(new FileInputStream("DataBase/Usuarios.bin"));

                while(true){

                    try {
                        Usser UsuarioLeido = (Usser) OIS.readObject();

                        if(ValidarDatos(TFEmail.getText(),TFPassword.getText(),UsuarioLeido)){
                            new Main();
                            this.setVisible(false);
                            break;
                        }

                    }catch (EOFException | ClassNotFoundException ex){
                        JOptionPane.showMessageDialog(this,"Usuario o contraseña inconrrectos");
                        break;
                    }

                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Usuario o contraseña inconrrectos");
            }
        }
        if(e.getSource() == Registrase){
            new Register();
        }

    }

    public boolean ValidarDatos(String Name, String Password, Usser Usuario){

        return Name.equals(Usuario.getNombre()) && Password.equals(Usuario.getContrasenia());
    }

    private class Register extends JFrame implements ActionListener{

        JLabel NombreReg, ContraseniaReg;
        JTextField NameReg, PassWordReg;
        JButton AceptarReg, CancelarReg;

        public Register(){
            setLayout(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setContentPane(new ImagenDeFondo(this));
            setLocationRelativeTo(null);
            setBounds((int) Ubicacion.getX()+25, (int) Ubicacion.getY()+15,300,450);

            setVisible(true);

            InitReg();
        }

        private void InitReg(){

            NombreReg = new JLabel("Nombre de usuario");
            NombreReg.setBounds(50,50,175,30);
            add(NombreReg);

            NameReg = new JTextField();
            NameReg.setBounds(50,85,175,30);
            add(NameReg);

            ContraseniaReg = new JLabel("Contraseña");
            ContraseniaReg.setBounds(50,140,175,30);
            add(ContraseniaReg);

            PassWordReg = new JTextField();
            PassWordReg.setBounds(50,170,175,30);
            add(PassWordReg);

           AceptarReg = new JButton("Aceptar");
           AceptarReg.setBounds(50,270,80,30);
           AceptarReg.addActionListener(this);
           add(AceptarReg);

           CancelarReg = new JButton("Salir");
           CancelarReg.setBounds(145,270,80,30);
           CancelarReg.addActionListener(this);
           add(CancelarReg);

        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == CancelarReg) dispose();

            if(e.getSource() == AceptarReg){

                if(!(NameReg.equals("") && PassWordReg.equals(""))) {
                    Usser NuevoUsuario = new Usser(NameReg.getText(), PassWordReg.getText());
                    try {
                        SaveData SD = new SaveData(NuevoUsuario);
                        SD.Save();
                        dispose();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } else JOptionPane.showMessageDialog(this,"Complete todas las casillas");

            }

        }
    }

}
