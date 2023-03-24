package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName = "";
        String argumentString;
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        MethodSignature ms = new MethodSignature(methodName, arguments);

        if (signatureString.contains("private") || signatureString.contains("public")) {
            String[] parts = signatureString.split(" ", 3);
            accessModifier = parts[0];
            returnType = parts[1];
            methodName = parts[2].substring(0, parts[2].indexOf("("));
            ms.setAccessModifier(accessModifier);

        } else {
            String[] parts = signatureString.split(" ", 2);
            returnType = parts[0];
            methodName = parts[1].substring(0, parts[1].indexOf("("));
        }

        ms.setMethodName(methodName);
        ms.setReturnType(returnType);

        argumentString = signatureString.substring(signatureString.indexOf("("), signatureString.indexOf(")"));
        String[] argsArray = argumentString.split(", ");

        for (String arg : argsArray) {
            String[] argParts = arg.split(" ");
            arguments.add(new MethodSignature.Argument(argParts[0], argParts[1]));
        }

        return new MethodSignature(methodName, arguments);

    }
}
