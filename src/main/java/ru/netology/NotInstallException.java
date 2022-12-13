package ru.netology;

public class NotInstallException extends RuntimeException {

    public NotInstallException(String game) {
        super("Game " + game + " not install");
    }
}
