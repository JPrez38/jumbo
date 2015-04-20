# jumbo
Attempt to solve jumbo problem


I may look for a better way to do this. The attempt I made was to generate all permutations of a string and then find the combinations of each in order to attempt to find all possible words that could be made given the characters in a word

I first generated the dictionary. I used the mac dictionary from /usr/share/dict/words and I stored all values in a hashtable for O(1) lookup. Then I attempted to find all permutations. Unfortunately to find all permutations, it is O(n!) runtime, which is no fun so too long of a string will take far too long. 
