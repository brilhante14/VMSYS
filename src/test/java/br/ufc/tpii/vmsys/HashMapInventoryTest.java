package br.ufc.tpii.vmsys;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemNotFound;

public class HashMapInventoryTest {

	@Test
	public void testAddItemSuccessfully() {
		HashMapInventory inventory = new HashMapInventory();
		
		Item item = new Item("Café", 3.5, 3);
		try {
			inventory.addItem(item);
		} catch (ItemAlreadyAdded e) {
			fail();
		}
		
		assertEquals(1, inventory.numberOfItems());
	}
	
	@Test
	public void testAddItemItemAlreadyAdded() {
		HashMapInventory inventory = new HashMapInventory();
		
		Item item = new Item("Café", 3.5, 3);
		try {
			inventory.addItem(item);
		} catch (ItemAlreadyAdded e) {
			fail();
		}
		
		ItemAlreadyAdded thrown = assertThrows(ItemAlreadyAdded.class, () -> inventory.addItem(item));
		
		assertEquals("Item already added: Café", thrown.getMessage());
	}

	@Test
	public void testRemoveItemSuccessfully() {
		HashMapInventory inventory = new HashMapInventory();
		
		Item item = new Item("Café", 3.5, 3);
		try {
			inventory.addItem(item);
			inventory.removeItem("Café");
		} catch (Exception e) {
			fail();
		}
		
		assertEquals(0, inventory.numberOfItems());
	}
	
	@Test
	public void testRemoveItemItemNotFound() {
		HashMapInventory inventory = new HashMapInventory();
		
		ItemNotFound thrown = assertThrows(ItemNotFound.class, () -> inventory.removeItem("Café"));
		
		assertEquals("Item not found: Café", thrown.getMessage());
	}

	@Test
	public void testGetItem() {
		HashMapInventory inventory = new HashMapInventory();
		
		Item item = new Item("Café", 3.5, 3);
		try {
			inventory.addItem(item);
			Item itemGotten = inventory.getItem("Café");
			
			assertEquals("Café", itemGotten.getName());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testGetItemItemNotFound() {
		HashMapInventory inventory = new HashMapInventory();
		
		ItemNotFound thrown = assertThrows(ItemNotFound.class, () -> inventory.getItem("Café"));
		
		assertEquals("Item not found: Café", thrown.getMessage());
	}

	@Test
	public void testNumberOfItems() {
		HashMapInventory inventory = new HashMapInventory();
		
		Item item = new Item("Café", 3.5, 3);
		assertEquals(0, inventory.numberOfItems());
		
		try {
			inventory.addItem(item);
		} catch (ItemAlreadyAdded e) {
			fail();
		}
		
		assertEquals(1, inventory.numberOfItems());
	}

}
