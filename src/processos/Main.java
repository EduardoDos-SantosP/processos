package processos;

public class Main {
    private static void print(Object anything) {
        System.out.println(anything);
    }

    public static void main(String[] args) {
        var so = new SistemaOperacional();
        so.iniciar();
    }
}
