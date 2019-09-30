package com.aq.mcorpdemo;

import java.util.logging.Logger;

public class WebUtil {

    protected static final char[] DEFAULT_REPLACE = {'.', '.', '.', '(', 't', 'r', 'u', 'n', 'c', 'a', 't', 'e', 'd', ')', '.', '.', '.'};
    private static final int DEFAULT_REPLACE_LENGTH = DEFAULT_REPLACE.length;
    private static final Logger LOG = Logger.getAnonymousLogger();

    public static String truncate(String source, int limit) {

        if (limit <= DEFAULT_REPLACE_LENGTH) {
            LOG.warning("Limit too small. entire string will be emiited to logs"); //this is a gap in reqreuiments.
            return source;
        }

        if (source == null || limit < 1) {
            return source;
        }

        int stringLength = source.length();

        if (stringLength <= limit || stringLength <= DEFAULT_REPLACE_LENGTH) {
            return source;
        }
        //do ot get to this point unless all validation is done
        int charsFromSource = limit - DEFAULT_REPLACE_LENGTH;
        int endCount = charsFromSource / 2;
        int beginCount = charsFromSource - endCount;
        char[] target = new char[limit];
        source.getChars(0, beginCount, target, 0);
        System.arraycopy(DEFAULT_REPLACE, 0, target, beginCount, DEFAULT_REPLACE_LENGTH);

        if (endCount > 0) {
            source.getChars(stringLength - endCount, stringLength, target, beginCount + DEFAULT_REPLACE_LENGTH);
        }
        return new String(target);
    }

}
