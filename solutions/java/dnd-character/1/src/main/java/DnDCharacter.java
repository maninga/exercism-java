import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

enum Ability {
    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA;
}

class DnDCharacter {

    final Random random = new Random();
    final Map<Ability, Integer> abilities = new HashMap<>();
    int hitPoints;

    DnDCharacter() {
        this.setAbility(Ability.STRENGTH, this.rollDice());
        this.setAbility(Ability.DEXTERITY, this.rollDice());
        this.setAbility(Ability.CONSTITUTION, this.rollDice());
        this.setAbility(Ability.INTELLIGENCE, this.rollDice());
        this.setAbility(Ability.WISDOM, this.rollDice());
        this.setAbility(Ability.CHARISMA, this.rollDice());
        this.setHitPoints();
    }

    Map<Ability, Integer> getAbilities() {
        return this.abilities;
    }
    
    int rollOnce() {
        return random.nextInt(6) + 1;
    }

    void setAbility(Ability ability, List<Integer> scores) {
        this.getAbilities().put(ability, this.ability(scores));
    }

    void setHitPoints () {
        this.hitPoints = 10 + this.modifier(this.getConstitution());
    }

    int ability(List<Integer> scores) {
        List<Integer> sortedScores = new ArrayList<>(scores);
        sortedScores.sort(Collections.reverseOrder());
        int sum = 0;
        for (int num : sortedScores.subList(0, sortedScores.size() - 1)) {
            sum += num;
        }
        return sum;
    }

    List<Integer> rollDice() {
        return List.of(rollOnce(), rollOnce(), rollOnce(), rollOnce());
    }

    int modifier(int input) {
        return (input - (input % 2 == 0 ? 0 : 1) - 10) / 2;
    }

    int getStrength() {
        return this.getAbilities().get(Ability.STRENGTH);
    }

    int getDexterity() {
        return this.getAbilities().get(Ability.DEXTERITY);
    }

    int getConstitution() {
        return this.getAbilities().get(Ability.CONSTITUTION);
    }

    int getIntelligence() {
        return this.getAbilities().get(Ability.INTELLIGENCE);
    }

    int getWisdom() {
        return this.getAbilities().get(Ability.WISDOM);
    }

    int getCharisma() {
        return this.getAbilities().get(Ability.CHARISMA);
    }

    int getHitpoints() {
        return this.hitPoints;
    }
}
