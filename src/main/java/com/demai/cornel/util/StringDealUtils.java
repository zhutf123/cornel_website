package com.demai.cornel.util;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * @author tfzhu
 */
public class StringDealUtils {
    public static final Joiner JOINER_AXIS = Joiner.on("|").skipNulls();
    public static final Joiner JOINER_COMMA = Joiner.on(",").skipNulls();
    public static final Joiner JOINER_CROSS = Joiner.on("-").skipNulls();
    public static final Joiner JOINER_SPACE = Joiner.on(" ").skipNulls();
    public static final Joiner JOINER_EMPTY = Joiner.on("").skipNulls();
    public static final Joiner JOINER_COLON = Joiner.on(":").skipNulls();
    public static final Joiner JOINER_UNDERLINE = Joiner.on("_").skipNulls();
    public static final Joiner JOINER_AT = Joiner.on("@").skipNulls();
    public static final Joiner JOINER_JIN = Joiner.on("#").skipNulls();
    public static final Joiner JOINER_SLASH = Joiner.on("/").skipNulls();
    public static final Joiner JOINER_WITH_AND = Joiner.on(" AND ");
    public static final Joiner JOINER_POINT = Joiner.on(".");

    public static final Splitter SPLITTER_AXIS = Splitter.on("|").omitEmptyStrings().trimResults();
    public static final Splitter SPLITTER_COMMA = Splitter.on(",").omitEmptyStrings().trimResults();
    public static final Splitter SPLITTER_POINT = Splitter.on(".").omitEmptyStrings().trimResults();
    public static final Splitter SPLITTER_CROSS = Splitter.on("-");
    public static final Splitter SPLITTER_COLON = Splitter.on(":");
    public static final Splitter SPLITTER_WELL = Splitter.on("#");
    public static final Splitter SPLITTER_SPACE = Splitter.on(" ").omitEmptyStrings().trimResults();
    public static final Splitter SPLITTER_UNDERLINE = Splitter.on("_").omitEmptyStrings().trimResults();
    public static final Splitter SPLITTER_SLASH = Splitter.on("/").omitEmptyStrings().trimResults();
    public static final Splitter SPLITTER_ARROW = Splitter.on("=>").omitEmptyStrings().trimResults();
}
