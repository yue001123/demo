package com.woniu.mergedemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentScore {
    private String stuName;
    private String subject;
    private int score;
}
