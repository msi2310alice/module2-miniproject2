package warehouse;

import warehouse.service.IdGenerator;
import warehouse.service.Inventory;
import warehouse.view.ConsoleMenu;

public class App {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        IdGenerator idGenerator = new IdGenerator();

        ConsoleMenu consoleMenu = new ConsoleMenu(inventory, idGenerator);

        consoleMenu.start();
    }
}
