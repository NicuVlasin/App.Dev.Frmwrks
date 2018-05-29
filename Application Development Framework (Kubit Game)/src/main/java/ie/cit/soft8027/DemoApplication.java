package ie.cit.soft8027;

import java.util.List;
import java.util.Scanner;

import ie.cit.soft8027.repository.PlayerCurrentItemsRepository;
import ie.cit.soft8027.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import ie.cit.soft8027.domain.PlayerCurrentItems;
import ie.cit.soft8027.domain.Player;


@SpringBootApplication
@ComponentScan({"ie.cit.soft8027"})
@EntityScan("ie.cit.soft8027.domain")


public class DemoApplication implements CommandLineRunner
{

	private Scanner input = new Scanner(System.in);


	public static void main(String[] args)
	{
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PlayerCurrentItemsRepository playerCurrentItemsRepository;

	@Autowired
	private GameMenu gameMenu;


	@Override
	public void run(String... arg0) throws Exception
	{
		gameMenu.run();
	}


	public void display() throws Exception
	{
		Player player = playerRepository.get(1);

		System.out.println("-- Actions --");
		System.out.println(
				"Select an option: \n" +
						"  1) Display inventory\n" +
						"  2) Shop\n" +
						"  3) Finish round \n" +
						"  4) Exit\n "
		);

		int selection = input.nextInt();
		input.nextLine();

		switch (selection)
		{
			case 1:
				System.out.println(playerCurrentItemsRepository.findAll());
				this.run();
				break;
			case 2:
				this.displayShop();
				this.run();
				break;
			case 3:
				this.run();
				break;
			case 4:
				this.exit();
				break;
			default:
				System.out.println("Invalid selection.");
				break;
		}
	}

	public void displayShop()
	{
		List<PlayerCurrentItems> newEquipment = playerCurrentItemsRepository.findAll();

		System.out.println("-- Welcome to the shop! We have quite a selection  --");
		System.out.println(
				"Select an option: \n" +
						"  1) Buy\n" +
						"  2) Sell\n" +
						"  3) Upgrade \n" +
						"  4) Exit\n "
		);

		int selection = input.nextInt();
		input.nextLine();

		switch (selection)
		{
			case 1:
				System.out.println(newEquipment);
				this.displayShop();
				break;
			case 2:
				//queries.query02();
				this.displayShop();
				break;
			case 3:
				this.displayShop();
				break;
			case 4:
				this.exit();
				break;
			default:
				System.out.println("Invalid selection.");
				break;
		}
	}

	private void exit()
	{
		System.out.println("Exiting...");
		System.exit(1);
	}
}