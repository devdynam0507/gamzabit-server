package com.gamzabit.order.core;

import java.util.List;

import com.gamzabit.order.consumers.dto.OrderProduceMessage;

public interface OrderProcessorAdapter<Out, Value> {

    Out buy(Value orderMessage);

    Out sell(Value orderMessage);

    void saveBuy(Value orderMessage);

    void saveBuys(List<Value> orderMessages);
}
