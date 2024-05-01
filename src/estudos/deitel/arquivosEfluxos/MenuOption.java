package estudos.deitel.arquivosEfluxos;

// tipo enum para as opções do programa de consulta de crédito.

public enum MenuOption
{
    // declara o conteúdo do tipo enum
    ZERO_BALANCE(1),
    CREDIT_BALANCE(2),
    DEBIT_BALANCE(3),
    END(4);

    private final int value; // opção atual de menu

    // construtor
    private MenuOption(int value)
    {
        this.value = value;
    }
} // fim do enum de MenuOption