package kr.starly.core.language;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Language {

    CHINESE("zh_cn"),
    ENGLISH("en_us"),
    SPANISH("es_ar"),
    FRENCH("fr_fr"),
    ITALIAN("it_it"),
    KOREAN("ko_kr"),
    JAPANESE("ja_jp"),
    POLISH("pl_pl"),
    THAI("th_th"),
    TURKISH("tr_tr"),
    UKRAINIAN("uk_ua");

    private final String name;
}
