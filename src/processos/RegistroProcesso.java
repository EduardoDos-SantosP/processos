package processos;

public class RegistroProcesso {
    private static int pidAutoIncremento = 0;
    private int pid;
    private EstadoProcesso estado;
    private int duracao;
    private int tempo;
    private int usosES;
    private int usosCpu;
    private int quantum;

    private RegistroProcesso(int pid, EstadoProcesso estado, int duracao, int tempo, int usosES, int usosCpu, int quantum) {
        this.pid = pid;
        this.estado = estado;
        this.duracao = duracao;
        this.tempo = tempo;
        this.usosES = usosES;
        this.usosCpu = usosCpu;
        this.quantum = quantum;
    }

    public static RegistroProcesso criar(int duracao, int quantum) {
        return new RegistroProcesso(pidAutoIncremento++, EstadoProcesso.PRONTO, duracao, 0, 0, 0, quantum);
    }

    public static RegistroProcesso carregar(int pid, EstadoProcesso estado, int duracao, int tempo, int usosES, int usosCpu, int quantum) {
        return new RegistroProcesso(pid, estado, duracao, tempo, usosES, usosCpu, quantum);
    }

    public int getPid() {
        return pid;
    }

    public EstadoProcesso getEstado() {
        return estado;
    }

    public int getDuracao() {
        return duracao;
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

    @Override
    public boolean equals(Object registro) {
        if (this == registro) return true;
        if (registro == null || getClass() != registro.getClass()) return false;

        RegistroProcesso that = (RegistroProcesso) registro;

        return pid == that.pid;
    }

    @Override
    public int hashCode() {
        return pid;
    }
}
