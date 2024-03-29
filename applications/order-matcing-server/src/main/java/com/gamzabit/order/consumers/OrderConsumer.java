package com.gamzabit.order.consumers;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamzabit.engine.DataSourceOrderEngine;
import com.gamzabit.infrastructure.kafka.KafkaMessageListener;
import com.gamzabit.order.consumers.dto.OrderProduceMessage;
import com.gamzabit.order.core.OrderEngineIncomingMessage;
import com.gamzabit.order.service.OrderValidator;
import com.gamzabit.order.service.exception.OrderValidationException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderConsumer implements KafkaMessageListener<String> {

    private final ObjectMapper objectMapper;
    private final OrderValidator orderValidator;
    private final DataSourceOrderEngine<OrderEngineIncomingMessage> orderEngine;

    @Override
    public void onMessage(String orderJson) {
        try {
            OrderProduceMessage orderProduceMessage = objectMapper.readValue(orderJson, OrderProduceMessage.class);
            OrderEngineIncomingMessage orderEngineIncomingMessage = orderProduceMessage.toOrderEngineMessage();

            orderValidator.validateOrder(orderProduceMessage.userId(), orderProduceMessage.toOrder());
            switch (orderProduceMessage.orderType()) {
                case Buy -> orderEngine.buy(orderEngineIncomingMessage);
                case Sell -> orderEngine.sell(orderEngineIncomingMessage);
                default -> throw new UnsupportedOperationException("지원되지 않는 매칭 타입입니다.");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (OrderValidationException e) {
            e.printStackTrace();
            // TODO: 주문 에러 처리
            // TODO: 유저에게 주문이 실패했다는 알림을 보낸다.
            // TODO: 주문을 상태를 변경한다.
        }
    }
}
