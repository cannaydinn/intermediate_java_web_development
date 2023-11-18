package AdventureGame;
public class SafeHouse extends NormalLoc{
    
    public SafeHouse(Player player){
        super(player, "Güvenli Ev");
    }

    @Override
    public boolean onLocation() {
        System.err.println("Güvenli evdesiniz !");
        System.out.println("Canınız yenilendi !");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        return true;
    }
}
