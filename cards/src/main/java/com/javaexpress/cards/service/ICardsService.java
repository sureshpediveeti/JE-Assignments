package com.javaexpress.cards.service;

import com.javaexpress.cards.dto.CardsDto;

public interface ICardsService {
	
	public void createCards(CardsDto cardsDto);
	
	public CardsDto fetchCards(String mobileNumber);
	
	public boolean updateCard(CardsDto cardsDto);
	
	public boolean deleteCard(String mobileNumber);

}
