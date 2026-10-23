import java.util.HashSet;
import java.util.List;
import java.util.Set;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        Set<String> collection = new HashSet<>();
        for (String card: cards)
            collection.add(card);
        return collection;
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        return !(myCollection.containsAll(theirCollection) || theirCollection.containsAll(myCollection));
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        Set<String> commonCollection = new HashSet<>(collections.get(0));
        for (Set<String> collection: collections)
            commonCollection.retainAll(collection);
        return commonCollection;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> allCollection = new HashSet<>();
        for (Set<String> collection: collections)
            allCollection.addAll(collection);
        return allCollection;
    }
}
