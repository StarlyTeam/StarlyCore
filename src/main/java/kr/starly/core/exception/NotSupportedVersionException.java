package kr.starly.core.exception;

public class NotSupportedVersionException extends Exception {

    public NotSupportedVersionException(String version) {
        super(version + " 버전은 지원하지 않는 버전입니다.");
    }
}
