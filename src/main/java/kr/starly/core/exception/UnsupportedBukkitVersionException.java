package kr.starly.core.exception;

public class UnsupportedBukkitVersionException extends Exception {

    public UnsupportedBukkitVersionException(String version) {
        super(version + " is an unsupported version.");
    }
}
