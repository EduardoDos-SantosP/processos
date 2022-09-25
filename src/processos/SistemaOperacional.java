package processos;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaOperacional {
    private ArrayList<RegistroProcesso> tabelaProcessos;
    private final int QUANTUM = 1000;

    public SistemaOperacional() {
        tabelaProcessos = new ArrayList<>();
    }

    public void iniciar() {
        criarProcessos();
        executarKernel();
    }

    private void criarProcessos() {
        var duracoesProcessos = Arrays.asList(10000, 5000, 7000/*, 3000, 3000, 8000, 2000, 5000, 4000, 10000*/);
        duracoesProcessos.forEach(d -> tabelaProcessos.add(RegistroProcesso.criar(d / 10, QUANTUM / 50)));
    }

    private void executarKernel() {
        while (!tabelaProcessos.isEmpty()) {
            tabelaProcessos.forEach(this::executarProcesso);
            tabelaProcessos = tabelaProcessos.stream()
                    .filter(registro -> registro.getEstado() != EstadoProcesso.FINALIZADO)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    private void executarProcesso(RegistroProcesso registro) {
        atualizarTabela(Processo.newInstance(registro).executar());
    }

    private void atualizarTabela(RegistroProcesso registro) {
        tabelaProcessos.set(localizarRegistro(registro), registro);
    }

    private int localizarRegistro(RegistroProcesso registro) {
        for (int i = 0; i < tabelaProcessos.size(); i++)
            if (tabelaProcessos.get(i).equals(registro))
                return i;
        return -1;
    }
}
