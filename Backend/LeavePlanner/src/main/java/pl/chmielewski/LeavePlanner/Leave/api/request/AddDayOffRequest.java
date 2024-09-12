package pl.chmielewski.LeavePlanner.Leave.api.request;

import java.time.LocalDate;

public record AddDayOffRequest(
        String holyName,
        LocalDate dayOff
) {
}
