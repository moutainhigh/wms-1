package com.teeny.wms.util;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see Validator
 * @since 2017/10/23
 */
public class Validator {

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0;
    }
}
