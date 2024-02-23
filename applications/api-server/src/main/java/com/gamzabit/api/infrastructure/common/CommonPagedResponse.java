package com.gamzabit.api.infrastructure.common;

import java.util.List;

import lombok.Getter;

@Getter
public class CommonPagedResponse<T> extends AbstractCommonResponse<List<T>> {

    private final int page;
    private final int totalPage;
    private final boolean hasNext;

    CommonPagedResponse(String message, List<T> result, int totalPage, int currentPage, boolean hasNext) {
        super(message, result);
        this.page = currentPage;
        this.totalPage = totalPage;
        this.hasNext = hasNext;
    }

    public static <S> CommonPagedResponse<S> of(String message, int totalPage, int currentPage, List<S> results) {
        boolean hasNext = currentPage < totalPage;
        return new CommonPagedResponse<>(message, results, totalPage, currentPage, hasNext);
    }

    public static <T, S> CommonPagedResponse<S> copyAndSwapResponseBody(
        CommonPagedResponse<T> response,
        List<S> swapBodyData
    ) {
        return of(response.getMessage(), response.getTotalPage(), response.getPage(), swapBodyData);
    }
}
