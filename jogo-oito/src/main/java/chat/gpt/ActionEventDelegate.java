package chat.gpt;

@FunctionalInterface
public interface ActionEventDelegate<T> {
    void doAction(T dataEvent);
}

class NotifyAction implements ActionEventDelegate<String> {
    @Override
    public void doAction(String dataEvent) {
        throw new UnsupportedOperationException("Unimplemented method 'doAction'");
    }

}

class MoveAction implements ActionEventDelegate<Integer> {
    private Integer dx;
    private Integer dy;
    private Table table;

    public MoveAction(Table table, Integer dx, Integer dy) {
        this.dx = dx;
        this.dy = dy;
        this.table = table;
    }

    // dataEvent is a keyCodeEvent
    @Override
    public void doAction(Integer dataEvent) {
        String out = dataEvent + " - (" + dx + "," + dy + ")";
        System.out.println(out);

        int linhaVazia = -1;
        int colunaVazia = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table.getCell(i, j).isEmpty()) {
                    linhaVazia = i;
                    colunaVazia = j;
                }
            }
        }
        int novaLinha = linhaVazia + dx;
        int novaColuna = colunaVazia + dy;
        if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
            // movimento inválido
            return;
        }

        table.swap(linhaVazia, colunaVazia, novaLinha, novaColuna);
    }
}