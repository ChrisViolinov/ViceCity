package viceCity.models.guns;

public class Rifle extends BaseGun {

    protected static int BULLETS_PER_SHOT = 5;
    int reload = 50;

    public Rifle(String name) {
        super(name, 50, 500);
        this.bulletsPerBarrel = 50;
        this.totalBullets = 500;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return bulletsPerBarrel;
    }

    @Override
    public int getTotalBullets() {
        return totalBullets;
    }

    @Override
    public int fire() {
        setBulletsPerBarrel(bulletsPerBarrel-BULLETS_PER_SHOT);
        if (bulletsPerBarrel == 0 && totalBullets > 0) {
            setTotalBullets(totalBullets-reload);
            setBulletsPerBarrel(bulletsPerBarrel += reload);
        }
        return BULLETS_PER_SHOT;
    }
    @Override
    public boolean canFire() {
        return bulletsPerBarrel + totalBullets > 0;
    }
}
