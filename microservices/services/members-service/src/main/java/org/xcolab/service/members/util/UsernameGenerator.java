package org.xcolab.service.members.util;

public class UsernameGenerator {

    private final String[] inputValues;
    private final boolean forceLowercase;
    private final int sizeLimit;
    private int enumerationCount;

    public UsernameGenerator(String[] inputValues, boolean forceLowercase, int sizeLimit) {
        this.inputValues = inputValues;
        this.forceLowercase = forceLowercase;
        this.sizeLimit = sizeLimit;
    }

    public String getFull() {
        return getAbbreviated(inputValues.length);
    }

    public String getFullest() {
        return getFullest(0);
    }

    public String getFullest(int extraCharacters) {
        for (int fullComponents = inputValues.length; fullComponents >= 0; --fullComponents) {
            final String name = getAbbreviated(fullComponents);
            if (name.length() <= sizeLimit - extraCharacters) {
                return name;
            }
        }
        throw new IllegalStateException("Size limit is too small to allow fully abbreviated name");
    }

    public String getAbbreviated(int fullComponents) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, inputValuesSize = inputValues.length; i < inputValuesSize; i++) {
            String value = forceLowercase ? inputValues[i].toLowerCase() : inputValues[i];
            value = value.replaceAll("\\s+", "");
            if (i < fullComponents) {
                sb.append(value);
            } else {
                sb.append(value.substring(0, 1));
            }
        }
        return sb.toString();
    }

    public String getNext() {
        final String suffix = enumerationCount > 0 ? Integer.toString(enumerationCount) : "";
        final String enumeratedName = getFullest(suffix.length()) + suffix;
        enumerationCount++;
        return enumeratedName;
    }
}
