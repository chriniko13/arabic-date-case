package com.chriniko.example.infrastructure;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.vavr.control.Try;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;

public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.toString());
        }
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }

        final String dateToProcess = in.nextString();

        return Try
                .ofSupplier(() -> LocalDate.parse(dateToProcess))
                .getOrElseGet(throwable -> arabicDateFallback.process(dateToProcess));

    }

    // ---- infrastructure section ----

    @FunctionalInterface
    interface DateProcessorFallback {
        LocalDate process(String input);
    }

    /*
        Note:

        [INPUT]
        String date = "١٩٥١-٠١-١٢";

        [PROCESS]
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date parsedDate = format.parse(date);
        LocalDate localDate = parsedDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate();

        System.out.println("localDate: " + localDate);

        [OUTPUT]
        localDate: 1951-01-11

     */
    private DateProcessorFallback arabicDateFallback = input -> {
        try {
            final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date parsedDate = format.parse(input);
            return parsedDate.toInstant().atZone(ZoneOffset.UTC).toLocalDate();
        } catch (Exception ignored) {
            return null;
        }
    };
}
