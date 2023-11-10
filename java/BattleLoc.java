import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Random random = new Random();

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız ! " +this.getName());
        System.out.println("Dikkatli ol !! Burada "+obsNumber+" tane "+this.getObstacle().getName()+" yaşıyor");
        System.out.println("<S>avaş veya <K>aç : ");
        String selectCase = sc.nextLine().toUpperCase();
        while(!selectCase.equals("S") && !selectCase.equals("K")){
            System.out.println("Yanlış tuşlama yaptınız. Tekrar deneyiniz !!");
            selectCase = sc.nextLine().toUpperCase();
        }
        if(selectCase.equals("S") && combat(obsNumber)){
            System.out.println(this.getName()+" tüm düşmanları yendiniz !! ");
            return true;
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz !!");
            return false; 
        }
        return true;
    }

    public int randomSnakeDamage(){
        return random.nextInt() * 100;
    }

    public int startChance(){
        return random.nextInt() * 100;
    }

    public void drop(){
		int chance = random.nextInt() * 100;
        if(chance >= 45){
            int itemChance = random.nextInt(1,55);
            if(itemChance <= 15){
                int weaponChance = random.nextInt() * 100;
                if(weaponChance <= 20){
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                    System.out.println(this.getPlayer().getInventory().getWeapon().getName() + " Kazandınız ..");
                }
                else if(weaponChance > 20 && weaponChance <= 50){
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
					System.out.println(this.getPlayer().getInventory().getWeapon().getName() + " Kazandınız ..");
                }
                else{
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
					System.out.println(this.getPlayer().getInventory().getWeapon().getName() + " Kazandınız ..");
                }
            }
            if(itemChance > 15 && itemChance <= 30){
                int armorChance = random.nextInt() * 100;
                if(armorChance <= 20){
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " Kazandınız ..");
                }
                else if(armorChance > 20 && armorChance <= 50){
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " Kazandınız ..");
                }
                else{
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                    System.out.println(this.getPlayer().getInventory().getArmor().getName() + " Kazandınız ..");
                }
            }
            if(itemChance > 30){
                int moneyChance = random.nextInt() * 100;
                if(moneyChance <= 20){
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                    System.out.println(" 10 Para Kazandınız ..");
                }
                else if(moneyChance > 20 && moneyChance <= 50){
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                    System.out.println(" 5 Para Kazandınız ..");
                }
                else{
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                    System.out.println(" 1 Para Kazandınız ..");
                }
            }
        }
        else{
            System.out.println(" İtem Kazanamadınız !! ");
        }
    }

    public boolean combat(int maxObstacle){
        if(this.getObstacle().getName().equals("Yılan")){
            this.getObstacle().setDamage(randomSnakeDamage());
        }
        for(int i=1;i<=maxObstacle;i++){
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            int startChance = startChance();
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<V>ur veya <K>aç : ");
                String selectCombat = sc.nextLine().toUpperCase();
                if(selectCombat.equals("V")){
                    if(startChance < 50){
                        System.out.println("Siz vurdunuz !!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu !!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    }
                    else{
                        if(this.getObstacle().getHealth() > 0){
                            System.out.println();
                            System.out.println("Canavar size vurdu !!");
                            int obstacleDamage = this.getObstacle().getHealth() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }   
                        System.out.println("Siz vurdunuz !!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                }
                else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz !!");
                if(this.getObstacle().getName().equals("Yılan")){
                    drop();
                }
                else{
                    System.out.println(this.getObstacle().getAward() + " Para kazandınız!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    if(this.getObstacle().getName().equals("Zombi")){
                        this.getPlayer().getInventory().setFood(1);
                        System.out.println(" Yemek Kazandınız ..");
                    }
                    if(this.getObstacle().getName().equals("Vampir")){
                        this.getPlayer().getInventory().setWood(2);
                        System.out.println(" Odun Kazandınız ..");
                    }
                    if(this.getObstacle().getName().equals("Ayı")){
                        this.getPlayer().getInventory().setWater(3);
                        System.out.println(" Su Kazandınız ..");
                    }
                }
                System.out.println("Güncel paranız: "+this.getPlayer().getMoney());   
            }
        }
        return false;
    }

    public void afterHit(){
        System.out.println("Canınız: "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: "+this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats(){
        System.out.println("Oyuncu Değerleri");
        System.out.println("------------------------------");
        System.out.println("Sağlık: "+this.getPlayer().getHealth());
        System.out.println("Silah: "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar: "+this.getPlayer().getTotalDamage());
        System.out.println("Zırh: "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: "+this.getPlayer().getMoney());
    }

    public void obstacleStats(int i){
        System.out.println(i+". "+this.getObstacle().getName()+" Değerleri");
        System.out.println("-------------------------------");
        System.out.println("Sağlık: "+this.getObstacle().getHealth());
        System.out.println("Hasar: "+this.getObstacle().getDamage());
        System.out.println("Ödül: "+this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle(){
        return obstacle;
    }

    public String getAward(){
        return award;
    }

    public int getMaxObstacle(){
        return maxObstacle;
    }

    public void setObstacle(Obstacle obstacle){
        this.obstacle = obstacle;
    }

    public void setAward(String award){
        this.award = award;
    }

    public void setMaxObstacle(int maxObstacle){
        this.maxObstacle = maxObstacle; 
    }
    
}
