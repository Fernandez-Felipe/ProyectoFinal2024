import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

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

        Bienvenida = new JLabel("Bienvenido");
        Bienvenida.setBounds(75,50,200,55);
        Bienvenida.setFont(new Font("Great Vibes",1,48));
        Bienvenida.setForeground(Color.black);
        add(Bienvenida);

        Email = new JLabel("Email");
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == Entrar){
            new Main();
            this.setVisible(false);
        }

    }
    public static void main(String[] args) {
        new Login();
    }

}
