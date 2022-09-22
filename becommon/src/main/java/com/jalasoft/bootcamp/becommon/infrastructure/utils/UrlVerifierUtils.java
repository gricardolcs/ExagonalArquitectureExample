package com.jalasoft.bootcamp.becommon.infrastructure.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;

public final class UrlVerifierUtils
{
    /*
     * The verification pattern was obtained from the following link
     * "https://www.javaer101.com/es/article/363491.html"
     */
    private static final Pattern DATA_URL_PATTERN =
        Pattern.compile(
            "^data:image/(.+?);base64,\\s*",
            Pattern.CASE_INSENSITIVE);

    private UrlVerifierUtils()
    {
    }

    public static boolean isBase64(String url)
    {
        if (url.startsWith("data:"))
        {
            final Matcher matcher = DATA_URL_PATTERN.matcher(url);
            if (matcher.find())
            {
                return Base64.isBase64(url.substring(matcher.end()));
            }
        }
        return false;
    }
}
