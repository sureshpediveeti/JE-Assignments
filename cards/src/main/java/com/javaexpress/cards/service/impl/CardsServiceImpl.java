package com.javaexpress.cards.service.impl;

import com.javaexpress.cards.exceptions.CardAlreadyExistInDbException;
import com.javaexpress.cards.exceptions.CreditCardNotFoundInDBException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.javaexpress.cards.dto.CardsDto;
import com.javaexpress.cards.entity.Cards;
import com.javaexpress.cards.repository.CardsRepository;
import com.javaexpress.cards.service.ICardsService;

import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor // if use this annotation @Autowired is not needed
public class CardsServiceImpl implements ICardsService {
	
	private CardsRepository cardsRepository;
	

	@Override
	public void createCards(CardsDto cardsDto) {
		Optional<Cards>  optionalCard= cardsRepository.findByMobileNumber(cardsDto.getMobileNumber());

		if(optionalCard.isPresent()){
			throw new CardAlreadyExistInDbException("Card already exist with given Mobile Number: "+cardsDto.getMobileNumber());
		}

		createNewCard(cardsDto);
	}

	private void createNewCard(CardsDto cardsDto) {
		Cards newCard = new Cards();

		long randomAccountNumber = 1000000000L + new Random().nextLong(7000000000L);

		newCard.setCardNumber(String.valueOf(randomAccountNumber));
		newCard.setMobileNumber(cardsDto.getMobileNumber());
		newCard.setCardType(cardsDto.getCardType());
		newCard.setTotalLimit(cardsDto.getTotalLimit());
		newCard.setAvailableLimit(cardsDto.getAvailableLimit());
		newCard.setAmountUsed(cardsDto.getAmountUsed());

		cardsRepository.save(newCard);
	}

	@Override
	public CardsDto fetchCards(String mobileNumber) {
		Cards dbCards = cardsRepository.findByMobileNumber(mobileNumber).
				orElseThrow(() -> new CardAlreadyExistInDbException("Credit card not found for the Mobile Number: "+mobileNumber));
		CardsDto cardsDto = new CardsDto();
		BeanUtils.copyProperties(dbCards, cardsDto);
		return cardsDto;
	}

	@Override
	public boolean updateCard(CardsDto cardsDto) {
		Cards dbCard = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).
				orElseThrow(() -> new CreditCardNotFoundInDBException("Credit card not found for the Card Number: "+cardsDto.getCardNumber()));
		BeanUtils.copyProperties(cardsDto, dbCard);

		Optional<Cards> optionalCards = Optional.of(cardsRepository.save(dbCard));

		if(optionalCards.isPresent()){
			return true;
		}

		return false;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		Cards dbCards = cardsRepository.findByMobileNumber(mobileNumber).
				orElseThrow(() -> new CreditCardNotFoundInDBException("Credit card not found for the Mobile Number: "+mobileNumber));
		cardsRepository.deleteById(dbCards.getCardId());
		return true;
	}

}
