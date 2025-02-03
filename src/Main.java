
import DataBase.Turno;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import DataBase.Usser;
import DataBase.LoadData;

public class Main extends JFrame implements ActionListener {

    int DIA,MES,ANIO, I;
    private String UsuarioActual;

    Calendario almanaque = new Calendario();

    HashMap<Integer,Turno[][][]> TurnosAño;
    DefaultListModel<String> TurnosDeUnDia = new DefaultListModel<>();

    ImagenDeFondo fondo = new ImagenDeFondo(this);
    JMenuBar menubar;
    JMenu Options;
    JMenuItem Atras;
    JButton Cancelar, Agregar, Dia;
    JList Turnos;
    JLabel Fecha;
    JTextArea Info;

    public Main(HashMap<Integer,Turno[][][]> TurnosAño, String UsuarioActual){

        this.TurnosAño = TurnosAño;
        this.UsuarioActual = UsuarioActual;

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

        Atras = new JMenuItem("Salir");
        Atras.addActionListener(this);
        Options.add(Atras);

        Turnos = new JList(TurnosDeUnDia);
        Turnos.setBounds(40,50,210,330);
        Turnos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                I = Turnos.getSelectedIndex();

                AgregarInfoDeTurnosAlPanel();

            }
        });
        add(Turnos);

        Fecha = new JLabel();
        Fecha.setBounds(270,50,100,30);
        Fecha.setBackground(Color.WHITE);
        Fecha.setOpaque(true);
        add(Fecha);

        Info = new JTextArea(10,30);
        Info.setBounds(270,100,190,230);
        Info.setBackground(Color.WHITE);
        Info.setOpaque(true);
        add(Info);

        Cancelar = new JButton("Cancelar");
        Cancelar.setBounds(270,350,90,30);
        Cancelar.addActionListener(this);
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

    //Eventos del panel principal
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Dia){
            almanaque.setVisible(true);
            TurnosDeUnDia.removeAllElements();
        }
        if(e.getSource() == Agregar) {
            new AgregarTurno();
        }
        if(e.getSource() == Cancelar){
            try {
                TurnosAño.get(ANIO)[I][DIA][MES] = null;
                AgregarTunosALaLista();
                ActualizarDatos();
            }catch (Exception ex){
                System.out.println(ex);
            }
        }

        if(e.getSource() == Atras){
            new Login();
            dispose();
        }
    }

    /*Se selecciona la fecha, se agregan los turnos en el JList de los turnos diarios*/
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
            setVisible(false);
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

                    VerificarFechas(DIA,MES,ANIO);

                    Fecha.setText(DIA+"/"+MES+"/"+ANIO);

                    AgregarTunosALaLista();

                    dispose();

                }catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this,ex);
                    Dias.setText("");
                    Meses.setText("");
                    Anios.setText("");
                }
            }
        }
    }

    /*Se agregan los turnos, se almacenan los datos de los turnos*/
    class AgregarTurno extends JFrame implements ActionListener{

        String NombreT, ApellidoT, CausaT;
        Integer DiaT, MesT, AnioT, HoraT;
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
            Cancelar.addActionListener(this);
            add(Cancelar);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == Aceptar) {

                try {

                    NombreT = Nombre.getText();
                    ApellidoT = Apellido.getText();
                    CausaT = Causa.getText();

                    if(NombreT.equals("") || ApellidoT.equals("") || CausaT.equals("")){
                        throw  new RuntimeException("Complete todos las casillas");
                    }

                    DiaT = Integer.parseInt(Dia.getText());
                    MesT = Integer.parseInt(Mes.getText());
                    AnioT = Integer.parseInt(Anio.getText());
                    HoraT = Integer.parseInt(NumeroTurno.getText())-1;

                    VerificarFechas(DiaT,MesT,AnioT);

                    if(!(HoraT>=0 && HoraT<=8)) throw new RuntimeException("Ingrese un turno valido");

                    if (TurnosAño.containsKey(AnioT)) {

                        if(TurnosAño.get(AnioT)[HoraT][DiaT][MesT] == null) {
                            TurnosAño.get(AnioT)[HoraT][DiaT][MesT] = new Turno(HoraT+7,ApellidoT
                                    + " " + NombreT, CausaT);

                            if(ANIO == AnioT && MES == MesT && DIA == DiaT){
                                AgregarTunosALaLista();
                            }

                        }else throw new RuntimeException("el turno Nº "+HoraT+" ya esta ocupado");
                    } else {
                        TurnosAño.put(AnioT, new Turno[8][31][12]);
                        TurnosAño.get(AnioT)[HoraT][DiaT][MesT] = new Turno(HoraT,ApellidoT+" "+NombreT,CausaT);
                        if(ANIO == AnioT && MES == MesT && DIA == DiaT){
                            AgregarTunosALaLista();
                        }
                    }

                    ActualizarDatos();

                    dispose();

                } catch (RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

    private void AgregarInfoDeTurnosAlPanel(){
        try{
            Turno T = TurnosAño.get(ANIO)[I][DIA][MES];
            Info.setText("");
            Info.setText(T.getNombrePaciente() +"\nHora del turno: "+T.getHorario()+" Horas"+"\nCausa del turnos: "+T.getCausa());
        }catch (Exception ex){
            Info.setText("");
            Info.setText("Turno disponible");
        }
    }

    private void AgregarTunosALaLista(){
        TurnosDeUnDia.removeAllElements();


        for(int i = 0; i < 8; i++){
            if(!(TurnosAño.containsKey(ANIO))){
                for(int e = 0; e < 8; e++){
                    TurnosDeUnDia.addElement((e+1)+"º Turno: VACIO");
                }
                break;
            }
            if(TurnosAño.get(ANIO)[i][DIA][MES]!= null) {
                TurnosDeUnDia.addElement((i+1)+"º Turno: "+ TurnosAño.get(ANIO)[i][DIA][MES].getNombrePaciente());
            }else{
                TurnosDeUnDia.addElement((i+1)+"º Turno: VACIO");
            }
        }
    }

    private void VerificarFechas(int dia, int mes, int anio){
        if(anio<=0) throw new RuntimeException();

        switch(mes){
            case 1, 3, 5, 7, 8, 10, 12 -> {
                if(dia>31 || dia <= 0) throw new RuntimeException("Ingrese uan fecha valida");
            }
            case 2 -> {
                if(anio%4 == 0 &&(anio%100 != 0 || dia%400 == 0)) {
                    if (dia > 29 || dia <= 0) throw new RuntimeException("Ingrese uan fecha valida");
                }else if (dia > 28 || dia <= 0) throw new RuntimeException("Ingrese uan fecha valida");
            }
            case 4, 6, 9, 11 -> {
                if(dia>30 || dia <= 0) throw new RuntimeException("Ingrese uan fecha valida");
            }
            default -> throw new RuntimeException("Ingrese uan fecha valida");
        }
    }

    private void ActualizarDatos() throws IOException {
        LoadData ld = new LoadData();
        ArrayList<Usser> UsuariosActuales = ld.CargarUsuarios();

        UsuariosActuales.stream().filter(User -> User.getNombre().equals(UsuarioActual)).forEach(user ->{

            user.setTurnosAño(TurnosAño);

        });

        ObjectOutputStream Reesccribir = new ObjectOutputStream(new FileOutputStream("DataBase/Usuarios.bin"));

        UsuariosActuales.stream().forEach(U ->{

            try {
                Reesccribir.writeObject(U);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

}
