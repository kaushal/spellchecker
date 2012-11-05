Kaushal Parikh
Spellcheck Efficiency Analysis
readme.txt

Instructions:
To run the program.
Run javac spellcheck.java
run java spellcheck

To check the program with the 1000 most occurring words in the english
language, all randomly "messed up"
run javac generateWords.java
run java generateWords


Worst case run time analysis:
Since I am using a prefix tree, the search time is linear dependant on the
length of the word. The worst case for failure is evaluated below.

The worst case input for failure would be an input word with many repeated
vowels in a row with consonants surrounding them. 
To calculate the big O of this algorithm, I will evaluate the example below.

The checkWord would recursively break down the word, one repeated character at
a time, and then check all of the vowel iterations of that word. Since there
are 5 vowels this makes the amount of searches 5^v where v is the number of
vowels in a given word. So, if search fails each time from bottom to top we have our worst
case run time.

Input: zxaaaa

The analysis below is going from bottom to top. 
zxaaaa  5^4 = 625
zxaaa   5^3 = 125
zxaa    5^2 = 25
zxa     5^1 = 5

If each of these checks fails, we have a worst case run time of 
O(5^((v(v-1))/2))    where v is the number of vowels.


This run time only occurs in the very specific case of no combination of
vowels less than the given number of vowels will produce an actual word.
Furthermore, this is not a case you would be confronted with with normal use.

Given more time, there is a solution that I could implement to fix this
special case. I would need to edit the prefixTree class to recognize when it
at a leaf node after reaching multiple vowels, and realize that a word is not
possible. This information would have to be conveyed back up to checkWord
method which would then proceed to exit. 

zxaaaa

First test:     zxe -> null 
Second test:    zxa -> null
Third test:     zxi -> null
Fourth test:    zxo -> null
Fifth test:     zxu -> null
Exit method. 

In this situation, it would be 5 checks before the program realizes that a
word is not possible with the given combination of vowels.

