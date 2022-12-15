package com.oipie.core.shared.domain;

import java.util.List;

public final class PageResult<T> {

    private List<T> result;

    private int page;

    private int limit;

    private long totalResults;

    private PageResult(List<T> result, int page, int limit, long totalResults) {
        this.result = result;
        this.page = page;
        this.limit = limit;
        this.totalResults = totalResults;
    }

    public static <E> PageResult<E> create(List<E> result, int skip, int limit, long totalResult) {
        return new PageResult(result, skip, limit, totalResult);
    }

    public List<T> getResults() {
        return this.result;
    }

    public long getTotalResults() {
        return this.totalResults;
    }
}
