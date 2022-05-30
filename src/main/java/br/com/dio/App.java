package br.com.dio;

import br.com.dio.interfaces.Terminal;
import br.com.dio.utilitaries.ProgramHandler;

/**
 * App class
 *
 * @author Wagner Maciel
 */
public class App {
    public static void main(String[] args) {
        ProgramHandler program = new ProgramHandler(Terminal.getTerminalInstance());
        program.programLoop();
    }
}
