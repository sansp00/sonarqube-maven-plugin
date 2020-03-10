package com.github.sansp00.maven.sonarqube.model;

public enum Rating {
    A("A", 1), //
    B("B", 2), //
    C("C", 3), //
    D("D", 4), //
    E("E", 5), //
    UNDEFINED("", 0);

    private final String code;
    private final Integer value;

    private Rating(final String code, final Integer value) {
        this.code = code;
        this.value = value;
    }

    public static Rating valueOfCode(final String value) {
        Rating v = UNDEFINED;
        switch (value) {
            case "A":
                v = A;
                break;
            case "B":
                v = B;
                break;
            case "C":
                v = C;
                break;
            case "D":
                v = D;
                break;
            case "E":
                v = E;
                break;
            default:
                break;
        }
        return v;
    }

    public static Rating valueOfInteger(final Integer value) {
        Rating v = UNDEFINED;
        switch (value) {
            case 1:
                v = A;
                break;
            case 2:
                v = B;
                break;
            case 3:
                v = C;
                break;
            case 4:
                v = D;
                break;
            case 5:
                v = E;
                break;
            default:
                break;
        }
        return v;
    }

    public static Rating valueOfDouble(final Double value) {
        return valueOfInteger((int) Math.round(value));
    }

    public String getCode() {
        return this.code;
    }

    public Integer getNumericValue() {
        return this.value;
    }

}
