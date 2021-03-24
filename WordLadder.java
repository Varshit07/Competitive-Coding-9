/**
 * TC: O(n*n*m*constant) where n is the length of word and m is the number of words in wordList
 * SC: O(n*n*m*constant) where n is the length of word and m is the number of words in wordList
 * Approach: The relation between the words of the wordList is that each letter should differ by its adjacentWord by only one character
 *           This relation transforms the problem into a graph problem where the nodes are words in wordList and there exists an edge 
 *           between two nodes of a wordList if and only if the corresponding words differ by one letter
 *           So to find the ladder length do a BreadthFirstSearch keeping track of level in this graph-like structure
 *           Convert wordList into wordSet for constant lookup
 *           Maintain a set to track the vistied words so that we do not visit a visited word from a different path again
 */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        // edge case
        if(beginWord == null || endWord == null || beginWord.length() == 0 || endWord.length() == 0 || wordList == null || wordList.size() == 0) {
            return 0;
        }
        
        // convert wordList into wordSet
        Set<String> wordSet = new HashSet<String>(wordList);
        
        // if endWord is not in the input wordList then return 0 (can't reach endWord)
        if(!wordSet.contains(endWord)) {
            return 0;
        }
        
        // set to track the words already in queue or already processed
        Set<String> visitedWords = new HashSet<String>();
        
        // queue to perform BFS
        Queue<String> q = new LinkedList<String>();
        // level-0 intially
        int level = 0;
        // add beginWord to queue
        q.add(beginWord);
        
        // perform bfs until words in the queue
        while(!q.isEmpty()) {
            // get number of word at current level
            int levelSize = q.size();
            // increment level 
            level++;
            
            // process all words at current level
            while(levelSize-- > 0) {
                // remove the firstWord from queue and convert it to char array
                char[] currentWord = q.poll().toCharArray();
                
                // loop through each character of currentWord
                for(int i = 0 ; i < currentWord.length ; i++) {
                    // remember original character before modifying it
                    char originalCharacter = currentWord[i];
                    // loop through all the possibilities for modification
                    for(char ch = 'a' ; ch <= 'z' ; ch++) {
                        // change current character
                        currentWord[i] = ch;
                        
                        // convert modified character array into currentWord
                        String potentialWord = new String(currentWord);
                        
                        // if potentialWord exists in wordList and is not visited yet
                        if(wordSet.contains(potentialWord) && !visitedWords.contains(potentialWord)) {
                            // if potentialWord is endWord then return (level + 1) because it is at next level
                            if(potentialWord.equals(endWord)) {
                                return (level + 1);
                            }
                            // else add potentialWord to queue
                            q.add(potentialWord);
                            // else add potentialWord to visitedWords
                            visitedWords.add(potentialWord);
                        }
                    }
                    
                    // undo modified character
                    currentWord[i] = originalCharacter;
                } 
            }
        }
        
        // no valid word ladder exists
        return 0;
        
        
    }
}
