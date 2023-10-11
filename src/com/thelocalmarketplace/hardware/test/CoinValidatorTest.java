package com.thelocalmarketplace.hardware.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tdc.Sink;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinStorageUnit;
import com.tdc.coin.CoinValidator;
import com.thelocalmarketplace.hardware.CoinTray;

import ca.ucalgary.seng300.simulation.SimulationException;

@SuppressWarnings("javadoc")
public class CoinValidatorTest {
	private CoinValidator coinValidator;
	
	private Currency currency;
	private List<BigDecimal> coinDenominations;
	
	private CoinTray rejectionSink;
	private Map<BigDecimal, Sink<Coin>> standardSinks;
	private CoinStorageUnit overflowSink;
	
	
	@Before
	public void generalSetup() {
		this.currency = Currency.getInstance("USD");
		this.coinDenominations = new ArrayList<BigDecimal>();
		this.coinDenominations.add(new BigDecimal(2));
		coinValidator = new CoinValidator(this.currency, this.coinDenominations);
		
//		Coin coin1 = new Coin(new BigDecimal(2));
		this.rejectionSink = new CoinTray(5);
		this.standardSinks = new HashMap<BigDecimal, Sink<Coin>>();
//		this.standardSinks.put();
		this.overflowSink = new CoinStorageUnit(5);
		
	}
	
	@Test(expected = SimulationException.class)
	public void AbstractCoinValidatorSetupNoRejectionSink() {
		this.rejectionSink = null;
		coinValidator.setup(this.rejectionSink, this.standardSinks, this.overflowSink);
	}
	
	@Test(expected = SimulationException.class)
	public void AbstartctCoinValidatorSetupNoOverflowSink() {
		this.overflowSink = null;
		coinValidator.setup(this.rejectionSink, this.standardSinks, this.overflowSink);
	}
	
	@Test(expected = SimulationException.class)
	public void AbstractCoinValidatorSetupNoStandardSinks() {
		this.standardSinks = null;
		coinValidator.setup(this.rejectionSink, this.standardSinks, this.overflowSink);
	}
	
	@Test(expected = SimulationException.class)
	public void NumberOfSinksMismatch() {
		coinValidator.setup(this.rejectionSink, this.standardSinks, this.overflowSink);
	}
}
