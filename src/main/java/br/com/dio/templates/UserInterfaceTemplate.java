package br.com.dio.templates;

/**
 * UserInterfaceTemplate interface
 *
 * @author Wagner Maciel
 */
public interface UserInterfaceTemplate {
    void showMainMenu();

    String getUserInfo(String instruction);

    void showMessage(String messageToShow);

    void actionToContinue();
}
