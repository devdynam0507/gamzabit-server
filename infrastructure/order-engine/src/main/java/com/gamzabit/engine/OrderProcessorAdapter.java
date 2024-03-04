package com.gamzabit.engine;

import java.util.List;

public interface OrderProcessorAdapter<Out, Message> {

    Out buy(Message orderMessage);

    Out sell(Message orderMessage);

    void save(Message orderMessage);

    void saveAll(List<Message> orderMessages);
}
