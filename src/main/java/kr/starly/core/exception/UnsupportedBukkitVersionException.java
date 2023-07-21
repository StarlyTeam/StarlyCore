package kr.starly.core.exception;

public class UnsupportedBukkitVersionException extends Exception {

    public UnsupportedBukkitVersionException(String version) {
        super(version + " 버전은 지원하지 않는 버전입니다.");
    }
}
