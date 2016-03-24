package model;

import org.junit.Test;

import util.Baralho;

public class MesaTest {

	@Test
	public void test() {
		Mesa mesa = new Mesa(new Baralho());
		System.out.println(mesa);
	}

}
