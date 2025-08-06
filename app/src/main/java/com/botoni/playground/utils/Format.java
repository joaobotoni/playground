package com.botoni.playground.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Format {

    private static final DecimalFormat DECIMAL_FORMAT = createDecimalFormat();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));

    private static DecimalFormat createDecimalFormat() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        return new DecimalFormat("#,##0.00", symbols);
    }

    private Format() {}

    public static String decimal(BigDecimal value) {
        return DECIMAL_FORMAT.format(value);
    }

    public static String date(LocalDateTime date) {
        return DATE_FORMAT.format(date);
    }
}