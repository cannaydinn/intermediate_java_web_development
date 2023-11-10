import java.util.Scanner;

public class Game {
    
    private Scanner sc = new Scanner(System.in);
    
    public void start(){
        System.out.println("Macera dolu hayatta kalma oyunu");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = sc.nextLine();

        Player player = new Player(playerName);
        System.out.println("Sayın "+ player.getName()+ " hoşgeldiniz!!");
        player.selectChar();
        
        Location location = null;
        while(true){
            player.printInfo();
            System.out.println("--------------------------");
            System.out.println("Bölgeler");
            System.out.println("1 - Güvenli Ev --> Burası sizin için güvenli bir ev, düşman yoktur !");
            System.out.println("2 - Eşya Dükkanı --> Silah veya zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara --> Dikkatli ol zombi çıkabilir ! Ödül <Yemek>");
            System.out.println("4 - Orman --> Dikkatli ol vampir çıkabilir ! Ödül <Odun>");
            System.out.println("5 - Nehir --> Dikkatli ol ayı çıkabilir ! Ödül <Su>");
            System.out.println("6 - Maden --> Dikkatli ol zehirli yılan çıkabilir ! Ödül <İtem, para veya hiçbir şey>");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            int selectedLoc = sc.nextInt();
            System.out.println("--------------------------");
            if(selectedLoc == 3 && player.getInventory().getFood() == 1){
                selectedLoc = 0;
                System.out.println("Görev Tamamlandı !!");
                System.out.println();
                System.out.println("Güvenli Ev'e gidiyorsunuz !!");
            }
            if(selectedLoc == 4 && player.getInventory().getWood() == 1){
                selectedLoc = 0;
                System.out.println("Görev Tamamlandı !!");
                System.out.println();
                System.out.println("Güvenli Ev'e gidiyorsunuz !!");
            }
            if(selectedLoc == 5 && player.getInventory().getWater() == 1){
                selectedLoc = 0;
                System.out.println("Görev Tamamlandı !!");
                System.out.println();
                System.out.println("Güvenli Ev'e gidiyorsunuz !!");
            }
            switch(selectedLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz !! ");
            }
            if (location == null) {
				System.out.println("Korkup Kaçtın !!");
				break;
			}
            if(!location.onLocation()){
                System.out.println("GAME OVER");
                break;
            }
        }
    }
}
