package ie.cit.soft8027;

import ie.cit.soft8027.domain.Equipment;
import ie.cit.soft8027.domain.Player;
import ie.cit.soft8027.domain.PlayerCurrentItems;
import ie.cit.soft8027.repository.EquipmentRepository;
import ie.cit.soft8027.repository.PlayerCurrentItemsRepository;
import ie.cit.soft8027.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class GameMenu {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerCurrentItemsRepository playerCurrentItemsRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    private Scanner input = new Scanner(System.in);
    private Player loggedInPlayer = null;
    private boolean buyExecuted = false;
    private boolean sellExecuted = false;
    private boolean upgradeExecuted = false;

    private boolean hasUsedAllActions() {
        return buyExecuted && sellExecuted && upgradeExecuted;
    }


    public void run() {
        while (true) {
            displayLogin();
            int selection = input.nextInt();
            input.nextLine();
            switch (selection) {
                case 1:
                    break;
            }
        }
    }

    private void displayLogin() {
        System.out.println("-- Actions --");
        System.out.println(
                "Select an option: \n" +
                        "  1) Login\n" +
                        "  2) Exit\n"
        );
        int selection = input.nextInt();
        input.nextLine();
        if (selection == 1) {
            System.out.print("-- Enter username:");
            String username = input.nextLine();
            System.out.print("-- Enter password:");
            String password = input.nextLine();
            login(username, password);
        } else {
            exit();
        }
    }

    private void login(String username, String password)
    {
       // try {
            Player player = playerRepository.findByFullName(username);
            if (player != null)
            {
                //encrypt user input password and compare with encrypted password in DB
                //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                //if (!passwordEncoder.matches(password, player.getPassword())) {
                    System.out.println("Invalid Credentials ...");
                    exit();
            }
                loggedInPlayer = player;
                displayMainMenu();
            //} //else {
                displayLogin();
           // }
        //} catch (EmptyResultDataAccessException e) {
          //  System.out.println("Invalid credentials ...");
            displayLogin();
       // }
    }

    private void displayMainMenu() {
        displayUser();
        System.out.println("\n-- Actions --");
        System.out.println(
                "Select an option: \n" +
                        "  1) Display my items\n" +
                        "  2) Buy\n" +
                        "  3) Sell\n" +
                        "  4) Upgrade\n" +
                        "  5) Finish round \n" +
                        "  6) Buy Kubits \n" +
                        "  7) Exit\n "
        );

        int selection = input.nextInt();
        input.nextLine();
        switch (selection) {
            case 1:
                displayCurrentItems();
                displayMainMenu();
                break;
            case 2:
                if (buyExecuted) {
                    System.out.println("Buy already executed");
                    displayMainMenu();
                } else {
                    displayBuy();
                }
                break;
            case 3:
                if (sellExecuted) {
                    System.out.println("Sell already executed");
                    displayMainMenu();
                } else {
                    displaySell();
                }
                break;
            case 4:
                if (upgradeExecuted) {
                    System.out.println("Upgrade already executed");
                    displayMainMenu();
                } else {
                    displayUpgrade();
                }
                break;
            case 5:
                loggedInPlayer = null;
                displayLogin();
                break;
            case 6:
                displayBuyKubits();
                break;
            case 7:
                loggedInPlayer = null;
                System.exit(0);
                break;
        }

    }

    private void displayBuyKubits() {
        System.out.println("\nEnter EUR amount : [1EUR = 10 Kubits]\n");
        int eur = input.nextInt();
        input.nextLine();
        loggedInPlayer.setBalance(loggedInPlayer.getBalance() + (eur * 10));
        playerRepository.save(loggedInPlayer);
        System.out.println((eur * 10) + " Kubits were added to your balance\n");
        displayMainMenu();
    }

    private void displayUpgrade() {
        PlayerCurrentItems playerItems = playerCurrentItemsRepository.getPlayerItems(loggedInPlayer.getId());
        Equipment melee = equipmentRepository.get(playerItems.getMelee());
        Equipment nextMelee = melee != null ? equipmentRepository.findByTypeAndLevel("melee", melee.getLevel() + 1) : null;
        Equipment range = equipmentRepository.get(playerItems.getRangeW());
        Equipment nextRange = range != null ? equipmentRepository.findByTypeAndLevel("range", range.getLevel() + 1) : null;
        Equipment shield = equipmentRepository.get(playerItems.getShield());
        Equipment nextShield = shield != null ? equipmentRepository.findByTypeAndLevel("shield", shield.getLevel() + 1) : null;
        Equipment armour = equipmentRepository.get(playerItems.getArmour());
        Equipment nextArmour = armour != null ? equipmentRepository.findByTypeAndLevel("armour", armour.getLevel() + 1) : null;

        System.out.println("\nSelect item to upgrade:");
        if (melee != null) {
            System.out.println("1) " + melee + " " + nextPrice(melee, nextMelee));
        }
        if (range != null) {
            System.out.println("2) " + range + " " + nextPrice(range, nextRange));
        }
        if (shield != null) {
            System.out.println("3) " + shield + " " + nextPrice(shield, nextShield));
        }
        if (armour != null) {
            System.out.println("4) " + armour + " " + nextPrice(armour, nextArmour));
        }
        System.out.println("0) Back");
        int toSellId = input.nextInt();
        input.nextLine();
        if (toSellId == 0) displayMainMenu();
        Equipment equipmentToUpgrade = equipmentRepository.get(toSellId);
        int balance = loggedInPlayer.getBalance();
        switch (equipmentToUpgrade.getType()) {
            case "melee":
                playerItems.setMelee(nextMelee.getId());
                loggedInPlayer.setBalance(balance - (nextMelee.getPrice() - melee.getPrice()));
                System.out.println("\n Equipment upgraded: " + nextMelee);
                break;
            case "range":
                playerItems.setRangeW(nextRange.getId());
                loggedInPlayer.setBalance(balance - (nextRange.getPrice() - range.getPrice()));
                System.out.println("\n Equipment upgraded: " + nextRange);
                break;
            case "shield":
                playerItems.setShield(nextShield.getId());
                loggedInPlayer.setBalance(balance - (nextShield.getPrice() - shield.getPrice()));
                System.out.println("\n Equipment upgraded: " + nextShield);
                break;
            case "armour":
                playerItems.setArmour(nextArmour.getId());
                loggedInPlayer.setBalance(balance - (nextArmour.getPrice() - armour.getPrice()));
                System.out.println("\n Equipment upgraded: " + nextArmour);
                break;
        }

        playerCurrentItemsRepository.save(playerItems);
        playerRepository.save(loggedInPlayer);
        upgradeExecuted = true;
        displayMainMenu();

    }

    private String nextPrice(Equipment currentLevel, Equipment nextLevel) {
        return nextLevel == null ? "Already at max level" : "Upgrade price: " + (nextLevel.getPrice() - currentLevel.getPrice());
    }

    private void displaySell() {
        PlayerCurrentItems playerItems = playerCurrentItemsRepository.getPlayerItems(loggedInPlayer.getId());
        Equipment melee = equipmentRepository.get(playerItems.getMelee());
        Equipment range = equipmentRepository.get(playerItems.getRangeW());
        Equipment shield = equipmentRepository.get(playerItems.getShield());
        Equipment armour = equipmentRepository.get(playerItems.getArmour());
        System.out.println("\nSelect item to sell:");
        if (melee != null) System.out.println("1) " + melee);
        if (range != null) System.out.println("2) " + range);
        if (shield != null) System.out.println("3) " + shield);
        if (armour != null) System.out.println("4) " + armour);
        System.out.println("0) Back");

        int toSellId = input.nextInt();
        input.nextLine();
        if (toSellId == 0) displayMainMenu();
        Equipment equipmentToSell = equipmentRepository.get(toSellId);
        switch (equipmentToSell.getType()) {
            case "melee":
                playerItems.setMelee(0);
                break;
            case "range":
                playerItems.setRangeW(0);
                break;
            case "shield":
                playerItems.setShield(0);
                break;
            case "armour":
                playerItems.setArmour(0);
                break;
        }

        loggedInPlayer.setBalance(loggedInPlayer.getBalance() + equipmentToSell.getPrice());
        playerRepository.save(loggedInPlayer);
        playerCurrentItemsRepository.save(playerItems);
        System.out.println("\n You sold the Equipment: " + equipmentToSell + "\n");
        sellExecuted = true;
        displayMainMenu();
    }

    private void displayBuy() {
        List<Equipment> all = equipmentRepository.findAll();
        for (Equipment e : all) {
            System.out.println(e.getId() + ") " + e.getName() + "-" + e.getLevel() + " DMG:" + e.getDamage() + " Protection:" + e.getProtection() + " Type:" + e.getType() + " Price:" + e.getPrice() + " Kubits");
        }
        System.out.println("\n0 - Back\n");
        System.out.println("\nSelect Equipment to buy");
        int equipmentId = input.nextInt();
        input.nextLine();
        //go back to main menu
        if (equipmentId == 0) {
            displayMainMenu();
        }
        Equipment equipmentToBuy = equipmentRepository.get(equipmentId);
        int loggedInPlayerId = loggedInPlayer.getId();
        PlayerCurrentItems playerItems = playerCurrentItemsRepository.getPlayerItems(loggedInPlayerId);
        switch (equipmentToBuy.getType()) {
            case "melee":
                if (playerItems.getMelee() == 0) {
                    buyEquipment(equipmentToBuy);
                } else {
                    System.out.println("Slot is full please sell the item first !\n");
                }
                break;
            case "range":
                if (playerItems.getRangeW() == 0) {
                    buyEquipment(equipmentToBuy);
                } else {
                    System.out.println("Slot is full please sell the item first !\n");
                }
                break;
            case "shield":
                if (playerItems.getShield() == 0) {
                    buyEquipment(equipmentToBuy);
                } else {
                    System.out.println("Slot is full please sell the item first !\n");
                }
                break;
            case "armour":
                if (playerItems.getArmour() == 0) {
                    buyEquipment(equipmentToBuy);
                } else {
                    System.out.println("\nSlot is full please sell the item first !\n\n");
                }
                break;
        }
        displayMainMenu();
    }

    private void buyEquipment(Equipment equipmentToBuy) {
        int price = equipmentToBuy.getPrice();
        if (price > loggedInPlayer.getBalance()) {
            System.out.println("\n You don't have enough Kubits");
            displayMainMenu();
        } else {
            PlayerCurrentItems playerItems = playerCurrentItemsRepository.getPlayerItems(loggedInPlayer.getId());
            switch (equipmentToBuy.getType()) {
                case "melee":
                    playerItems.setMelee(equipmentToBuy.getId());
                    break;
                case "range":
                    playerItems.setRangeW(equipmentToBuy.getId());
                    break;
                case "shield":
                    playerItems.setShield(equipmentToBuy.getId());
                    break;
                case "armour":
                    playerItems.setArmour(equipmentToBuy.getId());
                    break;
            }
            loggedInPlayer.setBalance(loggedInPlayer.getBalance() - equipmentToBuy.getPrice());
            playerCurrentItemsRepository.save(playerItems);
            playerRepository.save(loggedInPlayer);
            System.out.println("\nEquipment: " + equipmentToBuy + " added to slot\n");
            buyExecuted = true;
            displayMainMenu();
        }
    }

    private void displayCurrentItems() {
        PlayerCurrentItems playerItems = playerCurrentItemsRepository.getPlayerItems(loggedInPlayer.getId());
        Equipment armour = equipmentRepository.get(playerItems.getArmour());
        Equipment melee = equipmentRepository.get(playerItems.getMelee());
        Equipment range = equipmentRepository.get(playerItems.getRangeW());
        Equipment shield = equipmentRepository.get(playerItems.getShield());
        displayUser();
        System.out.println("\nCurrent Items");
        System.out.println("Melee slot = " + melee);
        System.out.println("Range slot = " + range);
        System.out.println("Shield slot = " + shield);
        System.out.println("Armour slot = " + armour);
    }

    private void displayUser() {
        System.out.println("\nLogged in user: " + loggedInPlayer.getFullName());
        System.out.println("Balance:" + loggedInPlayer.getBalance() + " Kubits");
        String actions = (!buyExecuted ? " [Buy] " : "") + (!sellExecuted ? " [Sell] " : "") + (!upgradeExecuted ? " [Upgrade] " : "");
        String remainingActions = hasUsedAllActions() ? "None" : actions;
        System.out.println("Remaining actions:" + remainingActions);
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(1);
    }
}