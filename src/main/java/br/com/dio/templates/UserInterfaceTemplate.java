package br.com.dio.templates;

public interface UserInterfaceTemplate {
    void showMainMenu();

    String getUserInfo(String instruction);

    void showMessage(String messageToShow);

    void actionToContinue();
}
