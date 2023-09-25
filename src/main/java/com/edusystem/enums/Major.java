package com.edusystem.enums;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

/**
 *  Student Majors
 */
public enum Major {
    Software,

    Finance,

    Computer,

    Accounting,

    Economics;

    private final static Random random = new Random();

    /**
     *  Get randomly major
     * @return major
     */
    public static Major randomMajor(){
        Major[] majors = values();
        return majors[random.nextInt(majors.length)];
    }

    /**
     *  Check if contains major
     * @param majorName Major name
     * @return true if contains major
     */
    public static Boolean containMajor(String majorName)
    {
        Optional<Major> existMajor = Arrays.stream(values()).filter(e -> e.name().contains(majorName)).findAny();
        return existMajor.isPresent();
    }
}