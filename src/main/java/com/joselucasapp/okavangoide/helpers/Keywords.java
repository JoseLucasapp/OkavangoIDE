package com.joselucasapp.okavangoide.helpers;

import java.util.Map;

public class Keywords {
    public static final Map<String, String> KEYWORDS = Map.ofEntries(
            //keyword Var
            Map.entry("fct", "keyword-var"),
            Map.entry("var", "keyword-var"),

            //keyword System
            Map.entry("if", "keyword-system"),
            Map.entry("else", "keyword-system"),
            Map.entry("while", "keyword-system"),

            //Keyword fct
            //array
            Map.entry("addToArrayStart", "keyword-fct"),
            Map.entry("addToArrayEnd", "keyword-fct"),
            Map.entry("allButFirst", "keyword-fct"),
            Map.entry("first", "keyword-fct"),
            Map.entry("indexOf", "keyword-fct"),
            Map.entry("last", "keyword-fct"),
            Map.entry("max", "keyword-fct"),
            Map.entry("min", "keyword-fct"),
            Map.entry("organize", "keyword-fct"),
            Map.entry("removeFromArray", "keyword-fct"),
            Map.entry("sizeOf", "keyword-fct"),
            Map.entry("sum", "keyword-fct"),

            //dicts
            Map.entry("addToDict", "keyword-fct"),
            Map.entry("deleteFromDict", "keyword-fct"),
            Map.entry("dictKeys", "keyword-fct"),
            Map.entry("dictValues", "keyword-fct"),
            Map.entry("getFromDict", "keyword-fct"),

            //extras
            Map.entry("date", "keyword-fct"),

            //http
            Map.entry("get", "keyword-fct"),
            Map.entry("html", "keyword-fct"),
            Map.entry("registerRoute", "keyword-fct"),
            Map.entry("server", "keyword-fct"),
            Map.entry("serveFile", "keyword-fct"),
            Map.entry("serveStatic", "keyword-fct"),

            //ioUtils
            Map.entry("input", "keyword-fct"),
            Map.entry("show", "keyword-fct"),

            //messages
            Map.entry("sendEmail", "keyword-fct"),
            Map.entry("sendWhatsapp", "keyword-fct"),

            //numbersUtils
            Map.entry("bhaskara", "keyword-fct"),
            Map.entry("randomFloat", "keyword-fct"),
            Map.entry("randomInteger", "keyword-fct"),

            //parsers
            Map.entry("json_parse", "keyword-fct"),
            Map.entry("toBool", "keyword-fct"),
            Map.entry("toFloat", "keyword-fct"),
            Map.entry("toInt", "keyword-fct"),
            Map.entry("toString", "keyword-fct"),

            //stringUtils
            Map.entry("capitalize", "keyword-fct"),
            Map.entry("removeWhiteSpaces", "keyword-fct"),
            Map.entry("replace", "keyword-fct"),
            Map.entry("toLowercase", "keyword-fct"),
            Map.entry("toUppercase", "keyword-fct")
    );
}
