package com.gamzabit.engine;

public interface DataSourceOrderEngine<Message> {

    void buy(Message orderMessage);

    void sell(Message orderMessage);
}
