package eCommerce.ui;

import eCommerce.interfaces.IConsoleUIStrategy;

public class ConsoleUIContext {
    private IConsoleUIStrategy _consoleStrategy;

    public void setConsoleStrategy(IConsoleUIStrategy strategy) {
        _consoleStrategy = strategy;
    }

    public void runApp() {
        _consoleStrategy.printUI();
    }
}
