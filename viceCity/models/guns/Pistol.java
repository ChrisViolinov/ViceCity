package viceCity.models.guns;

public class Pistol extends BaseGun {

    private static int BULLETS_PER_SHOT = 1;

    public Pistol(String name) {
        super(name, 10, 100);
        this.bulletsPerBarrel = 10;
        this.totalBullets = 100;
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
    public boolean canFire() {
        return bulletsPerBarrel + totalBullets > 0;
    }

    @Override
    public int getTotalBullets() {
        return totalBullets;
    }

    @Override
    public int fire() {
        setBulletsPerBarrel(bulletsPerBarrel - BULLETS_PER_SHOT);

        if (this.bulletsPerBarrel == 0 && totalBullets > 0) {
            int reload = 10;
            setTotalBullets(totalBullets -= reload);
            setBulletsPerBarrel(bulletsPerBarrel += reload);
        }
        return BULLETS_PER_SHOT;
    }
}
