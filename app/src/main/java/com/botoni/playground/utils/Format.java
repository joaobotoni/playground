package com.botoni.playground.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Format {

    private static final DecimalFormatSymbols DECIMAL_SYMBOLS;
    private static final DecimalFormat DECIMAL_FORMAT;
    private static final DateTimeFormatter DATE_FORMAT;

    static {
        DECIMAL_SYMBOLS = new DecimalFormatSymbols();
        DECIMAL_SYMBOLS.setGroupingSeparator('.');
        DECIMAL_SYMBOLS.setDecimalSeparator(',');
        DECIMAL_FORMAT = new DecimalFormat("#,##0.00", DECIMAL_SYMBOLS);
    }

    static {
        DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(new Locale("pt", "BR"));
    }

    public static String decimal(BigDecimal value) {
        return DECIMAL_FORMAT.format(value);
    }

    public static String date(LocalDateTime date) {
        return DATE_FORMAT.format(date);
    }
}
