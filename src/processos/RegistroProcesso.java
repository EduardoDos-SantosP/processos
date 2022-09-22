package processos;

public class RegistroProcesso {
    private int pid;
    private EstadoProcesso estado;
    private int tempo;
    private int usosES;
    private int usosCpu;

    public int getPid() {
        return pid;
    }

    public EstadoProcesso getEstado() {
        return estado;
    }

    public int getTempo() {
        return tempo;
    }

    public int getUsosES() {
        return usosES;
    }

    public int getUsosCpu() {
        return usosCpu;
    }

    public int getQuantum() {
        return quantum;
    }

    private int quantum;

    private static int pidAutoIncremento = 0;

    public RegistroProcesso(int tempo, int quantum) {
        this.pid = pidAutoIncremento++;
        this.tempo = tempo;
        this.quantum = quantum;
        this.estado = EstadoProcesso.PRONTO;
        this.usosCpu = 0;
        this.usosES = 0;
    }
}
