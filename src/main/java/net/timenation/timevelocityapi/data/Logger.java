package net.timenation.timevelocityapi.data;

import net.timenation.timevelocityapi.data.logger.ConsoleColors;
import net.timenation.timevelocityapi.data.logger.LoggerType;

public class Logger {

    public void log(String message, LoggerType loggerType) {
        switch (loggerType) {
            case INFO -> System.out.println(ConsoleColors.BLACK_BRIGHT + "[ " + ConsoleColors.RED_BRIGHT + "TimeSpigotAPI " + ConsoleColors.BLACK_BRIGHT + "] " + ConsoleColors.GREEN_BRIGHT + message);
            case WARNING -> System.out.println(ConsoleColors.BLACK_BRIGHT + "[ " + ConsoleColors.RED_BRIGHT + "TimeSpigotAPI " + ConsoleColors.BLACK_BRIGHT + "] " + ConsoleColors.YELLOW_BRIGHT + message);
            case ERROR -> System.out.println(ConsoleColors.BLACK_BRIGHT + "[ " + ConsoleColors.RED_BRIGHT + "TimeSpigotAPI " + ConsoleColors.BLACK_BRIGHT + "] " + ConsoleColors.RED_BRIGHT + message);
        }
    }
}

