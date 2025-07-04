package Abstractions;

import java.time.LocalDate;

public interface Expirable {
    boolean isExpired(LocalDate date);
}
