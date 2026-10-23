class Fighter {

    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}

class Warrior extends Fighter {

    @Override
    public boolean isVulnerable() {
        return false;
    }

    @Override
    public int getDamagePoints(Fighter fighter) {
        return fighter.isVulnerable() ? 10 : 6;
    }

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }
}

class Wizard extends Fighter {
    private boolean usableSpell = false;

    public void prepareSpell() {
        this.usableSpell = true;
    }

    public boolean hasSpell() {
        return this.usableSpell;
    }
    
    @Override
    public boolean isVulnerable() {
        return !this.hasSpell();
    }

    @Override
    public int getDamagePoints(Fighter fighter) {
        return this.hasSpell() ? 12 : 3;
    }

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }
}