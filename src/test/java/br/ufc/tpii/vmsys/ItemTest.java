package br.ufc.tpii.vmsys;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ufc.tpii.vmsys.inventory.Item;

public class ItemTest {

	@Test
	public void testGetName() {
		Item item = new Item("Café", 3.5, 3);
		
		assertEquals("Café", item.getName());
	}

	@Test
	public void testGetPrice() {
		Item item = new Item("Café", 3.5, 3);
		
		assertEquals(3.5, item.getPrice());
	}

	@Test
	public void testSetPrice() {
		Item item = new Item("Café", 3.5, 3);
		item.setPrice(2.5);
		
		assertEquals(2.5, item.getPrice());
	}	

	@Test
	public void testGetCount() {
		Item item = new Item("Café", 3.5, 3);
		
		assertEquals(3, item.getCount());
	}

	@Test
	public void testDecCount() {
		Item item = new Item("Café", 3.5, 3);
		item.decCount();
		assertEquals(2, item.getCount());
	}

	@Test
	public void testIncCount() {
		Item item = new Item("Café", 3.5, 3);
		item.incCount();
		assertEquals(4, item.getCount());
	}

}
