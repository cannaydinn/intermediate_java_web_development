import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String name;
    private String charName;
    private Inventory inventory;
    private Scanner sc = new Scanner(System.in);

    public Player(String name){
        super();
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        for(GameChar gameChar : charList){
            System.out.println("\t ID: " +gameChar.getId() +
                        "\t Karakter:" +gameChar.getName() +
                        "\t Hasar:" +gameChar.getDamage() +
                        "\t Sağlık:" +gameChar.getHealth() +
                        "\t Para:" +gameChar.getMoney());
        }
        System.out.print("Lütfen oyuna başlamak için bir karakter seçiniz: "); 
        int selectedChar = sc.nextInt();
        System.out.println("--------------------------");

        switch(selectedChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
                break;
        }
        System.out.println("Karakter: "+this.getCharName() +
                        ", Hasar: "+this.getTotalDamage() +
                        ", Sağlık: "+this.getHealth() +
                        ", Para: "+this.getMoney());
    }

    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo(){
        System.out.println("Silahınız: "+this.getInventory().getWeapon().getName()+
                        " , Zırh: "+this.getInventory().getArmor().getName()+
                        " , Hasarınız: "+this.getDamage()+
                        " , Blok: "+this.getInventory().getArmor().getBlock()+
                        " , Sağlık: "+this.getHealth()+
                        " , Para: "+this.getMoney());
    }

    public int getTotalDamage(){
        return damage +this.getInventory().getWeapon().getDamage();
    }

    public int getDamage(){
        return damage;
    }

    public int getHealth(){
        if(health < 0){
            health = 0;
        }
        return health;
    }

    public int getOriginalHealth(){
        return originalHealth;
    }

    public int getMoney(){
        return money;
    }

    public String getName(){
        return name;
    }

    public String getCharName(){
        return charName;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setOriginalHealth(int originalHealth){
        this.originalHealth = originalHealth;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCharName(String charName){
        this.charName = charName;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }
}
