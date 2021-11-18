package com.practo.instahms.pubsub.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.util
 * @date 04/10/21
 */
public class Utils {

    public static String generateRandomString(final int length, final boolean useLetters, final boolean useNumbers){
        return RandomStringUtils.random( length, useLetters, useNumbers );
    }

    public static String generateTokenPrefix() {
        int length = Constants.AUTH_TOKEN_PREFIX_LENGTH;
        boolean useLetters = true;
        boolean useNumbers = false;
        return generateRandomString(length, useLetters, useNumbers);
    }

    public static String generateToken() {
        int length = Constants.AUTH_TOKEN_LENGTH;
        boolean useLetters = true;
        boolean useNumbers = true;
        return generateRandomString(length, useLetters, useNumbers);
    }

    public static String mask(final String st, int len, char maskChar){
        if(len <= 0) return st;
        len = Math.min( len, st.length() );
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append( maskChar );
        }
        sb.append( st.substring( len ) );
        return sb.toString();
    }
}
