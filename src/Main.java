import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

    int DIA,MES,ANIO;



    ImagenDeFondo fondo = new ImagenDeFondo(this);
    JMenuBar menubar;
    JMenu Options, AcercaDe, Atras;
    JButton Cancelar, Agregar, Dia;
    JTextArea Info;
    JList Turnos;
    JTextField Fecha;

    public Main(){
        setLayout(null);
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(fondo);

        Init();

        setVisible(true);
    }

    private void Init(){

        menubar = new JMenuBar();
        setJMenuBar(menubar);

        Options = new JMenu("Opciones");
        menubar.add(Options);

        AcercaDe = new JMenu("Acerca de");
        menubar.add(AcercaDe);

        Atras = new JMenu("Atras");
        menubar.add(Atras);

        Turnos = new JList();
        Turnos.setBounds(40,50,210,330);
        add(Turnos);

        Fecha = new JTextField();
        Fecha.setBounds(270,50,100,30);
        Fecha.setEditable(false);
        add(Fecha);

        Info = new JTextArea();
        Info.setBounds(270,100,190,230);
        add(Info);

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(270,350,90,30);
        add(Cancelar);

        Agregar = new JButton("Agregar");
        Agregar.setBounds(370,350,90,30);
        add(Agregar);

        Dia = new JButton("Fecha");
        Dia.setBounds(380,50,80,30);
        Dia.addActionListener(this);
        add(Dia);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Dia){
            new Calendario();
        }
    }

    //Fecha
    class Calendario extends JFrame implements ActionListener{

        JLabel D,M,A;
        JTextField Dias, Meses, Anios;
        JButton Aceptar;

        public Calendario(){
            setLayout(null);
            setSize(315,150);
            setLocationRelativeTo(null);
            setResizable(false);

            Init();
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        private void Init(){

            D = new JLabel("Dia");
            D.setBounds(30,0,100,30);
            add(D);

            Dias = new JTextField();
            Dias.setBounds(30,30,75,30);
            add(Dias);

            M = new JLabel("Mes");
            M.setBounds(115,0,100,30);
            add(M);

            Meses = new JTextField();
            Meses.setBounds(115,30,75,30);
            add(Meses);

            A = new JLabel("Año");
            A.setBounds(200,0,100,30);
            add(A);

            Anios = new JTextField();
            Anios.setBounds(200,30,75,30);
            add(Anios);

            Aceptar = new JButton("Aceptar");
            Aceptar.setBounds(102,70,100,30);
            Aceptar.addActionListener(this);
            add(Aceptar);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == Aceptar){
                try {
                    DIA = Integer.parseInt(Dias.getText());
                    MES = Integer.parseInt(Meses.getText());
                    ANIO = Integer.parseInt(Anios.getText());

                    if(ANIO<=0) throw new RuntimeException();

                    switch(MES){
                        case 1, 3, 5, 7, 8, 10, 12 -> {
                            if(DIA>31 || DIA <= 0) throw new RuntimeException();
                        }
                        case 2 -> {
                            if(ANIO%4 == 0 &&(ANIO%100 != 0 || ANIO%400 == 0)) {
                                if (DIA > 29 || DIA <= 0) throw new RuntimeException();
                            }else if (DIA > 28 || DIA <= 0) throw new RuntimeException();
                        }
                        case 4, 6, 9, 11 -> {
                            if(DIA>30 || DIA <= 0) throw new RuntimeException();
                        }
                        default -> throw new RuntimeException();
                    }

                    Fecha.setText(DIA+"/"+MES+"/"+ANIO);
                    this.dispose();

                }catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this,"Ingrese una fecha valida");
                    Dias.setText("");
                    Meses.setText("");
                    Anios.setText("");
                }
            }
        }
    }

    //Test
    private class Turno{
        private int Horario;
        private String NombrePaciente, Causa;

        public Turno(int Horario, String NombrePaciente, String Causa){
            this.Horario = Horario;
            this.NombrePaciente = NombrePaciente;
            this.Causa = Causa;
        }

        public int getHorario() {
            return Horario;
        }

        public String getNombrePaciente() {
            return NombrePaciente;
        }

        public String getCausa() {
            return Causa;
        }
    }
}
