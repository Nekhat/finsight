package com.finsight.service;

import com.finsight.dto.DashboardResponse;

public interface DashboardService {
    DashboardResponse getDashboard(Integer userId, Integer month, Integer year);
}
