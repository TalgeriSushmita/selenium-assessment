package exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggedException extends RuntimeException {

    protected static Logger logger = LogManager.getLogger();

    public LoggedException(String message) {
        super(message);
        logger.debug(message);
    }
}
