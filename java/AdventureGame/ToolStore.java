package AdventureGame;
public class ToolStore extends NormalLoc{

    public ToolStore(Player player){
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation(){
        System.out.println("Mağazaya Hoşgeldiniz !");
        System.out.println("1 - Silahlar");
        System.out.println("2 - Zırhlar");
        System.out.println("3 - Çıkış Yap");
        System.out.print("Seçiminiz : ");
        int selectedCase = sc.nextInt();
        while(selectedCase < 1 || selectedCase > 3){
            System.out.println("Geçersiz değer, Lütfen tekrar deneyiniz");
            selectedCase = sc.nextInt();
        }
        switch(selectedCase){
            case 1:
                printWeapon();
                buyWeapon();
                break;
            case 2:
                printArmor();
                buyArmor()
;               break;
            case 3:
                System.out.println("Çıkış işlemi gerçekleşmiştir !");
                return true; 
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("--------------------------");
        System.out.println("Silahlar");
        for(Weapon w : Weapon.weapons()){
            System.out.println(w.getId()+" - "+w.getName()+ " <Hasar: "+ w.getDamage()+" Para: "+w.getMoney()+">");
        }
        
    }

    public void buyWeapon(){
        System.out.print("Lütfen bir silah seçiniz: ");
        int selectWeapon = sc.nextInt();
        while(selectWeapon < 1 || selectWeapon > Weapon.weapons().length){
            System.out.println("Geçersiz değer, Lütfen tekrar deneyiniz !!");
            selectWeapon = sc.nextInt();
            System.out.println("--------------------------");
        }
        Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeapon);
        if(selectedWeapon != null){
            if(selectedWeapon.getMoney() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır !");
                System.out.println("--------------------------");
            }
            else{
                System.out.println("--------------------------");
                System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getMoney();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız: "+this.getPlayer().getMoney());
                System.out.println("Önceki silahınız: "+this.getPlayer().getInventory().getWeapon().getName()); 
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                System.out.println("--------------------------");
                
            }
        }
    }

    public void printArmor(){
        System.out.println("Zırhlar");
        for(Armor a : Armor.armors()){
            System.out.println(a.getId() +"-"+a.getName()+"<Zırh: "+a.getBlock()+" Para: "+a.getPrice()+">");
            System.out.println("--------------------------");
        }
    }

    public void buyArmor(){
        System.out.println("Lütfen bir zırh seçiniz: ");
        int selectArmor = sc.nextInt();
        while(selectArmor < 1 || selectArmor > Armor.armors().length){
            System.out.println("Geçersiz değer, Lütfen tekrar deneyiniz !!");
            selectArmor = sc.nextInt();
            System.out.println("--------------------------");
        }
        Armor selectedArmor = Armor.getArmorObjByID(selectArmor);
        if(selectedArmor != null){
            if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yeterli paranız bulunmamaktadır !");
                System.out.println("--------------------------");
            }
            else{
                System.out.println("--------------------------");
                System.out.println(selectedArmor.getName()+" zırh aldınız !");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan paranız: "+this.getPlayer().getMoney());
                System.out.println("Önceki silahınız: "+this.getPlayer().getInventory().getArmor().getName()); 
                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("--------------------------");
            }
        }
    }
    
}
