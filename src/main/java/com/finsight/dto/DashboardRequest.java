package com.finsight.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DashboardRequest {

    @Min(1)
    @Max(12)
    @NotNull
    private Integer month;

    @Min(2000)
    @NotNull
    private Integer year;

    public DashboardRequest() {
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }
}
