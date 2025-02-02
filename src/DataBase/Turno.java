package DataBase;

import java.io.Serializable;

public class Turno implements Serializable {
    private int Horario = 0;
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

    @Override
    public String toString() {
        return "Turno{" +
                "Horario=" + Horario +
                ", NombrePaciente='" + NombrePaciente + '\'' +
                ", Causa='" + Causa + '\'' +
                '}';
    }
}
