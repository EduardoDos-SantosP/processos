package processos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SistemaOperacional {
    private ArrayList<RegistroProcesso> tabelaProcessos;
    private int quantum;

    public SistemaOperacional() {
        tabelaProcessos = new ArrayList<>();
    }

    public void iniciar() {
        criarProcessos();
        tabelaProcessos.forEach(System.out::println);
    }

    private void criarProcessos() {
        var duracoesProcessos = Arrays.asList(10000, 5000, 7000, 3000, 3000, 8000, 2000, 5000, 4000, 10000);
        duracoesProcessos.forEach(d -> tabelaProcessos.add(new RegistroProcesso(d, quantum)));
    }

    private void executarKernel() {
        tabelaProcessos.forEach(this::executarProcesso);
    }

    private void executarProcesso(RegistroProcesso registro) {
        atualizarTabela(new Processo(registro).executar());
    }

    private void atualizarTabela(RegistroProcesso registro) {
        tabelaProcessos.set(registro.getPid(), registro);
    }
}
