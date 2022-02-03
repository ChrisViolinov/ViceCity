package viceCity.models.guns;

import static viceCity.common.ExceptionMessages.*;

public abstract class BaseGun implements Gun {

    protected String name;
    protected int bulletsPerBarrel;
    protected int totalBullets;
    private boolean canFire;

    BaseGun(String name, int bulletsPerBarrel, int totalBullets) {

       this.setName(name);

        this.setBulletsPerBarrel(bulletsPerBarrel);

        this.setTotalBullets(totalBullets);

        if(bulletsPerBarrel+totalBullets>0){
            canFire = true;
        }
    }

    private void setName(String name) {
        if (name == null || name.trim().equals("")) {
            throw new NullPointerException(NAME_NULL);
        }
            this.name = name;
    }

    protected void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException(BULLETS_LESS_THAN_ZERO);
        }
          this.bulletsPerBarrel = bulletsPerBarrel;
    }
    protected void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException(TOTAL_BULLETS_LESS_THAN_ZERO);
        } else {
             this.totalBullets = totalBullets;
        }
    }

    @Override
    public boolean canFire() {
        return bulletsPerBarrel + totalBullets > 0;
    }
}
