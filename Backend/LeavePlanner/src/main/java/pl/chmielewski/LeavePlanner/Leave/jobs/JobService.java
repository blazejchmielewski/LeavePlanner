package pl.chmielewski.LeavePlanner.Leave.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public interface JobService {

    // 1. (codziennie 22:00:00:000) | Dodaje dni urlopowe
    @Scheduled(cron = "0 0 22 * *?")
    String addDays();

    // 2. (01.01.XXXX 00:00:00:000) | Zmniejszam liczbe dni po skończeniu urlopu
    @Scheduled(cron = "0 0 0 1 1?")
    String reduceAvailableDaysAfterLeave();

    // 3. (codziennie 22:00:00:000) | Codziennie ustaw status urlopu jako "trwa" gdzie date = getdate()
    @Scheduled(cron = "0 0 22 * *?")
    String setLeaveStatusPending();
}
