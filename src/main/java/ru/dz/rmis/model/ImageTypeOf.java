package ru.dz.rmis.model;

/**
 *
 * @author vassaeve
 */
public enum ImageTypeOf {

    I,
    II,
    III,
    IV,
    VI;

    public static ImageTypeOf valueOf1(String name) {
        switch (name+"") {
            case "I":
                return I;
            case "II":
                return II;
            case "III":
                return III;
        }
        return VI;
    }
}
