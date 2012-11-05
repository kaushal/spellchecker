public class prefixTree {

    private Node root;

    public prefixTree() {
        root = new Node();
    }

    public class Node {
        private Node[] next = new Node[26];
        private boolean word = false;
    }

    public void insert(String word) {
        word = word.toLowerCase();
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                if (temp.next[word.charAt(i) - 'a'] == null) {
                    temp.next[word.charAt(i) - 'a'] = new Node();
                }

                temp = temp.next[word.charAt(i) - 'a'];
            }
        }
        temp.word = true;
    }

    public boolean search(String word) {
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            if (temp.next[word.charAt(i) - 'a'] != null) {
                temp = temp.next[word.charAt(i) - 'a'];
            }
            else {
                return false;
            }
        }
        if (temp.word)
            return true;

        return false;

    }
}
