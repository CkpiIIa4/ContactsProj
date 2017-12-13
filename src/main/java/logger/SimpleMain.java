package logger;

import org.apache.log4j.Logger;

public class SimpleMain {

    private static final Logger logger = Logger.getLogger(SimpleMain.class);

    public static void main(String[] args) {
        for(int i = 0; i < 3; i++) {
            try {
                logger.info("result: " + divide(i));
            } catch (Exception e) {
                logger.warn("this error is because ", e);
            }
        }
    }

    public static int divide(int x) {
        if(x == 0) {
            logger.warn("x = 0; exception may occur");
        }
        return 2 / x;
    }
}