package com.finsight.service.impl;

import com.finsight.dto.CategoryExpense;
import com.finsight.dto.DashboardResponse;
import com.finsight.dto.RecentTransaction;
import com.finsight.entity.Transaction;
import com.finsight.repository.TransactionRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.DashboardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public DashboardServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DashboardResponse getDashboard(Integer userId, Integer month, Integer year) {
        validateUser(userId);
        validateYear(year);

        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        BigDecimal totalIncome = transactionRepository.getTotalIncome(userId, startDate, endDate);
        BigDecimal totalExpense = transactionRepository.getTotalExpense(userId, startDate, endDate);
        totalIncome = (totalIncome != null) ? totalIncome : BigDecimal.ZERO;
        totalExpense = (totalExpense != null) ? totalExpense : BigDecimal.ZERO;

        List<CategoryExpense> expenseByCategory = transactionRepository.getExpenseByCategory(userId, startDate, endDate);

        List<Transaction> listOfRecentTransactions = transactionRepository.findTop5ByUserIdAndDateBetweenOrderByDateDesc(userId, startDate, endDate);

        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        List<RecentTransaction> listOfRecentTransactionsDTO = mapToRecentTransactionDTO(listOfRecentTransactions);

        return new DashboardResponse(totalIncome,
                totalExpense,
                netBalance,
                expenseByCategory,
                listOfRecentTransactionsDTO);
    }

    //Helper Methods

    private void validateUser(Integer userId){
        if (!userRepository.existsById(userId)){
            throw new IllegalArgumentException("User not found");
        }
    }

    private void validateYear(Integer year){
        if(year> LocalDate.now().getYear()){
            throw new RuntimeException("Year cannot be in the future");
        }
    }

    private List<RecentTransaction> mapToRecentTransactionDTO(List<Transaction> listOfRecentTransactions){

        List<RecentTransaction> listOfRecentTransactionsDTO = new ArrayList<>();
        for (Transaction transaction: listOfRecentTransactions)
        {
            listOfRecentTransactionsDTO.add(new RecentTransaction(transaction.getId(),
                    transaction.getCategory().getName(),
                    transaction.getType().name(),
                    transaction.getAmount(),
                    transaction.getDate()));
        }
        return listOfRecentTransactionsDTO;

    }
}
