package processos;

public class Processo {
    private int pid;
    private EstadoProcesso estado;
    private int tempo;
    private int usosES;
    private int usosCpu;
    private int quantum;
    private int duracao;

    private final double CHANCE_ES = 0.05;
    private final double CHANCE_DESBLOQUEAR = 0.30;

    private Processo(RegistroProcesso registro) {
        this.pid = registro.getPid();
        this.duracao = registro.getDuracao();
        this.tempo = registro.getTempo();
        this.quantum = registro.getQuantum();
        this.estado = registro.getEstado();
        this.usosCpu = registro.getUsosCpu();
        this.usosES = registro.getUsosES();
    }

    public static Processo newInstance(RegistroProcesso registro) {
        return new Processo(registro);
    }

    private RegistroProcesso getRegistro() {
        return RegistroProcesso.carregar(pid, estado, duracao, tempo, usosES, usosCpu, quantum);
    }

    private boolean desbloquear() {
        var bloqueado = estado == EstadoProcesso.BLOQUEADO;
        if (bloqueado && Math.random() < CHANCE_DESBLOQUEAR)
            estado = EstadoProcesso.PRONTO;

        return bloqueado;
    }

    public RegistroProcesso executar() {
        executarSemRetorno();
        return getRegistro();
    }

    private boolean permitirExecutar() {
        return !desbloquear() && estado != EstadoProcesso.FINALIZADO;
    }

    private void executarSemRetorno() {
        usosCpu++;
        EstadoProcesso estadoAnterior = estado;

        if (permitirExecutar()) {
            for (int i = 0; i < quantum && tempo < duracao; i++) {
                tempo++;
                if (Math.random() < CHANCE_ES) {
                    operacaoEntradaSaida();
                    break;
                }
            }
            if (tempo == duracao) estado = EstadoProcesso.FINALIZADO;
        }

        trocarContexto(estadoAnterior);
    }

    private void operacaoEntradaSaida() {
        estado = EstadoProcesso.BLOQUEADO;
        usosES++;
    }

    private void trocarContexto(EstadoProcesso estadoAnterior) {
        System.out.println(estadoAnterior + " >> " + estado);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Processo{" +
                "pid=" + pid +
                ", estado=" + estado +
                ", tempo=" + tempo +
                ", cp=" + (tempo + 1) +
                ", usosES=" + usosES +
                ", usosCpu=" + usosCpu +
                '}';
    }
}
