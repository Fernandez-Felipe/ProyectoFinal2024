import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends JFrame implements ActionListener {

    int DIA,MES,ANIO;

    HashMap<Integer,Turno[][][]> TurnosAño = new HashMap<>();
    DefaultListModel<String> TurnosDeUnDia = new DefaultListModel<>();

    ImagenDeFondo fondo = new ImagenDeFondo(this);
    JMenuBar menubar;
    JMenu Options, AcercaDe, Atras;
    JButton Cancelar, Agregar, Dia;
    JTextArea Info;
    JList Turnos;
    JTextField Fecha;

    public Main(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        Turnos = new JList(TurnosDeUnDia);
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
        Agregar.addActionListener(this);
        add(Agregar);

        Dia = new JButton("Fecha");
        Dia.setBounds(380,50,80,30);
        Dia.addActionListener(this);
        add(Dia);
    }

    //Agregar Turnos
    class AgregarTurno extends JFrame implements ActionListener{

        AgregarTurno AT;
        String NombreT, ApellidoT, CausaT;
        int DiaT, MesT, AnioT, HoraT;
        JLabel N,A,D,M,An, H;
        JTextField Nombre, Apellido, Dia, Mes, Anio, NumeroTurno;
        JTextArea Causa;
        JButton Aceptar, Cancelar;

        public AgregarTurno(){
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(null);
            setSize(400,330);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);

            getContentPane().setBackground(new Color(209, 242, 235));

            Iniciar();

        }

        private void Iniciar(){
            //Nombre
            N = new JLabel("Nombre");
            N.setBounds(10,5,100,30);
            add(N);
            Nombre = new JTextField();
            Nombre.setBounds(10,30,100,25);
            add(Nombre);

            //Apellido
            A = new JLabel("Apellido");
            A.setBounds(10,50,100,30);
            add(A);
            Apellido = new JTextField();
            Apellido.setBounds(10,75,100,25);
            add(Apellido);

            //Dia
            D = new JLabel("Dia");
            D.setBounds(10,95,100,30);
            add(D);
            Dia = new JTextField();
            Dia.setBounds(10,120,100,25);
            add(Dia);

            //Mes
            M = new JLabel("Mes");
            M.setBounds(10,140,100,30);
            add(M);
            Mes = new JTextField();
            Mes.setBounds(10,165,100,25);
            add(Mes);

            //Año
            An = new JLabel("Año");
            An.setBounds(10,185,100,30);
            add(An);
            Anio = new JTextField();
            Anio.setBounds(10,210,100,25);
            add(Anio);

            //Hora
            H = new JLabel("TURNO");
            H.setBounds(10,230,100,30);
            add(H);
            NumeroTurno = new JTextField();
            NumeroTurno.setBounds(10,255,100,25);
            add(NumeroTurno);

            //causa
            Causa = new JTextArea();
            Causa.setBounds(130,30,230,205);
            add(Causa);

            Aceptar = new JButton("Aceptar");
            Aceptar.setBounds(130,245,100,30);
            Aceptar.addActionListener(this);
            add(Aceptar);

            Cancelar = new JButton("Cancelar");
            Cancelar.setBounds(260,245,100,30);
            add(Cancelar);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Aceptar) {

                NombreT = Nombre.getText();
                ApellidoT = Apellido.getText();
                CausaT = Causa.getText();

                try {

                    DiaT = Integer.parseInt(Dia.getText());
                    MesT = Integer.parseInt(Mes.getText());
                    AnioT = Integer.parseInt(Anio.getText());
                    HoraT = Integer.parseInt(NumeroTurno.getText());

                    if (TurnosAño.containsKey(AnioT)) {

                        if(TurnosAño.get(AnioT)[HoraT][DiaT][MesT] !=null) {
                            TurnosAño.get(AnioT)[HoraT][DiaT][MesT] = new Turno(HoraT + 7, ApellidoT + " " + NombreT, CausaT);
                        }else throw new RuntimeException("el turno Nº "+HoraT+" ya esta ocupado");
                    } else {
                        TurnosAño.put(AnioT, new Turno[8][31][12]);
                        TurnosAño.get(AnioT)[HoraT][DiaT][MesT] = new Turno(HoraT, ApellidoT + " " + NombreT, CausaT);
                    }

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, ex);
                }

                dispose();

            }
        }
    }

    //Eventos del panel principal
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Dia){
            new Calendario();
            TurnosDeUnDia.removeAllElements();
        }
        if(e.getSource() == Agregar) {
            new AgregarTurno();
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
            setDefaultCloseOperation(2);
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


                    for(int i = 0; i < 8; i++){
                        if(TurnosAño.get(ANIO)[i][DIA][MES]!= null) {
                            TurnosDeUnDia.addElement((i+1)+"º Turno: "+TurnosAño.get(ANIO)[i][DIA][MES].NombrePaciente);
                        }else{
                            TurnosDeUnDia.addElement((i+1)+"º Turno: VACIO");
                        }
                    }

                    dispose();

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
