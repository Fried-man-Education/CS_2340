package com.theswagbois.towerdefense.entities.components;

import com.almasb.fxgl.entity.component.Component;

public class BulletComponent extends Component {
    private int damage;

    public BulletComponent(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
