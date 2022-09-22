package processos;

public class Processo {
    private int pid;
    private EstadoProcesso estado;
    private int tempo;
    private int usosES;
    private int usosCpu;
    private int quantum;

    public Processo(RegistroProcesso registro) {
        this.tempo = registro.getTempo();
        this.quantum = registro.getQuantum();
        this.estado = registro.getEstado();
        this.usosCpu = registro.getUsosCpu();
        this.usosES = registro.getUsosES();
    }

    public RegistroProcesso executar() {
        return null;
    }

    @Override
    public String toString() {
        return "Processo{" +
                "pid=" + pid +
                ", estado=" + estado +
                ", tempo=" + tempo +
                ", usosES=" + usosES +
                ", usosCpu=" + usosCpu +
                ", quantum=" + quantum +
                '}';
    }
}
