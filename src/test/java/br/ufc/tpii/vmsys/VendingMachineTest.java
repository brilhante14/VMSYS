package br.ufc.tpii.vmsys;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import br.ufc.tpii.vmsys.inventory.HashMapInventory;
import br.ufc.tpii.vmsys.inventory.Inventory;
import br.ufc.tpii.vmsys.inventory.Item;
import br.ufc.tpii.vmsys.inventory.exceptions.ItemAlreadyAdded;

public class VendingMachineTest {
	VendingMachine vm;
	Inventory inventory;
	
	@Test
	public void testAddCoins() {
		inventory = new HashMapInventory();
		vm = new VendingMachine(inventory);
		
		vm.addCoins(3.5);
		
		assertEquals(3.5, vm.howManyCoinsLeft());
	}

	@Test
	public void testWithdrawRemainingCoins() {
		inventory = new HashMapInventory();
		vm = new VendingMachine(inventory);
		
		vm.addCoins(3.5);
		
		double coins = vm.withdrawRemainingCoins();
		
		assertEquals(0.0, vm.howManyCoinsLeft());
		assertEquals(3.5, coins);
	}

	@Test
	public void testHowManyCoinsLeft() {
		inventory = new HashMapInventory();
		
		vm = new VendingMachine(inventory);
		assertEquals(0.0, vm.howManyCoinsLeft());
		
		vm.addCoins(3.5);
		assertEquals(3.5, vm.howManyCoinsLeft());
	}

	@Test
	public void testVendSuccessfully() {
		try {
			inventory = new HashMapInventory();
			Item item1 = new Item("Café", 3.5, 2);
			inventory.addItem(item1);
			vm = new VendingMachine(inventory);
			vm.addCoins(3.5);
			
			vm.vend("Café");
			
			assertEquals(1, item1.getCount());
			assertEquals(0, vm.howManyCoinsLeft());
		} catch (Exception e) {
			fail();
		} 
		
	}
	
	@Test
	public void testVendInvalidSelection() {
		inventory = new HashMapInventory();
		vm = new VendingMachine(inventory);

		InvalidSelection thrown = assertThrows(InvalidSelection.class, () -> vm.vend("Café"));
		
		assertEquals(thrown.getMessage(), "Invalid item selection: Café");
	}
	
	@Test
	public void testVendOutOfStock() {
		inventory = new HashMapInventory();
		Item item1 = new Item("Café", 3.5, 0);
		try {
			inventory.addItem(item1);
		} catch (ItemAlreadyAdded e) {
			fail();
		}
		vm = new VendingMachine(inventory);
		vm.addCoins(3.5);

		OutOfStock thrown = assertThrows(OutOfStock.class, () -> vm.vend("Café"));
		
		assertEquals(thrown.getMessage(), "Item out of stock: Café");
	}
	
	@Test
	public void testVendInsufficientFunds() {
		inventory = new HashMapInventory();
		Item item1 = new Item("Café", 3.5, 2);
		try {
			inventory.addItem(item1);
		} catch (ItemAlreadyAdded e) {
			fail();
		}
		vm = new VendingMachine(inventory);
		vm.addCoins(2.0);

		InsufficientFunds thrown = assertThrows(InsufficientFunds.class, () -> vm.vend("Café"));
		
		assertEquals(thrown.getMessage(), "Insufficient coins to by Café: 2.0");
	}
}
