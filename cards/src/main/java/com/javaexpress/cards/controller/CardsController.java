package com.javaexpress.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.cards.dto.CardsContactDetailsDto;
import com.javaexpress.cards.dto.CardsDto;
import com.javaexpress.cards.service.ICardsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "api")
public class CardsController {

    @Autowired
    private ICardsService iCardsService;
    
    @Value("${build.version}")
    private String buildVersion;
    
    @Autowired
    private Environment sytemEnvVariables;
    
    @Autowired
    private CardsContactDetailsDto cardsContactDetail;

    @PostMapping("/create")
    public String createCard(@RequestBody CardsDto cardsDto) {
        log.info("CardsController :: createCard");

		iCardsService.createCards(cardsDto);

        return "Card Created Successfully";
    }

	@GetMapping("/fetch")
	public CardsDto fetchCardsDetails(@RequestParam String mobileNumber){
		log.info("CardsController :: fetchCardsDetails");
		return iCardsService.fetchCards(mobileNumber);
	}

    @PutMapping("/update")
    public Boolean updateCardDetails(@RequestBody CardsDto inputCardsDto) {
        log.info("CardsController :: updateCardDetails");

        return iCardsService.updateCard(inputCardsDto);
    }

    @DeleteMapping("/delete")
    public Boolean deleteCardsDetails(@RequestParam String mobileNumber){
        log.info("CardsController :: deleteCardsDetails");
        return iCardsService.deleteCard(mobileNumber);
    }
    
    @GetMapping("/build-info")
    public String getBuildInfo() {
    	return buildVersion;
    }
    
    @GetMapping("/env-maven-version")
    public String getMavenVersion() {
    	String mavenversion =  sytemEnvVariables.getProperty("M2_HOME");
		return mavenversion;
    }
    
    @GetMapping("/contact-info")
    public CardsContactDetailsDto getContactDetails() {
    	return cardsContactDetail;
    }


}
