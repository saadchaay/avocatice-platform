package com.example.configservice;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Objects;

public class test {
    public static void main(String[] args) {
        String a = null;

        if (StringUtils.isEmpty(a)) {
            System.out.println("1 The string is empty or null");
        }

        if (StringUtils.hasText(a)) {
            System.out.println(" 2 The string is not empty and contains at least one non-whitespace character");
        }

        if (StringUtils.hasLength(a)) {
            System.out.println(" 3 The length of the string is greater than zero");
        }
        if (Objects.nonNull(a)) {
            System.out.println(" 10 The length of the string is greater than zero");
        }

        if ((Objects.isNull(a) || a.isEmpty())) {
            System.out.println(" 4 The length of the string is greater than zero");
        }

    }
}


