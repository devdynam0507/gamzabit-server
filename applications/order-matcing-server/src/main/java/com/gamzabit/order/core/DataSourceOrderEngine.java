package com.gamzabit.order.core;

import com.gamzabit.order.consumers.dto.OrderProduceMessage;

public interface DataSourceOrderEngine {

    void buy(OrderProduceMessage orderMessage);

    void sell(OrderProduceMessage orderMessage);
}
