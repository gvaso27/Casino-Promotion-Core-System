package test;

import service.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class appServicesTest {

    private appServices service;

    @Before
    public void setUp() {
        service = new appServices();
    }

    @Test
    public void testRegisterNewUser() {
        service.register("testUser");
        service.register("testUser");
        assertEquals(1, service.getUsers().size());
        assertEquals("testUser", service.getUsers().getFirst().getUsername());
    }

    @Test
    public void testAddScenario() {
        service.addScenario(100, 200, 300);
        assertEquals(1, service.getScenarios().size());
        assertEquals(100, service.getScenarios().getFirst().getPrize1());
        assertEquals(200, service.getScenarios().getFirst().getPrize2());
        assertEquals(300, service.getScenarios().getFirst().getPrize3());
    }

    @Test
    public void testBalanceForUser() {
        service.register("testUser");
        assertEquals(0, service.balance("testUser"));
        assertEquals(0, service.balance("nonExistentUser"));
    }

    @Test
    public void testDeposit() {
        service.register("testUser");
        service.deposit("testUser", 500);
        assertEquals(500, service.balance("testUser"));

        service.deposit("nonExistentUser", 500);
        assertEquals(0, service.balance("nonExistentUser"));
    }

    @Test
    public void testBet() {
        service.register("testUser");
        service.deposit("testUser", 500);
        service.bet("testUser", 200, true);
        assertEquals(700, service.balance("testUser"));

        service.bet("testUser", 200, false);
        assertEquals(500, service.balance("testUser"));

        service.bet("testUser", 501, true);
        assertEquals(500, service.balance("testUser"));
    }

    @Test
    public void testBetAssignScenario() {

        service.register("testUser");
        service.register("testUser1");

        service.addScenario(100, 200, 300);
        service.addScenario(200, 300, 400);

        service.deposit("testUser", 1000); // Ensure max deposit condition is met
        service.deposit("testUser1", 1000); // Ensure max deposit condition is met

        service.bet("testUser", 100, true);//testUser balance 1100 + prize1 = 1200
        assertNotNull(service.getUsers().getFirst().getScenario());
        assertEquals(1200, service.balance("testUser"));//testUser takes prize1 from first scenario

        service.bet("testUser1", 100, true);//testUser1 balance 900 + prize1 = 1100
        assertNotNull(service.getUsers().get(1).getScenario());
        assertEquals(1100, service.balance("testUser1"));//testUser1 takes prize1 from first scenario

        service.bet("testUser", 100, true);//testUser balance 1300
        service.bet("testUser", 100, true);//testUser balance 1200 + prize2 = 1400
        assertNotNull(service.getUsers().getFirst().getScenario());
        assertEquals(1400, service.balance("testUser"));//testUser takes prize2 from first scenario

        service.bet("testUser1", 100, true);//testUser1 balance 1200
        service.bet("testUser1", 100, true);//testUser1 balance 1100 + prize2 = 1400
        assertNotNull(service.getUsers().get(1).getScenario());
        assertEquals(1400, service.balance("testUser1"));//testUser1 takes prize2 from first scenario
    }
}
