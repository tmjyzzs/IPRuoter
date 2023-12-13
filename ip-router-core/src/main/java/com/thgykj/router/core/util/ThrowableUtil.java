package com.thgykj.router.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Description throwable
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public class ThrowableUtil {

    /**
     * parse error to string
     *
     * @param e
     * @return
     */
    public static String toString(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        String errorMsg = stringWriter.toString();
        return errorMsg;
    }
}
