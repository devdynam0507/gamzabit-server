package com.gamzabit.domain.order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderProcessor {

    @Transactional
    public Long buy() {
        return -1L;
    }

    @Transactional
    public Long sell() {
        return -1L;
    }
}
