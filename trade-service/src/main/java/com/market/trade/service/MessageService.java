package com.market.trade.service;

import com.market.trade.exception.InvalidTimestampException;
import com.market.trade.model.Message;
import com.market.trade.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CurrencyPairUsageService currencyPairUsageService;

    public void processAndSave (Message message) throws InvalidTimestampException {
        if (!message.isValid()) {
            messageRepository.save(message);
            throw new InvalidTimestampException();
        }
        currencyPairUsageService.processMessage(message);
        messageRepository.save(message);
    }
}
