package kr.starly.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@AllArgsConstructor
@Getter
public enum Language {

    KOREAN("ko_kr", Locale.KOREA),
    CHINESE("zh_cn", Locale.CHINA),
    ENGLISH("en_us", Locale.US),
    SPANISH("es_ar", new Locale("es", "AR")),
    FRENCH("fr_fr", Locale.FRANCE),
    ITALIAN("it_it", Locale.ITALY),
    JAPANESE("ja_jp", Locale.JAPAN),
    POLISH("pl_pl", new Locale("pl", "PL")),
    THAI("th_th", new Locale("th", "TH")),
    TURKISH("tr_tr", new Locale("tr", "TR")),
    UKRAINIAN("uk_ua", new Locale("uk", "UA"));

    private final String name;
    private final Locale locale;

    public static Language fromLocale(Locale locale) {
        for (Language language : values()) {
            if (language.getLocale().equals(locale)) {
                return language;
            }
        }
        return ENGLISH;
    }
}